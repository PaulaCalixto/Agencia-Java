package br.com.bank.persistence.dao;

import br.com.bank.persistence.model.User;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserDao implements Dao<User> {

    @Inject
    EntityManager em;

    @Override
    public User get() {
        return null;
    }

    @Override
    public void save(User user) {
        this.em.persist(user);
    }

    @Override
    public void update(User user) {
        em.merge(user);
    }

    @Override
    public void delete(Long id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }

    @Override
    public List<User> getAll() {
        return em.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    public Optional<User> get(Long id) {
        User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }
}
