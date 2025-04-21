# 1단계: Gradle Wrapper 사용해서 빌드
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

COPY . .

# gradlew에 실행 권한 부여 (중요)
RUN chmod +x ./gradlew

# build
RUN ./gradlew build --no-daemon

# 2단계: 실제 실행용 이미지
FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
