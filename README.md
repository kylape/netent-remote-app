netent-remote-ejb
=================

Project to reproduce the net ent issue with remote EJB

To build and run the test do the following

	mvn clean install jboss-as:deploy

	cd netent-remote-app-client
	mvn assembly:single

	java -jar target/netent-remote-app-client-0.0.1-SNAPSHOT-jar-with-dependencies.jar localhost 1000 4000 100 teststring


The parameters is
 - first is hostname
 - second is number of threads
 - third is wait time between request
 - fourth is number of request per thread
 - fifth is a string to send to the server


