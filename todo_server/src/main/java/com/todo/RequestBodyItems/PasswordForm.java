package com.todo.RequestBodyItems;

public class PasswordForm {
	public String oldPassword;
	public String newPassword;
	
	public PasswordForm(String oldPassword, String newPassword) {
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
