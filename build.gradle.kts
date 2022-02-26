buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Build.android_build_tools)
        classpath(Build.gradle_kotlin)
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.wrapper {
    distributionType = Wrapper.DistributionType.ALL
}

tasks.register("assembleHomework", Zip::class) {
    dependsOn("clean", ":app:clean")
    archiveFileName.set("pocket-homework.zip")
    destinationDirectory.set(layout.projectDirectory)
    from(layout.projectDirectory) {
        exclude(".*")
        exclude("local.properties")
        exclude("pocket-homework.zip")
    }

}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
    delete("pocket-homework.zip")
}