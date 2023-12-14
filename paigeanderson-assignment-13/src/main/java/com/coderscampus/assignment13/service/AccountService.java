package com.coderscampus.assignment13.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.repository.AccountRepository;



@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepo;
	@Autowired
	private UserService userService;
	

	public Account findById(Long accountId) {
		Optional<Account> accountOpt = accountRepo.findById(accountId);
		return accountOpt.orElse(new Account());
	}

	public void saveAccount(Account account) {
		accountRepo.save(account);
	}

	public Account addAccount(long userId) {
		User user = userService.findById(userId);
		Account account = new Account();
		
		user.getAccounts().add(account);
		account.getUsers().add(user);
		account.setAccountName("Account" + user.getAccounts().size());
		
		return accountRepo.save(account);
	}
	
}