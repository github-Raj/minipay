package com.paypal.minipay.repository;

import org.springframework.data.repository.CrudRepository;

import com.paypal.minipay.entity.User;

public interface UserRepository<U> extends CrudRepository<User, String> {

}
