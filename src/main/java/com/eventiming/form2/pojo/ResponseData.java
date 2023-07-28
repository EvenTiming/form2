package com.eventiming.form2.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData<T> {

    private String code;
    private HashMap<String, String> message;
    private String token;
    private T data;

}
