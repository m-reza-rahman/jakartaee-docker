FROM websphere-liberty
RUN installUtility install adminCenter-1.0 --acceptLicense

ADD javaee-cafe.war /config/dropins/javaee-cafe.war
COPY postgresql-42.2.4.jar  /opt/ibm/wlp/usr/shared/resources/
COPY server.xml /opt/ibm/wlp/usr/servers/defaultServer/
#VOLUME ~/javaeedemo-volume/:/config/dropins/
ENV LICENSE accept
#EXPOSE 80 9080 9448 9443 9060