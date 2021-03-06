package com.cafuc.addressbook.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseBean {
    private int code;
    private String msg;
    private Object data;
}