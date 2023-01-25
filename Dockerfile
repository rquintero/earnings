FROM softleader/openjre8:latest
ADD ./build/libs/stock-1.0-SNAPSHOT.jar /opt/stock-1.0.jar
ENTRYPOINT ["java", "-jar", "/opt/stock-1.0.jar"]