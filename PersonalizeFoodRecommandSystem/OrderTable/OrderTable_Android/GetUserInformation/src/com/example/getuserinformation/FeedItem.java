package com.example.getuserinformation;

public class FeedItem {
	
	private String name;
	private String date;
	private String message;

	public FeedItem(String inName, String inDate, String inMessage) {
		name = inName;
		date = inDate;
		message = inMessage;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
