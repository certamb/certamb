package org.sistcoop.cooperativa;

import java.io.Serializable;

public class Jsend implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private Object id;

	public static Jsend getSuccessJSend(Object id) {
		Jsend jsend = new Jsend();
		jsend.setId(id);
		
		return jsend;
	}

	private Jsend() {
		// TODO Auto-generated constructor stub
	}

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

}
