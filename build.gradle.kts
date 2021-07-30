val libVersion = properties["version"] as String
val libGroup = properties["group"] as String

version = libVersion
group = libGroup

subprojects {
    group = libGroup
    version = libVersion
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
}

tasks.register("buildAll") {
    subprojects.forEach {
        dependsOn(it.name + ":build")
    }
}