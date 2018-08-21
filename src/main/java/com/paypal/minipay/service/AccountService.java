package com.paypal.minipay.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paypal.minipay.commons.Constants;
import com.paypal.minipay.entity.Account;
import com.paypal.minipay.repository.AccountRepository;
import com.paypal.minipay.to.DebitCreditTO;

@Service
public class AccountService {

	@Autowired
	AccountRepository<Account> accountRepository;

	@Autowired
	TransactionService transactionService;
	
	@Transactional
	public Account save(Account account) {
		return accountRepository.save(account);
	}

	@Transactional
	public Account addMoney(DebitCreditTO debitCredit, String transId) {
		Account accnt = accountRepository.findOne(debitCredit.getAccountNumber());
		accnt.setAmount(debitCredit.getAmount());
		accnt.setBalance(accnt.getBalance() + accnt.getAmount());
		accnt.setLastChangeTs(new Date());
		accountRepository.save(accnt);
		transactionService.createTransaction(accnt, transId);
		return accnt;
	}

	@Transactional
	public Account withdrawMoney(DebitCreditTO debitCredit, String transId) {
		Account accnt = accountRepository.findOne(debitCredit.getAccountNumber());
		accnt.setAmount(debitCredit.getAmount());
		accnt.setBalance(accnt.getBalance() - accnt.getAmount());
		accnt.setLastChangeTs(new Date());
		accountRepository.save(accnt);
		transactionService.createTransaction(accnt, transId);
		return accnt;
	}

	@Transactional
	public Account makePayment(DebitCreditTO debitCredit, int accntNbr) {

		double amount = debitCredit.getAmount();
		String transId = UUID.randomUUID().toString();

		Account creditAccnt = accountRepository.findOne(debitCredit.getAccountNumber());
		DebitCreditTO creditAccountTO = new DebitCreditTO();
		creditAccountTO.setAccountNumber(creditAccnt.getAccountNumber());
		creditAccountTO.setAmount(amount);
		addMoney(creditAccountTO, transId);
		
		
		Account debitAccnt = accountRepository.findOne(accntNbr);
		double debitAmount = amount * creditAccnt.getCurrency().getValue() / debitAccnt.getCurrency().getValue();
		DebitCreditTO debitAccntTO = new DebitCreditTO();
		creditAccountTO.setAccountNumber(debitAccnt.getAccountNumber());
		debitAccntTO.setAmount(debitAmount);
		withdrawMoney(debitAccntTO, transId);

		
		Account payPalAccnt = accountRepository.findOne(Constants.paypalAccount);
		double transCharge = amount * Constants.TRANSACTION_CHARGE_PERCENT / 100;
		double ppAmount = transCharge * creditAccnt.getCurrency().getValue() / payPalAccnt.getCurrency().getValue();
		DebitCreditTO payPalAccntTO = new DebitCreditTO();
		creditAccountTO.setAccountNumber(payPalAccnt.getAccountNumber());
		payPalAccntTO.setAmount(ppAmount);
		addMoney(payPalAccntTO, transId);

		return debitAccnt;
	}

	public Account getBalance(int accntNbr) {
		return accountRepository.findOne(accntNbr);
	}

}
