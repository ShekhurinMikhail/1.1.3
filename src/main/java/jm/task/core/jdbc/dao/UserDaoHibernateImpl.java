package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS `schema`.`users` (" +
                "`id` BIGINT NOT NULL AUTO_INCREMENT," +
                "`Name` VARCHAR(45) NULL," +
                "`LastName` VARCHAR(45) NULL," +
                "`Age` TINYINT NULL," +
                "PRIMARY KEY (`id`)," +
                "UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)";
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            NativeQuery sqlQuery = session.createSQLQuery(sql);
            sqlQuery.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS `schema`.`users`";
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            NativeQuery sqlQuery = session.createSQLQuery(sql);
            sqlQuery.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO `schema`.`users` " +
                "(Name, LastName, Age) " +
                "VALUES(?, ?, ?)";
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            NativeQuery sqlQuery = session.createSQLQuery(sql);
            sqlQuery.setParameter(1, name);
            sqlQuery.setParameter(2, lastName);
            sqlQuery.setParameter(3, age);
            sqlQuery.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM schema.users WHERE id=:id";
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            NativeQuery sqlQuery = session.createSQLQuery(sql);
            sqlQuery.setParameter("id", id);
            sqlQuery.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> listUser = new ArrayList<>();
        String sql = "SELECT Name, lastName, Age FROM schema.users";
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            List<Object[]> resultList = session.createNativeQuery(sql).getResultList();
            for (Object[] object : resultList) {
                User user = new User((String) object[0], (String) object[1], (Byte) object[2]);
                listUser.add(user);
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
        return listUser;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE schema.users";
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            NativeQuery sqlQuery = session.createSQLQuery(sql);
            sqlQuery.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }
}
