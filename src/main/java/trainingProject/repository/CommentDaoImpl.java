package trainingProject.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import trainingProject.model.Comment;
import trainingProject.model.Recipe;
import trainingProject.model.User;
import trainingProject.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Comment save(Comment comment) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(comment);
            transaction.commit();
            session.close();
            return comment;
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("failure to insert record", e);
            session.close();
            return null;
        }
    }

    @Override
    public List<Comment> getComments() {
        String hql = "FROM Comment";
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Comment> result = new ArrayList<>();
        try {
            Query<Comment> query = session.createQuery(hql);
            result = query.list();
            session.close();
        }
        catch(HibernateException e) {
            logger.error("failure to retrieve data record", e);
            session.close();
        }
        return result;
    }

    @Override
    public Comment getBy(Long id) {
        String hql = "FROM Comment c where c.id = :Id";
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Comment> query = session.createQuery(hql);
            query.setParameter("Id", id);
            Comment result = query.uniqueResult();
            session.close();
            return result;
        } catch (HibernateException e) {
            logger.error("failure to retrieve data record",e);
            session.close();
            return null;
        }
    }

    @Override
    public boolean delete(Comment comment) {
        String hql = "DELETE Comment as com WHERE com.id = :Id ";
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        int deletedCount = 0;
        try {
            transaction = session.beginTransaction();
            Query<Comment> query = session.createQuery(hql);
            query.setParameter("Id", comment.getId());
            deletedCount = query.executeUpdate();
            transaction.commit();
            session.close();
            return deletedCount >= 1 ? true : false;
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            session.close();
            logger.error("unable to delete record", e);
        }
        return false;
    }

    //select * from comments as c left join recipes as r on r.id = ?
    @Override
    public List<Comment> getBy(Recipe recipe) {
        String hql = "From Comment as c LEFT JOIN FETCH c.recipe as r where r.id = :Id";
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Comment> query = session.createQuery(hql);
            query.setParameter("Id", recipe.getId());
            List<Comment> result = query.list();
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
    public List<Comment> getBy(User user) {
        String hql = "From Comment as c LEFT JOIN FETCH c.user as u where u.id = :Id";
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Comment> query = session.createQuery(hql);
            query.setParameter("Id", user.getId());
            List<Comment> result = query.list();
            session.close();
            return result;
        }
        catch (HibernateException e) {
            logger.error("fail to retrieve data", e);
            session.close();
            return null;
        }
    }

}
