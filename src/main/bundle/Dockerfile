FROM java:openjdk-8-jdk
EXPOSE 8080
WORKDIR /opt/${artifactId}/
ADD logback.xml /opt/${artifactId}/logback.xml
ADD ${jarFilename} /opt/${artifactId}/

CMD /usr/bin/java \
  -Xms512m \
  -Xmx1g \
  -Dfile.encoding=UTF-8 \
  -Dspring.profiles.active=\$SPRING_PROFILES_ACTIVE \
  -Dlogging.config=/opt/${artifactId}/logback.xml \
  -Dserver.port=8080 \
  -jar ${jarFilename}
