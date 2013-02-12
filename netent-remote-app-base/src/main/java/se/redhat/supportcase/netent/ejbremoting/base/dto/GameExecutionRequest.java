package se.redhat.supportcase.netent.ejbremoting.base.dto;

import java.io.Serializable;

public class GameExecutionRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7400234484807876525L;
	private String request;

	public GameExecutionRequest(String request) {
		super();
		this.request = request;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

}
