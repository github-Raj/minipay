package com.paypal.minipay.service;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.minipay.commons.CURRENCY;
import com.paypal.minipay.commons.Constants;
import com.paypal.minipay.commons.Usertype;
import com.paypal.minipay.entity.Account;
import com.paypal.minipay.entity.User;
import com.paypal.minipay.repository.UserRepository;
import com.paypal.minipay.to.UserTO;

@Service
public class UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserRepository<User> userRepository;

	@Autowired
	AccountService accountService;

	public User save(UserTO userTO) {
		User user = null;
		try {
			user = new ModelMapper().map(userTO, User.class);
			Date dt = new Date();
			user.setCreateTs(dt);
			user.setLastChangeTs(dt);
			user.setType(Usertype.getByDesc(userTO.getType()));

			Account account = new Account();
			account.setAccountNumber(1234);
			account.setCurrency(CURRENCY.getByName(userTO.getCurrency()));
			account.setCreateTs(dt);
			account.setLastChangeTs(dt);
			account.setUser(user);
			user.setAccount(account);
			userRepository.save(user);
			accountService.save(account);
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw e;
		}
		return user;

	}

	// @PostConstruct
	public void createAdminUser() {
		UserTO userTO = new UserTO();
		userTO.setUserId("paypal");
		userTO.setEmail("paypal@paypal.com");
		userTO.setName("paypal");
		userTO.setPassword("paypal@123");
		userTO.setType(Usertype.SELLER.toString());
		userTO.setCurrency(CURRENCY.INR.toString());

		User paypalUser = save(userTO);
		Constants.paypalAccount = paypalUser.getAccount().getAccountNumber();
	}

}
