package repository;

import domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;

    @Inject
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void register(User user) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(user);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        this.entityManager.getTransaction().begin();
        User user = this.entityManager.createQuery("select u from User u " +
                "where u.username= :username and u.password= :password", User.class)
                .setParameter("username", username)
                .setParameter("password", password)
                .getSingleResult();
        this.entityManager.getTransaction().commit();

        return user;

    }
}
