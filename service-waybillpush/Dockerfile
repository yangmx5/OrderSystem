FROM java
COPY target/*.jar app.jar
RUN bash -c 'touch ./app.jar'
EXPOSE 8763
CMD ["java","-jar","app.jar"]