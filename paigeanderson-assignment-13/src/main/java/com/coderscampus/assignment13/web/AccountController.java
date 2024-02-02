package com.coderscampus.assignment13.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	@GetMapping("users/{userId}/accounts/{accountId}")
	public String getOneAccount (ModelMap model, @PathVariable Long userId, @PathVariable Long accountId) {
		User user = userService.findById(userId);
		Account account = accountService.findAccountById(accountId);
		if(account.getAccountId()== null) {
			userService.addNewUserAccount(account, user);
		}
		model.put("user", user);
		model.put("account", account);
		return "account";
	}
	
	@PostMapping("users/{userId}/accounts")
	public String createAccount(@PathVariable Long userId) {
		User user = userService.findById(userId);
		Account account = new Account();
		user.getAccounts().add(account);
		account.getUsers().add(user);
		account.setAccountName("Account #: " + user.getAccounts().size());
		accountService.saveAccount(account);
		return "redirect:/users/" + userId + "/accounts/" + account.getAccountId();
	}
	
	@PostMapping("users/{userId}/accounts/{accountId}") 
	public String updateAccountName (Account account, @PathVariable Long userId) {
		User user = userService.findById(userId);
		user = userService.saveUser(user);
		account = accountService.saveAccount(account);
		return "redirect:/users/" + userId + "/accounts/" + account.getAccountId();
	}
}