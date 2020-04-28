# Alternative schema

This is how multiple schemas is supported.

Note that when using multiple schemas all schemas must be listed explicitly:

```groovy
 apollo {
    service("demo") {
        sourceFolder.set("com/subgarden/democlient/demo")
        rootPackageName.set("com.subgarden.democlient.demo")
    }

    service("alternative") {
        sourceFolder.set("com/subgarden/democlient/alternative")
        rootPackageName.set("com.subgarden.democlient.alternative")
    }
}

```