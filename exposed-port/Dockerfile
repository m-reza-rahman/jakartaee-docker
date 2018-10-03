FROM websphere-liberty
RUN installUtility install --acceptLicense adminCenter-1.0

COPY postgresql-42.2.4.jar /opt/ibm/wlp/usr/shared/resources/
COPY server.xml /src/
RUN rm -rf /config/server.xml && mv /src/server.xml /config/ &&\
    chmod -R 777 /config/server.xml
ADD javaee-cafe.war /config/dropins/javaee-cafe.war

#NOTE: I Tried tnext but I didn't work
#COPY server.xml /config/
#RUN chmod -R 777 /config/server.xml
#RUN chmod 777 /opt/ibm/wlp/usr/servers/defaultServer/server.xml