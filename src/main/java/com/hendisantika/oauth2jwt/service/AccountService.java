package com.hendisantika.oauth2jwt.service;

import com.hendisantika.oauth2jwt.entity.Account;
import com.hendisantika.oauth2jwt.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountException;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : oauth2-jwt
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 07/07/18
 * Time: 05.32
 * To change this template use File | Settings | File Templates.
 */
@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Account> account = accountRepo.findByUsername(s);
        if (account.isPresent()) {
            return account.get();
        } else {
            throw new UsernameNotFoundException(String.format("Username[%s] not found", s));
        }
    }

    public Account findAccountByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountRepo.findByUsername(username);
        if (account.isPresent()) {
            return account.get();
        } else {
            throw new UsernameNotFoundException(String.format("Username[%s] not found", username));
        }

    }

    public Account register(Account account) throws AccountException {
        if (accountRepo.countByUsername(account.getUsername()) == 0) {
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            return accountRepo.save(account);
        } else {
            throw new AccountException(String.format("Username[%s] already taken.", account.getUsername()));
        }
    }

    @Transactional // To successfully remove the date @Transactional annotation must be added
    public void removeAuthenticatedAccount() throws UsernameNotFoundException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account acct = findAccountByUsername(username);
        accountRepo.deleteAccountById(acct.getId());

    }
}