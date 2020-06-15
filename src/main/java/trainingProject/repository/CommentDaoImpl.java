package trainingProject.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import trainingProject.model.Comment;
import trainingProject.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

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
    public Comment getBy(Long commentId) {
        return null;
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

    @Override
    public Comment getCommentEagerBy(Long id) {
        return null;
    }
}
