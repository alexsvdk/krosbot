val libVersion = properties["version"] as String
val libGroup = properties["group"] as String

version = libVersion
group = libGroup

subprojects {
    tasks.withType<Jar> {
        manifest {
            attributes(
                mapOf(
                    "Implementation-Title" to this@subprojects.name,
                    "Implementation-Version" to this@subprojects.version
                )
            )
        }
    }
    repositories {
        mavenLocal()
        maven { setUrl("https://jitpack.io") }
    }
    group = libGroup
    version = libVersion
}

tasks.register("buildAll") {
    subprojects.forEach {
        dependsOn(it.name + ":build")
    }
}