package com.delivery.common.response;

import com.delivery.common.dto.CustomError;
import com.delivery.common.util.Constants;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.MDC;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessPageResponse<T>
{
	private boolean success = true;

	private Page<T> data;
	private List<CustomError> errors;
	private boolean isSuccess;
	private String timeStamp;
	private String correlationId = MDC.get(Constants.RequestTracking.CORRELATION_ID_VAR_NAME);

	public SuccessPageResponse() {
	}

	public SuccessPageResponse(Page<T> data, List<CustomError> errors, boolean isSuccess,
							   String timeStamp)
	{
		super();
		this.data = data;
		this.errors = errors;
		this.isSuccess = isSuccess;
		this.timeStamp = timeStamp;
	}

	public SuccessPageResponse(Page<T> data)
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

	public Page<T> getData() {
		return data;
	}

	public void setData(Page<T> data) {
		this.data = data;
	}

	public List<CustomError> getErrors() {
		return errors;
	}

	public void setErrors(List<CustomError> errors) {
		this.errors = errors;
	}
}
