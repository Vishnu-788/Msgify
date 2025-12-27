FROM amazoncorretto:25
ADD target/msgify-springboot.jar /msgify-springboot.jar
ENTRYPOINT ["java", "-jar", "/msgify-springboot.jar"]