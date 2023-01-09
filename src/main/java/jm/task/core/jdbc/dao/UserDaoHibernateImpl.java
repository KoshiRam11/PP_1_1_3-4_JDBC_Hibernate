package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()){
            transaction=session.beginTransaction();
            String query = "create table if not exists userstable (id INT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(30), lastName VARCHAR(35), age INT);";
            session.createSQLQuery(query).executeUpdate();
            transaction.commit();
            System.out.println("Table created");
        }catch (HibernateException e){
            if (transaction!=null){
                transaction.rollback();
            }
        }

    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()){
            transaction=session.beginTransaction();
            String query = "drop table if exists userstable";
            session.createSQLQuery(query).executeUpdate();
            transaction.commit();
            System.out.println("Table deleted");
        }catch (HibernateException e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            User user = new User(name,lastName,age);
            session.save(user);
            transaction.commit();
            System.out.println("User saved");
        }catch (HibernateException e){
            if (transaction!=null){
                transaction.rollback();
            }
        }

    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction =null;
        try (Session session = Util.getSessionFactory().openSession()){
            transaction=session.beginTransaction();
            User user = session.load(User.class,id);
            session.delete(user);
            transaction.commit();
            System.out.println("User delete");
        }catch (HibernateException e){
            if (transaction!=null){
                transaction.rollback();
            }
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()){
            transaction=session.beginTransaction();
            result = session.createQuery("from User",User.class).list();
            session.getTransaction().commit();
        }catch (HibernateException e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return result;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()){
            transaction=session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();
        }catch (HibernateException e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
    }
}
