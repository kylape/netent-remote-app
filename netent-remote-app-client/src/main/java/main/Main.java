package main;


import org.jboss.logging.Logger;

import se.redhat.supportcase.netent.client.RemoteClient;

public class Main {
	final private static Logger logger = Logger.getLogger(Main.class);

	/**
     * 1 =host 2 =number of threads 3 = waitTimeInEachRequest 4=NumOfRequestByEachThread
     * 
     * 5 = request String
     * 
     * @param args
     * @throws IOException
     * @throws NamingException
     */
	public static void main(final String[] args) {
		final RemoteClient remoteClientEJB = new RemoteClient(args[0]);
        int numberOfThreads = Integer.parseInt(args[1]);
        final int waitTimeInEachRequest = Integer.parseInt(args[2]);
        final int numOfRequestByEachThread = Integer.parseInt(args[3]);
        for (int i = 0; i < numberOfThreads; i++) {

            (new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < numOfRequestByEachThread; i++) {
                        try {
                            Long begin = System.currentTimeMillis();
                            remoteClientEJB.execute(args[4]);
                            logger.info( this.toString() + " - Request number:" + i + " executed in -" + (System.currentTimeMillis() - begin));
                            Thread.sleep(waitTimeInEachRequest);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            })).start();
        }
        try {
            Thread.sleep(1000 * 1000);
        } catch (InterruptedException e) {          
            e.printStackTrace();
        }
        System.out.println("There");

	}

}
