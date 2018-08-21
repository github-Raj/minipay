package com.paypal.minipay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paypal.minipay.commons.TRANSSTATUS;
import com.paypal.minipay.entity.Account;
import com.paypal.minipay.entity.Transaction;
import com.paypal.minipay.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	TransactionRepository<Transaction> transactionRepository;

	@Transactional
	public Transaction createTransaction(Account account, String transId) {
		Transaction transaction = new Transaction();
		transaction.setTransactionId(transId);
		transaction.setAccount(account);
		transaction.setAmount(account.getAmount());
		transaction.setComment("transaction");
		transaction.setCurrency(account.getCurrency());
		transaction.setStatus(TRANSSTATUS.COMPLETE);
		transaction.setTarnsactionTs(account.getLastChangeTs());
		transaction.setBalance(account.getBalance());

		transactionRepository.save(transaction);
		return transaction;
	}

}
