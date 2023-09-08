package com.delivery.common.response;

import com.delivery.common.dto.CustomError;
import com.delivery.common.util.Constants;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.MDC;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessResponse<T>
{
	private boolean success = true;

	private T data;
	private List<CustomError> errors;
	private boolean isSuccess;
	private String timeStamp;
	private String correlationId = MDC.get(Constants.RequestTracking.CORRELATION_ID_VAR_NAME);

	public SuccessResponse() {
	}

	public SuccessResponse(T data, List<CustomError> errors, boolean isSuccess,
						   String timeStamp)
	{
		super();
		this.data = data;
		this.errors = errors;
		this.isSuccess = isSuccess;
		this.timeStamp = timeStamp;
	}

	public SuccessResponse(T data)
	{
		super();
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<CustomError> getErrors() {
		return errors;
	}

	public void setErrors(List<CustomError> errors) {
		this.errors = errors;
	}
}
