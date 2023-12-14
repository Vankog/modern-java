plugins {
    java
    `test-report-aggregation`
    `jacoco-report-aggregation`
}

group = "org.example"
version = ""


repositories {
    mavenCentral()
}

tasks.withType(Test::class).configureEach({
    finalizedBy(
        tasks.testAggregateTestReport,
        tasks.testCodeCoverageReport
    )
})


private fun optimalForkCount(): Int {
    return (Runtime.getRuntime().availableProcessors() / 2).coerceAtLeast(1)
}

private val optimalForkCount = optimalForkCount()

allprojects {

    apply(plugin = "java")
    apply(plugin = "jacoco")

    java({
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    })

    jacoco({
        toolVersion = "0.8.11" // https://github.com/jacoco/jacoco/releases
    })

    tasks.withType<JavaCompile> {
        val compilerArgs = options.compilerArgs
        compilerArgs.add("--enable-preview")
        options.encoding = "UTF-8"
    }
    tasks.withType<JavaExec> {
        jvmArgs(listOf("--enable-preview"))
    }

    tasks.test {
        useJUnitPlatform()
        setMaxParallelForks(optimalForkCount)
        jvmArgs(listOf("--enable-preview"))
    }
}
