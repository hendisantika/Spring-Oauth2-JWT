package com.hendisantika.oauth2jwt.controller;

import com.hendisantika.oauth2jwt.entity.Account;
import com.hendisantika.oauth2jwt.entity.RestResponse;
import com.hendisantika.oauth2jwt.exception.RestError;
import com.hendisantika.oauth2jwt.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.AccountException;

/**
 * Created by IntelliJ IDEA.
 * Project : oauth2-jwt
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/07/18
 * Time: 06.51
 * To change this template use File | Settings | File Templates.
 */
@RestController
public class ApiController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/api/hello")
    public ResponseEntity<?> hello() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        String msg = String.format("Hello %s", name);
        return new ResponseEntity<Object>(msg, HttpStatus.OK);
    }

    @GetMapping(path = "/api/me", produces = "application/json")
    public Account me() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return accountService.findAccountByUsername(username);
    }

    @PostMapping(path = "/api/register", produces = "application/json")
    public ResponseEntity<?> register(@RequestBody Account account) {
        try {
            account.grantAuthority("ROLE_USER");
            return new ResponseEntity<Object>(
                    accountService.register(account), HttpStatus.OK);
        } catch (AccountException e) {
            e.printStackTrace();
            return new ResponseEntity<RestError>(new RestError(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping(path = "/api/user/remove", produces = "application/json")
    public ResponseEntity<?> removeUser() {
        try {
            accountService.removeAuthenticatedAccount();
            return new ResponseEntity<Object>(new RestResponse("User removed."), HttpStatus.OK);
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(new RestError(e.getMessage()), HttpStatus.OK);
        }
    }

}