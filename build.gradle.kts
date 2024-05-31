plugins {
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.noarg") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
    application
}

group = "ua.kpi.its.lab.rest"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    val spring = "6.1.5"
    val log4j = "2.23.1"
    val jackson = "2.17.0"

    // Needed for Spring Data
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.22")

    implementation("org.springframework:spring-context:${spring}")
    implementation("org.springframework.data:spring-data-jpa:3.2.3")

    // In Memory DB
    implementation("org.hsqldb:hsqldb:2.7.2")
    // Hibernate
    implementation("org.hibernate.orm:hibernate-core:6.4.4.Final")

    // Web
    compileOnly("jakarta.servlet:jakarta.servlet-api:6.0.0")
    implementation("org.eclipse.jetty.ee10:jetty-ee10-webapp:12.0.8")
    implementation("org.springframework:spring-webmvc:${spring}")

    // Serialization
    implementation("com.fasterxml.jackson.core:jackson-databind:${jackson}")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${jackson}")

    implementation("org.apache.logging.log4j:log4j-core:${log4j}")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:${log4j}")

    testImplementation(kotlin("test", "1.9.22"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
application {
    mainClass.set("ua.kpi.its.lab.rest.MainKt")
}

// Configures the noArg plugin to generate zero-argument constructors for JPA entities,
// which is necessary for entity instantiation by Hibernate.
noArg {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
    invokeInitializers = true // Ensures that property initializers are called in the generated constructors.
}

// Configures the allOpen plugin to prevent Kotlin from marking classes as final if they are annotated
// with JPA annotations. This is crucial for enabling runtime proxying and AOP-based enhancements.
allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

