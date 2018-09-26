FROM websphere-liberty
ADD javaee-cafe.war /config/dropins/javaee-cafe.war
COPY postgresql-42.2.4.jar  /opt/ibm/wlp/usr/shared/resources/
COPY server.xml /opt/ibm/wlp/usr/servers/defaultServer/
#VOLUME ~/javaeedemo-volume/:/config/dropins/