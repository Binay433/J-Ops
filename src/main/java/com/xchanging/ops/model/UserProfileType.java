package com.xchanging.ops.model;

public enum UserProfileType {
	USER("USER"),
	OPS("OPS"),
	ADMIN("ADMIN"),
	SUPER("SUPER");
;
	
	String userProfileType;
	
	private UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType(){
		return userProfileType;
	}
	
}
