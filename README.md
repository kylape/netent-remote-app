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

To reproduce the issue setup a remote server running JBoss EAP 6.0.1 with default configuration. Add a manager user (using add-user.sh). Also add a remote user with
	Username: admin
	Password: admin

Deploy the netent-remote-app/netent-remote-app-ear/target/netent-remote-app.ear to the server.

Run the the client with the following command

	java -jar target/netent-remote-app-client-0.0.1-SNAPSHOT-jar-with-dependencies.jar <server-url> 1000 4000 100 teststring

Wait for the client to throw exceptions in the console log. Please note that the client should be run on a remote server (since loopback interface doesn't seem to be affecgted by this).


 


