package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import java.util.List;




public class UserDaoHibernateImpl implements UserDao {

    private Session session = Util.getSessionFactory().openSession();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try {
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (Id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(40), lastname VARCHAR(40), age INT)")
                    .executeUpdate();
            
        } catch (HibernateException e) {
        }

    }

    @Override
    public void dropUsersTable() {
        try {
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            
        } catch (HibernateException e) {
        }
    }

    @Override

    public void saveUser(String name, String lastName, byte age) {
        try {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
            List<User> users;
            users = session.createQuery("from User ").list();
            return users;
    }

    @Override
    public void cleanUsersTable() {
        try {
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE users").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
    }
}
