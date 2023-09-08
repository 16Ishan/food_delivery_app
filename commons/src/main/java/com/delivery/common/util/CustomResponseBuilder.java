package com.delivery.common.util;

import com.delivery.common.dto.CustomError;
import com.delivery.common.response.SuccessPageResponse;
import com.delivery.common.response.SuccessResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public class CustomResponseBuilder<T>
{
    public SuccessResponse<T> buildResponse(T data, List<CustomError> errors, boolean isSuccess)
    {
        SuccessResponse<T> response = new SuccessResponse<>();
        response.setErrors(errors);
        response.setData(data);
        response.setSuccess(isSuccess);
        response.setTimeStamp(LocalDateTime.now().toString());

        return response;
    }

    public SuccessPageResponse<T> buildPageResponse(Page<T> data, List<CustomError> errors, boolean isSuccess)
    {
        SuccessPageResponse<T> response = new SuccessPageResponse<>();
        response.setErrors(errors);
        response.setData(data);
        response.setSuccess(isSuccess);
        response.setTimeStamp(LocalDateTime.now().toString());

        return response;
    }
}
