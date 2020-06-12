package trainingProject.repository;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import trainingProject.model.User;
import trainingProject.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public User save(User user) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            session.close();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("failure to insert record", e);
            session.close();
            return null;
        }
    }

    @Override
    public List<User> getUsers() {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM User";
        List<User> result = new ArrayList<>();
        try {
            Query query = session.createQuery(hql);
            result = query.list();
            session.close();
        }
        catch (Exception e) {
            logger.error("failure to retrieve data record", e);
            session.close();
        }
        return result;
    }

    @Override
    public User getBy(Long userId) {
        return null;
    }

    @Override
    public boolean delete(User user) {
        String hql = "DELETE FROM User WHERE userId = :Id";
        int deletedCount = 0;
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Query<User> query = session.createQuery(hql);
            query.setParameter("Id", user.getUserId());
            deletedCount = query.executeUpdate();
            transaction.commit();
            session.close();
            return deletedCount >= 1 ? true : false;
        }
        catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            session.close();
            logger.error("unable to delete record", e);
        }
        return false;
    }

    @Override
    public User getUserEagerBy(Long id) {
        return null;
    }
}
