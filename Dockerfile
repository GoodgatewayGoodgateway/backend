# 1단계: 빌드
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

COPY . .

RUN chmod +x ./gradlew

# 테스트 생략하고 빌드만
RUN ./gradlew build -x test --no-daemon

# 2단계: 실행
FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
