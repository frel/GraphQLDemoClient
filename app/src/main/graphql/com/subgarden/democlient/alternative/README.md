# Alternative schema

This is how multiple schemas is supported.

Note that when using multiple schemas all schemas must be listed explicitly:

```groovy
 apollo {
    service("demo") {
        schemaFile.set(file("src/main/graphql/com/subgarden/democlient/demo/schema.json"))
        srcDir(file("src/main/graphql/com/subgarden/democlient/demo/"))
        packageName.set("com.subgarden.democlient.demo")

    }

    service("alternative") {
        schemaFile.set(file("src/main/graphql/com/subgarden/democlient/alternative/schema.json"))
        srcDir(file("src/main/graphql/com/subgarden/democlient/alternative/"))
        packageName.set("com.subgarden.democlient.alternative")
    }
}

```