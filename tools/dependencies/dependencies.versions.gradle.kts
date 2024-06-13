buildscript {
    mapOf(
        "core" to "1.13.1",
        "appcompat" to "1.7.0",
        "androidMaterial" to "1.12.0",
        "constraintLayout" to "2.1.4",
        "workRuntime" to "2.9.0",
        "junit" to "4.13.2",
        "testExtJunit" to "1.1.5",
        "testEspressoJunit" to "3.5.1",
        "retrofit" to "2.9.0",
        "lifecycle" to "2.8.1",
        "lifecycleCommon" to "2.2.0",
        "coroutinesCore" to "1.5.0",
        "coroutinesAndroid" to "1.7.3",
        "koin" to "3.1.2"
    ).forEach { (name, version) ->
        project.extra.set(name, version)
    }
}


