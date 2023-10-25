FROM openjdk:17-slim

# 작업 디렉터리 설정
WORKDIR /app

# Spring 소스 코드를 이미지에 복사
COPY . .

# gradlew를 이용한 프로젝트 빌드
RUN ./gradlew clean build

# 빌드 결과 jar 파일을 실행
CMD ["java", "-jar", "/app/build/libs/ssap-0.0.1-SNAPSHOT.jar"]