package com.paypal.minipay.controller;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.minipay.entity.Account;
import com.paypal.minipay.service.AccountService;
import com.paypal.minipay.to.DebitCreditTO;

@RestController
@Path("/account/")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class AccountController {

	@Autowired
	AccountService accountService;

	@PUT
	@Path("/add/")
	public Response addMoney(DebitCreditTO debitCredit) {
		Account savedAccnt = null;
		try {
			savedAccnt = accountService.addMoney(debitCredit, UUID.randomUUID().toString());
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		return Response.ok().entity(savedAccnt).build();

	}

	@PUT
	@Path("/withdraw/")
	public Response withdrawMoney(DebitCreditTO debitCredit) {
		Account savedAccnt = null;
		try {
			savedAccnt = accountService.withdrawMoney(debitCredit, UUID.randomUUID().toString());
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		return Response.ok().entity(savedAccnt).build();

	}
	
	@PUT
	@Path("/payment/")
	public Response makePayment(@PathParam(value = "accntNbr") int accntNbr, DebitCreditTO debitCredit) {
		Account Accnt = null;
		try {
			Accnt = accountService.makePayment(debitCredit, accntNbr);
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		return Response.ok().entity(Accnt).build();

	}
	
	@GET
	public Response makePayment(@PathParam(value = "accntNbr") int accntNbr) {
		Account Accnt = null;
		try {
			Accnt = accountService.getBalance(accntNbr);
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		return Response.ok().entity(Accnt).build();

	}

}
