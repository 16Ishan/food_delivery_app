package com.delivery.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties
public class CustomError
{
	private String code;
	private String errorMessage;
	private String type;

	public CustomError(String code, String errorMessage, String type)
	{
		super();
		this.code = code;
		this.errorMessage = errorMessage;
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public CustomError setCode(String code)
	{
		this.code = code;
		return this;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public CustomError setErrorMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
		return this;
	}

	public String getType() {
		return type;
	}

	public CustomError setType(String type) {
		this.type = type;
		return this;
	}

	@Override
	public String toString() {
		return "Error [code=" + code + ", message=" + errorMessage + ", type=" + type + "]";
	}

}