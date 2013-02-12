package se.redhat.supportcase.netent.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.naming.NamingException;

import org.jboss.ejb.client.EJBClient;
import org.jboss.ejb.client.EJBClientConfiguration;
import org.jboss.ejb.client.EJBClientContext;
import org.jboss.ejb.client.PropertiesBasedEJBClientConfiguration;
import org.jboss.ejb.client.StatelessEJBLocator;
import org.jboss.ejb.client.remoting.ConfigBasedEJBClientContextSelector;

import se.redhat.supportcase.netent.ejbremoting.base.dto.GameExecutionRequest;
import se.redhat.supportcase.netent.ejbremoting.base.service.GameService;

public class RemoteClient {
	private GameService service;

	public RemoteClient(String host) {
		try {
			service = lookupStatelessRemoteService(GameService.class,
					"netent-remote-app",
					"netent-remote-app-ejb-0.0.1-SNAPSHOT",
					"GameServiceEjb", "", host);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws NamingException, IOException {
		RemoteClient remoteClient = new RemoteClient(args[0]);
		remoteClient.execute();
	}

	/**
	 * call remote service and so some stuff
	 * 
	 * @return
	 * @throws IOException
	 * @throws NamingException
	 */
	public void execute() throws NamingException, IOException {
		service.execute(new GameExecutionRequest("The remote request"))
				.getResponse();
	}

	/**
	 * call remote service and so some stuff
	 * 
	 * @return
	 * @throws IOException
	 * @throws NamingException
	 */
	public void execute(String requestStr) throws NamingException, IOException {
		service.execute(new GameExecutionRequest(requestStr)).getResponse();
	}

	/**
	 * Looks up and returns the proxy to remote stateless calculator bean
	 * 
	 * @param <T>
	 * 
	 * @return
	 * @throws NamingException
	 * @throws IOException
	 */
	private <T> T lookupStatelessRemoteService(Class<T> apiClass,
			String earPackageName, String serviceJarName,
			String implementationClassName, String distinctName, String host)
			throws NamingException, IOException {
		final String clientPropsName = "ejb_client.properties";
		final InputStream inputStream = RemoteClient.class.getClassLoader()
				.getResourceAsStream(clientPropsName);
		if (inputStream == null) {
			throw new RuntimeException(
					"Could not find EJB client configuration properties at "
							+ clientPropsName + " using classloader "
							+ RemoteClient.class.getClassLoader());
		}
		final Properties clientProps = new Properties();

		clientProps
				.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED",
						"false");
		clientProps.put("remote.connections", "default");
		clientProps.put("remote.connection.default.host", host);
		clientProps.put("remote.connection.default.port", "4447");
		clientProps.put("remote.connection.default.username", "admin");
		clientProps.put("remote.connection.default.password", "adminadmin");
		clientProps
				.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS",
						"false");
		final EJBClientConfiguration clientConfiguration = new PropertiesBasedEJBClientConfiguration(
				clientProps);
		final ConfigBasedEJBClientContextSelector selector = new ConfigBasedEJBClientContextSelector(
				clientConfiguration);
		EJBClientContext.setSelector(selector);

		final StatelessEJBLocator<T> statelessEJBLocator = new StatelessEJBLocator<T>(
				apiClass, earPackageName, serviceJarName,
				implementationClassName, distinctName);
		return EJBClient.createProxy(statelessEJBLocator);
	}
}
