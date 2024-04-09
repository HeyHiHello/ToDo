package com.todo.RequestBodyItems;

public class EmailForm {
	private String newEmail;
	
	public EmailForm() {
		
	}
	
	public EmailForm(String newEmail) {
		this.newEmail = newEmail;
	}
	
	public String getNewEmail() {
		return newEmail;
	}
	
	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}
}
