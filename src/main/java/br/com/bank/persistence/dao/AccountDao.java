package br.com.bank.persistence.dao;

import br.com.bank.persistence.model.Account;
import br.com.bank.persistence.model.AccountType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AccountDao implements Dao<Account> {

    @Inject
    EntityManager em;

    public AccountDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Account account) {
        this.em.persist(account);
    }

    @Override
    public void update(Account account) {
        em.merge(account);
    }

    @Override
    public void delete(Long id) {
        Account account = em.find(Account.class, id);
        em.remove(account);
    }

    @Override
    public List<Account> getAll() {
        return em.createQuery("SELECT a FROM Account a", Account.class)
                .getResultList();
    }

    @Override
    public Optional<Account> get(Long id) {
        Account account = em.find(Account.class, id);
        return Optional.ofNullable(account);
    }

    public List<Account> getByUserIdAndType(Long userId, AccountType tipoConta) {
        return em.createQuery("SELECT a FROM Account a WHERE a.usuario.id = :userId AND a.tipoConta = :tipoConta", Account.class)
                .setParameter("userId", userId)
                .setParameter("tipoConta", tipoConta)
                .getResultList();
    }
}
