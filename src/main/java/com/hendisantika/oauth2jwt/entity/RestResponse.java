package com.hendisantika.oauth2jwt.entity;

/**
 * Created by IntelliJ IDEA.
 * Project : oauth2-jwt
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 07/07/18
 * Time: 05.29
 * To change this template use File | Settings | File Templates.
 */
public class RestResponse {

    String msg;

    public RestResponse(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
