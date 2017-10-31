FROM eguahlak/glassfish-cph
MAINTAINER AKA RHP

COPY ./target/*.war /opt/glassfish4/glassfish/domains/domain1/autodeploy/SampleBackend.war

CMD [ "asadmin", "start-domain", "-v" ]
