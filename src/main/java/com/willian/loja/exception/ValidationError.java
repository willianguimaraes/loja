package com.willian.loja.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


public class ValidationError extends StandartError{

    private List<FieldsMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }

    public List<FieldsMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message){
        errors.add(new FieldsMessage(fieldName,message));
    }
}
