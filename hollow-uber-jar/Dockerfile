FROM websphere-liberty:kernel
RUN installUtility install jsf-2.3 --acceptLicense
RUN installUtility install jaxrs-2.1 --acceptLicense
RUN installUtility install ejbLite-3.2 --acceptLicense
RUN installUtility install cdi-2.0 --acceptLicense
RUN installUtility install beanValidation-2.0 --acceptLicense
RUN installUtility install jpa-2.2 --acceptLicense
RUN installUtility install jsonb-1.0 --acceptLicense
RUN installUtility install jaxb-2.2 --acceptLicense

COPY postgresql-42.2.18.jar /opt/ibm/wlp/usr/shared/resources/
COPY server.xml /config/
ADD jakartaee-cafe.war /config/dropins/jakartaee-cafe.war