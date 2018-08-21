package com.paypal.minipay.repository;

import org.springframework.data.repository.CrudRepository;

import com.paypal.minipay.entity.Account;

public interface AccountRepository<A> extends CrudRepository<Account, Integer> {
	

}
