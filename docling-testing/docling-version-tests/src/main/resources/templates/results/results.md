# Results for {results.registry}/{results.image} as of {results.timestamp}

Here are the results:

| Tag | Result | Details |
| --- | ------ | ------- |
{#each results.results}
| {it.tag} | {it.result.status.icon} {it.result.status.name()} | [Click for run details](#{it.tag}-details) |
{/each}

## Details

{#each results.results}
### {results.registry}/{results.image}:{it.tag}

<details id="{it.tag}-details">
<summary>Click to expand</summary>

#### Message

<details open>
<summary>Click to collapse</summary>

~~~markdown
{it.result.message}
~~~

</details>

{#if it.result.fullStackTrace}
#### Full stack trace

<details open>
<summary>Click to collapse</summary>

```
{it.result.fullStackTrace}
```

</details>
{/if}

{#if it.serverLogs}
#### Docling server logs

<details>
<summary>click to expand</summary>

```
{it.serverLogs}
```

</details>

{/if}
</details>

{/each}
