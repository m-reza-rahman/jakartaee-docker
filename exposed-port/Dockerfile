FROM websphere-liberty
RUN installUtility install adminCenter-1.0 --acceptLicense

COPY postgresql-42.2.4.jar /opt/ibm/wlp/usr/shared/resources/
COPY server.xml /config/
ADD javaee-cafe.war /config/dropins/javaee-cafe.wa