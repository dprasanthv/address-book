package com.cfm.utility;

import java.io.Serializable;

public class ContactFilter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7463407010382208883L;
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
