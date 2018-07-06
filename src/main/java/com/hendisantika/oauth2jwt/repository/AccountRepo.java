package com.hendisantika.oauth2jwt.repository;

import com.hendisantika.oauth2jwt.entity.Account;
import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : oauth2-jwt
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 07/07/18
 * Time: 05.30
 * To change this template use File | Settings | File Templates.
 */
public interface AccountRepo extends Repository<Account, Long> {

    Collection<Account> findAll();

    Optional<Account> findByUsername(String username);

    Optional<Account> findById(Long id);

    Integer countByUsername(String username);

    Account save(Account account);

    void deleteAccountById(Long id);

}
