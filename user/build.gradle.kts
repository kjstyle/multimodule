plugins {
    id("java")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

group = "kjstyle"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":common"))

    // Spring Boot 스타터
    implementation("org.springframework.boot:spring-boot-starter-web") // 웹 MVC
    implementation("org.springframework.boot:spring-boot-starter-data-jpa") // JPA
    implementation("org.springframework.boot:spring-boot-starter-validation") // Bean Validation

    // 롬복
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")

    // DB 드라이버
    runtimeOnly("com.h2database:h2")

    // 테스트
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}