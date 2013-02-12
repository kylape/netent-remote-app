package se.redhat.supportcase.netent.ejbremoting.base.dto;

import java.io.Serializable;

public class GameExecutionResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2912992969345141708L;
	private String response;

	public GameExecutionResponse(String response) {
		super();
		this.response = response;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
