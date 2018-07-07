package com.hendisantika.oauth2jwt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : oauth2-jwt
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/07/18
 * Time: 06.50
 * To change this template use File | Settings | File Templates.
 */
@RestController
public class GeneralController {

    @GetMapping("/")
    public String home() {
        return "Hello World";
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody MultiValueMap<String, String> map) {
        return "OK";
    }

}