"""
Ease documentation writing with mkdocs

- load the content of the 'variables.yaml' file
- provide the {{insert(file, tag)} macro

## Loading attributes

This feature loads a yaml file (default is `docs/attributes.yaml`), and import the content into the environment.
For example, if the imported file contains:

```
attributes:
  versions:
    camel: 3.13.0
    spec: 2.0.1
    mutiny: 1.1.2
  project-version: '3.14.0-SNAPSHOT'
  smallrye-config-version: '2.7.0'
```

You can access values using `{{ attributes.versions.camel}}` or `{{ attributes['project-version'] }}`.

The loaded file is `docs/variables.yaml` by default. The location can be configured in the `mkdocs.yml` file with:

```
extra:
  attributes_path: docs/my-attributes.yaml
```

## Partial file include

This feature allows including part of a file.
For example, include the code from the `Foo.java` located between `<example>` and `</example>`

```java linenums="1"
{{ insert('src/test/Foo.java', 'example') }}
```

The `Foo.java` file contain something like:

```
package test;

public class Foo {

    // <example> this is my snippet
    public static void main(String[]args){
        System.out.println("yoohoo");
    }
    // </example>

}
```

The file path is relative to the `docs` directory.
You can change the location using the `snippet_dir` attributes in the `mkdocs.yaml` file:

```
extra:
  snippet_dir: docs/snippets/src
```

Then, insert your snippet using:

```java linenums="1"
{{ insert('test/Foo.java', 'example') }}
```

When inserting you can:
- Pass a set of tags to skip - the content between the start and end tag will be excluded
- Hide a set of tags - //<tag> and //</tag> will be removed from the output

```java linenums="1"
{{ insert('test/Foo.java', 'example', [skip1, skip2], [hide1]) }}
```

"""

import math
import yaml
import textwrap
import os.path


def loadAttributes(env):
    path = env.conf['docs_dir'] + "/attributes.yaml"
    if 'attributes_path' in env.variables:
        path = env.variables['attributes_path']

    if os.path.exists(path):
        file = open(path)
        var = yaml.load(file, Loader=yaml.FullLoader)
        file.close()
        if var is not None:
            env.variables['attributes'] = var['attributes']
    else:
        print("Unable to import attributes - " + path  + " does not exists")

def areTagsInLine(line, tags):
    for tag in tags:
        if "<" + tag + ">" in line or "</" + tag + ">" in line:
            return True
    return False

def hasStartTagInLine(line, tags):
    for tag in tags:
        if "<" + tag + ">" in line:
            return True
    return False

def hasEndTagInLine(line, tags):
    for tag in tags:
        if "</" + tag + ">" in line:
            return True
    return False

def define_env(env):

    loadAttributes(env)

    @env.macro
    def insert(file, tag = None, skipTags = [], hideTags = []):
        root = env.conf['docs_dir']
        if 'snippet_dir' in env.variables:
           root = env.variables['snippet_dir']

        f = open(root + "/" + file)

        if tag is None:
            text = f.read()
            f.close()
            return textwrap.dedent(text)

        if tag is None:
            text = f.read()
            f.close()
            return textwrap.dedent(text)

        trace = False
        if "Hero.java" in file:
            trace = True

        if trace : print(f"Inserting {tag} from {file}")

        inRecordingMode = False
        inExclusionMode = False
        c = ""
        for line in f:
            if not inRecordingMode and not inExclusionMode:
                if "<" + tag + ">" in line:
                    inRecordingMode = True
            elif not inExclusionMode and "</" + tag + ">" in line:
                inRecordingMode = False
            elif inRecordingMode  and hasStartTagInLine(line, skipTags):
                inExclusionMode = True
            elif inExclusionMode  and hasEndTagInLine(line, skipTags):
                inExclusionMode = False
            elif inRecordingMode and not inExclusionMode:
                if not areTagsInLine(line, hideTags):
                       c += line

        f.close()
        if not c:
            raise Exception(f"Unable to find tag '{tag}' in '{file}'")

        c = textwrap.dedent(c)
        return c

    @env.macro
    def image(path, title):
        return f"""<figure class="docissimo, docissimo-figure">
              <img src="{path}" style="margin-left:auto; margin-right:auto;" alt="{title}">
              <figcaption style="margin-left:auto; margin-right:auto;">{title}</figcaption>
            </figure>"""

    @env.macro
    def javadoc(clazz, full = False, artifact = None):
        version = env.variables.attributes['project-version']
        if (artifact is None) :
            artifact = env.variables['javadoc_artifact']

        if not artifact:
            raise Exception("Missing configuration: 'extra.javadoc_artifact'")

        if not version:
            version = 'latest'

        path = clazz.replace(".", "/") + ".html"
        if full :
            name = clazz
        else :
            name = clazz.rsplit('.', 1)[1]
        return f"""<a class='docissimo, docissimo-javadoc' href='https://javadoc.io/doc/{artifact}/{version}/{path}'>{name}</a>"""

    @env.filter
    def reverse(x):
        "Reverse a string (and uppercase)"
        return x.upper()[::-1]
