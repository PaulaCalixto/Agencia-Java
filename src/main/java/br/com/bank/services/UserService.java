package br.com.bank.services;

import br.com.bank.exceptions.NotFoundException;
import br.com.bank.persistence.dao.UserDao;
import br.com.bank.persistence.dto.AccountDto;
import br.com.bank.persistence.dto.UserDto;
import br.com.bank.persistence.model.Account;
import br.com.bank.persistence.model.User;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserService {

    @Inject
    UserDao userDao;

    @Inject
    AccountService accountService;

    public void createAccount(AccountDto accountData) {
        Optional<User> optionalUser = userDao.get(accountData.getUserId());

        User user = optionalUser.orElseGet(() -> createUser(accountData));

        createAccountForUser(user, accountData);
    }

    private User createUser(AccountDto accountData) {
        User newUser = new User();
        newUser.setNome(accountData.getNome());
        newUser.setIdade(accountData.getIdade());
        newUser.setTelefone(accountData.getTelefone());
        newUser.setEndereco(accountData.getEndereco());
        userDao.save(newUser);
        return newUser;
    }

    private void createAccountForUser(User user, AccountDto accountData) {
        Account newAccount = new Account(user);
        newAccount.setTipoConta(accountData.getTipoConta());
        newAccount.setSaldo(0.0);
        accountService.save(newAccount);
    }

    public void deposit(Long accountId, Double valor) {
        accountService.deposit(accountId, valor);
    }

    @Transactional
    public void withdraw(Long accountId, Double valor) {
        accountService.withdraw(accountId, valor);
    }

    public List<AccountDto> listAccounts(Long userId) {
        return accountService.getByUserIdAndType(userId, null);
    }

    public UserDto getUserDetails(Long userId) {
        Optional<User> optionalUser = userDao.get(userId);
        return optionalUser.map(this::convertToDto)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado com o ID: " + userId));
    }

    public AccountDto getAccountDetails(Long accountId) {
        return accountService.getAccountDto(accountId).orElse(null);
    }

    private UserDto convertToDto(User user) {
        List<AccountDto> accounts = accountService.getByUserIdAndType(user.getId(), null);

        return new UserDto(user.getNome(), user.getIdade(), user.getTelefone(), user.getEndereco(), accounts);
    }
}
