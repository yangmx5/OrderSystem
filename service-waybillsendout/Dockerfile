FROM java
COPY target/*.jar app.jar
RUN bash -c 'touch ./app.jar'
EXPOSE 8764
CMD ["java","-jar","app.jar"]