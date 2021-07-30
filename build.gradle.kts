val libVersion = properties["version"] as String
val libGroup = properties["group"] as String

version = libVersion
group = libGroup

plugins {
    kotlin("jvm") version "1.5.21"
    `java-library`
    `maven-publish`
}


subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")

    tasks {
        val sourcesJar by creating(Jar::class) {
            archiveClassifier.set("sources")
            from(sourceSets.main.get().allSource)
        }

        val javadocJar by creating(Jar::class) {
            dependsOn.add(javadoc)
            archiveClassifier.set("javadoc")
            from(javadoc)
        }

        artifacts {
            archives(sourcesJar)
            archives(javadocJar)
            archives(jar)
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