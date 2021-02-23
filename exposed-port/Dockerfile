FROM websphere-liberty
RUN installUtility install adminCenter-1.0 --acceptLicense

COPY postgresql-42.2.18.jar /opt/ibm/wlp/usr/shared/resources/
COPY server.xml /config/
ADD jakartaee-cafe.war /config/dropins/jakartaee-cafe.war
