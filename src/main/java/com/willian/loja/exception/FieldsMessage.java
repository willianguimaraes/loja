package com.willian.loja.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldsMessage implements Serializable {

    private String fieldName;
    private String messages;
}
