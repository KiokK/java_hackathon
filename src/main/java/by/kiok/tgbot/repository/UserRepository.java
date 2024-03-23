package by.kiok.tgbot.repository;

import by.kiok.tgbot.model.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Transactional
public class UserRepository {

    private final SessionFactory sessionFactory;

    public Optional<User> create(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);

        return Optional.ofNullable(user);
    }

    public Optional<User> findUserByUsername(String username) throws EntityNotFoundException {
        Session session = sessionFactory.getCurrentSession();
        User user = session.createQuery("SELECT u FROM User u WHERE u.username = ?1", User.class)
                .setParameter(1, username).getSingleResultOrNull();

        return Optional.ofNullable(user);
    }

    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public List<User> findAllNotLessThatCoursePercentage(int coursePercentage) {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("SELECT u FROM User u WHERE u.coursePercentage >= ?1", User.class)
                .setParameter(1, coursePercentage).getResultList();
    }

    public Optional<User> updateByUsername(String username, User user) {
        Session session = sessionFactory.getCurrentSession();
        User findUser = session.createQuery("SELECT u FROM User u WHERE u.username = ?1", User.class)
                .setParameter(1, username).getSingleResultOrNull();
        if (findUser == null) {

            return Optional.empty();
        }

        return Optional.ofNullable(session.merge(user));
    }

    public boolean deleteByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.createQuery("SELECT u FROM User u WHERE u.username = ?1", User.class)
                .setParameter(1, username).getSingleResultOrNull();
        if (user == null) {
            return false;
        }
        session.remove(user);

        return true;
    }
}
