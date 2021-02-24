FROM websphere-liberty

COPY server/postgresql-42.2.18.jar /opt/ibm/wlp/usr/shared/resources/
COPY server/server.xml /config/
ADD target/jakartaee-cafe.war /config/dropins/jakartaee-cafe.war
