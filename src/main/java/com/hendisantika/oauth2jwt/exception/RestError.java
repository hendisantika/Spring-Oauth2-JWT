package com.hendisantika.oauth2jwt.exception;

/**
 * Created by IntelliJ IDEA.
 * Project : oauth2-jwt
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 07/07/18
 * Time: 05.31
 * To change this template use File | Settings | File Templates.
 */
public class RestError {
    private String message;

    public RestError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
