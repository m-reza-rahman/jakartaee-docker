FROM websphere-liberty
RUN installUtility install adminCenter-1.0 --acceptLicense
RUN installUtility install collectiveController-1.0 --acceptLicense
RUN collective create defaultServer --keystorePassword=admin123

COPY keystore.xml /config/configDropins/defaults/
COPY server.xml /config/
