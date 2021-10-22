plugins {
    java

    // Apply the application plugin to add support for building a CLI application in Java.
    application

    id("net.henryhc.fork.org.openjfx.javafxplugin")
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    compileOnly("org.jetbrains:annotations:22.0.0")

    // Use JUnit Jupiter for testing.
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.1")
}

javafx {
    version = "17.0.0.1"
    modules("javafx.graphics")
}

application {
    // Define the main class for the application.
    mainClass.set("hk.ust.cse.comp3021.lab7.Main")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
    withType<Jar> {
        manifest {
            attributes.apply {
                this["Main-Class"] = application.mainClass.get()
            }
        }
    }
    withType<Test> {
        group = "verification"

        // Use JUnit Platform for unit tests.
        useJUnitPlatform()

        systemProperties(
            "junit.jupiter.execution.timeout.testable.method.default" to "2000 ms"
        )
    }
}
