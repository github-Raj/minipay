package com.paypal.minipay.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.minipay.commons.CURRENCY;
import com.paypal.minipay.commons.Usertype;
import com.paypal.minipay.entity.Account;
import com.paypal.minipay.entity.User;
import com.paypal.minipay.service.UserService;
import com.paypal.minipay.to.UserTO;

@RestController
@Path("/user/")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class UserController {

	@Autowired
	UserService userService;

	@POST
	public Response save(UserTO userTO) {

		User savedUser = null;
		try {
			savedUser = userService.save(userTO);
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		return Response.ok().entity(savedUser).build();

	}
	

}
