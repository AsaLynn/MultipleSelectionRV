package com.example.my01.entity;

import java.io.Serializable;

/**
 * package: com.css.mobileoffice.model.entity.BaseEntity
 * author: gyc
 * description:实体基类
 * time: create at 2018/3/13 16:05
 */

public class BaseEntity implements Serializable {
    String code;
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
