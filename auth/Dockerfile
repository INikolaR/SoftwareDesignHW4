#FROM gradle AS BUILD
#RUN mkdir /opt/app
#WORKDIR /opt/app
#COPY .. .
#RUN ["gradle", "build"]

#FROM eclipse-temurin:21
#COPY --from=BUILD /opt/app .
#CMD ["java", "-jar", "/opt/app/auth/build/libs/auth-0.0.1-SNAPSHOT.jar"]
FROM eclipse-temurin:21
RUN mkdir /opt/app
COPY build/libs/auth-0.0.1-SNAPSHOT.jar /opt/app
CMD ["java", "-jar", "/opt/app/auth-0.0.1-SNAPSHOT.jar"]
