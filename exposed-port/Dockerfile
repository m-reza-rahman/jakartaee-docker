FROM websphere-liberty
RUN installUtility install --acceptLicense adminCenter-1.0

COPY postgresql-42.2.4.jar /opt/ibm/wlp/usr/shared/resources/
COPY server.xml /config/
ADD javaee-cafe.war /config/dropins/javaee-cafe.war
