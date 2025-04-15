# 1. 사용할 베이스 이미지 선택 (Java 17용)
FROM openjdk:17-jdk-slim

# 2. 작업 디렉터리 설정
WORKDIR /app

# 3. 빌드된 jar 파일을 복사
COPY build/libs/*.jar app.jar

# 4. 포트 설정 (Cloud Run에서 컨테이너가 수신 대기할 포트)
EXPOSE 8080
ENV PORT=8080

# 5. 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
