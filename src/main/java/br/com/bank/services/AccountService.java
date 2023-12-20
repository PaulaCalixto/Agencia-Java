package br.com.bank.services;

import br.com.bank.persistence.dao.AccountDao;
import br.com.bank.persistence.dto.AccountDto;
import br.com.bank.persistence.model.Account;
import br.com.bank.persistence.model.AccountType;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class AccountService {

    @Inject
    AccountDao accountDao;

    public void deposit(Long accountId, Double valor) {
        Optional<Account> optionalAccount = accountDao.get(accountId);
        optionalAccount.ifPresent(account -> {
            account.depositar(valor);
            accountDao.update(account);
        });
    }

    public void withdraw(Long accountId, Double valor) {
        Optional<Account> optionalAccount = accountDao.get(accountId);
        optionalAccount.ifPresent(account -> {
            account.sacar(valor);
            accountDao.update(account);
        });
    }

    public void save(Account account) {
        accountDao.save(account);
    }

    public List<AccountDto> getByUserIdAndType(Long userId, AccountType tipoConta) {
        List<Account> accounts = accountDao.getByUserIdAndType(userId, tipoConta);
        return accounts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<AccountDto> getAccountDto(Long accountId) {
        Optional<Account> optionalAccount = accountDao.get(accountId);
        return optionalAccount.map(this::convertToDto);
    }

    private AccountDto convertToDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setTipoConta(account.getTipoConta());
        accountDto.setSaldo(account.getSaldo());
        accountDto.setUserId(account.getUsuario().getId());

        return accountDto;
    }
}
