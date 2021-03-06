package trainingProject.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import trainingProject.model.Role;
import trainingProject.model.User;
import trainingProject.service.RoleService;
import trainingProject.util.HibernateUtil;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private RoleService roleService;

    @Override
    public User save(User user) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            if (roleService.getRoleByName("user") == null) {
                Role role = new Role();
                role.setName("user");
                role.setAllowedResource("/recipes,/comments,/images");
                role.setAllowedRead(true);
                role.setAllowedUpdate(true);
                role.setAllowedCreate(true);
                role.setAllowedDelete(true);
                roleService.save(role);
            }
            user.addRole(roleService.getRoleByName("user"));
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
        Session session = sessionFactory.openSession();
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
    public User getBy(Long id) {
        String hql = "FROM User u where u.id = :Id";
        Session session = sessionFactory.openSession();
        try {
            Query<User> query = session.createQuery(hql);
            query.setParameter("Id", id);
            User result = query.uniqueResult();
            session.close();
            return result;
        } catch (HibernateException e) {
            logger.error("failure to retrieve data record",e);
            session.close();
            return null;
        }
    }

    @Override
    public boolean delete(User user) {
        String hql = "DELETE FROM User WHERE id = :Id";
        int deletedCount = 0;
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            Query<User> query = session.createQuery(hql);
            query.setParameter("Id", user.getId());
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

    //based on table recipes(user_id), return the user who publishes the recipe
    //select * from users as u left join recipes as r on u.id = r.user_id
    @Override
    public User getUserEagerByRecipe(Long id) {
        String hql = "FROM User u LEFT JOIN FETCH u.recipes where u.id = :Id";
        Session session = sessionFactory.openSession();
        try {
            Query<User> query = session.createQuery(hql);
            query.setParameter("Id", id);
            User result = query.uniqueResult();
            session.close();
            return result;
        }
        catch (HibernateException e) {
            logger.error("failure to retrieve data record", e);
            session.close();
            return null;
        }
    }

    //based on table comments(user_id), return the user who publishes the comment
    @Override
    public User getUserEagerByComment(Long id) {
        String hql = "FROM User u LEFT JOIN FETCH u.comments where u.id = :Id";
        Session session = sessionFactory.openSession();
        try {
            Query<User> query = session.createQuery(hql);
            query.setParameter("Id", id);
            User result = query.uniqueResult();
            session.close();
            return result;
        }
        catch (HibernateException e) {
            logger.error("fail to retrieve data", e);
            session.close();
            return null;
        }
    }

    @Override
    public User update(User user) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            return user;
        }
        catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("fail to update record", e.getMessage());
            return null;
        }
    }

    @Override
    public User getUserByCredentials(String email, String password){
        String hql = "From User as u where (lower(u.email) = :email or lower(u.name) = :email) and u.password = :password";
        logger.debug(String.format("User email: %s, password: %s", email, password));
        Session session = sessionFactory.openSession();
        try {
            Query<User> query = session.createQuery(hql);
            query.setParameter("email", email.toLowerCase().trim());
            query.setParameter("password", password);
            User result = query.uniqueResult();
            session.close();
            return result;
        }
        catch (Exception e) {
            logger.error("can't find user based on given credential", e.getMessage());
            session.close();
            return null;
        }
    }
}
