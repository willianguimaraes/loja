package com.willian.loja.exception;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class StandartError implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Integer status;
    private String msg;
    private Long timeStamp;

    public StandartError(Integer status, String msg, Long timeStamp) {
        this.status = status;
        this.msg = msg;
        this.timeStamp = timeStamp;
    }


}
