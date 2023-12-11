plugins {
    id("java")
}

group = "org.example"
version = ""


repositories {
    mavenCentral()
}


subprojects {

    apply(plugin="java")

    java({
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    })

    tasks.withType<JavaCompile> {
        val compilerArgs = options.compilerArgs
        compilerArgs.add("--enable-preview")
    }

    tasks.test {
        useJUnitPlatform()
        jvmArgs(listOf("--enable-preview"))
    }
}