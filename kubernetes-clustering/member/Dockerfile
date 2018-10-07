FROM websphere-liberty
RUN installUtility install adminCenter-1.0 --acceptLicense
RUN installUtility install collectiveMember-1.0 --acceptLicense
RUN collective join defaultServer --host=172.17.0.3 --port=9443 --user=admin --password=admin123 --keystorePassword=admin123

COPY ../postgresql-42.2.4.jar /opt/ibm/wlp/usr/shared/resources/
COPY server.xml /config/
ADD ../javaee-cafe.war /config/dropins/javaee-cafe.war
