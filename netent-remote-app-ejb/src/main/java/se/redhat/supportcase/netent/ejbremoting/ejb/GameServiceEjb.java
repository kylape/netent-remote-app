package se.redhat.supportcase.netent.ejbremoting.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebService;

import org.jboss.ejb3.annotation.Clustered;

import se.redhat.supportcase.netent.ejbremoting.base.dto.GameExecutionRequest;
import se.redhat.supportcase.netent.ejbremoting.base.dto.GameExecutionResponse;
import se.redhat.supportcase.netent.ejbremoting.base.service.GameService;

@Stateless
@Remote(GameService.class)
@Clustered
@WebService(endpointInterface="se.redhat.supportcase.netent.ejbremoting.base.service.GameService")
public class GameServiceEjb implements GameService {
	

	@Override
	public GameExecutionResponse execute(GameExecutionRequest request) {
		GameExecutionResponse response = new GameExecutionResponse("Response from JBoss EAP 6");
		return response;
	}

}
