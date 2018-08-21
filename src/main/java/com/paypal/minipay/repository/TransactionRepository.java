package com.paypal.minipay.repository;

import org.springframework.data.repository.CrudRepository;

import com.paypal.minipay.entity.Transaction;

public interface TransactionRepository<T> extends CrudRepository<Transaction, String> {

}
