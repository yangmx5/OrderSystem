FROM java
COPY target/*.jar app.jar
RUN bash -c 'touch ./app.jar'
EXPOSE 8761
CMD ["java","-jar","app.jar"]