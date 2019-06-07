FROM websphere-liberty

COPY server/postgresql-42.2.4.jar /opt/ibm/wlp/usr/shared/resources/
COPY server/server.xml /config/
ADD target/javaee-cafe.war /config/dropins/javaee-cafe.war
