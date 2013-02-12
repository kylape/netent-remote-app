package se.redhat.supportcase.netent.ejbremoting.base.service;

import javax.jws.WebService;

import se.redhat.supportcase.netent.ejbremoting.base.dto.GameExecutionRequest;
import se.redhat.supportcase.netent.ejbremoting.base.dto.GameExecutionResponse;

@WebService
public interface GameService {
	
	GameExecutionResponse execute(GameExecutionRequest request);

}
