package com.abn.assessment.mycookbook.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Response<T> {
    private T data;
    private Object errors;

    public void addErrorMsgToResponse(String msgError) {
        ResponseError error = new ResponseError()
                .setDetails(msgError)
                .setTimestamp(LocalDateTime.now());
        setErrors(error);
    }
}
