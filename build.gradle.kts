val libVersion = properties["version"] as String

subprojects {
    version = libVersion
}

tasks.register("buildAll"){
    subprojects.forEach {
        dependsOn(it.name+":build")
    }
}