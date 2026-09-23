package ai.docling.serve.client;

import java.util.HashSet;
import java.util.Set;

/**
 * A class loader hiding the given classes, to simulate a missing dependency.
 */
final class ClassHidingClassLoader extends ClassLoader {
  private final Set<String> classesToHide = new HashSet<>();

  ClassHidingClassLoader(String... classesToHide) {
    this(Thread.currentThread().getContextClassLoader(), classesToHide);
  }

  ClassHidingClassLoader(ClassLoader parent, String... classesToHide) {
    super(parent);

    if (classesToHide != null) {
      this.classesToHide.addAll(Set.of(classesToHide));
    }
  }

  @Override
  public Class<?> loadClass(String name) throws ClassNotFoundException {
    if (this.classesToHide.contains(name)) {
      throw new ClassNotFoundException("Class %s not found".formatted(name));
    }

    return super.loadClass(name);
  }
}
