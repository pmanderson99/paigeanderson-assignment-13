package com.coderscampus.assignment13.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.service.AccountService;
import com.coderscampus.assignment13.service.UserService;

@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private UserService userService;
	
	 @PostMapping("/user/{userId}/accounts")
	    public String postCreateAccount(@PathVariable Long userId, ModelMap model) {
	        

	        return "redirect:/user/" + userId + "/accounts/" + accountService.findAccountById();
	    }

	    @GetMapping("/user/{userId}/accounts/{accountId}")
	    public String getUserAccount(@PathVariable Long userId, @PathVariable Long accountId, ModelMap model) {
	     
	        return "accounts";
	    }

	    @PostMapping("user/{userId}/accounts/{accountId}")
	    public String changeUserAccountName(@PathVariable Long userId, @PathVariable Long accountId, Account account) {
	       

	        return "redirect:/user/" + userId + "/accounts/" + accountId;
	    }
	}
