package com.xchanging.ops.model;

import java.io.Serializable;

public class SendMail implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String date;
	private String comments;
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public SendMail() {
	}
	public SendMail(String date, String comments) {
		super();
		this.date = date;
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "SendMail [date=" + date + ", comments=" + comments + "]";
	}
	
	
	
}
