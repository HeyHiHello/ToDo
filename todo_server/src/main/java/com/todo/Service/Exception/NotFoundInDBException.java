package com.todo.Service.Exception;

import java.sql.SQLException;

public class NotFoundInDBException extends SQLException {

	private static final long serialVersionUID = 1L;

	public NotFoundInDBException(String reason) {
		super(reason);
	}
	
	

}
