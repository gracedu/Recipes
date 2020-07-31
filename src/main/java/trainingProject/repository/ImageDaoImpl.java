package trainingProject.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import trainingProject.model.Image;
import trainingProject.model.Recipe;
import trainingProject.model.User;

import java.util.ArrayList;
import java.util.List;


@Repository
public class ImageDaoImpl implements ImageDao {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Image save(Image image) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.save(image);
            transaction.commit();
            session.close();
            return image;
        }
        catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.debug("fail to save image", e);
            session.close();
        }
        return null;
    }

    @Override
    public List<Image> getImages() {
        Transaction transaction = null;
        String hql = "From Image";
        List<Image> images = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            images = query.list();
            transaction.commit();
            session.close();
            return images;

        }
        catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.error("fail to get images", e);
            session.close();
        }
        return null;
    }

    @Override
    public Image getBy(Long id) {
        Transaction transaction = null;
        String hql = "From Image i where i.id = :Id";
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            Query<Image> query = session.createQuery(hql);
            query.setParameter("Id", id);
            Image result = query.uniqueResult();
            transaction.commit();
            session.close();
            return result;
        }
        catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.error("fail to retrieve record", e);
            session.close();
        }
        return null;
    }

    @Override
    public boolean delete(Image image) {
        Transaction transaction = null;
        String hql = "DELETE FROM Image as ima where ima.id = :Id";
        Session session = sessionFactory.openSession();
        int deletedCount = 0;
        try {
            transaction = session.beginTransaction();
            Query<Image> query = session.createQuery(hql);
            query.setParameter("Id", image.getId());
            deletedCount = query.executeUpdate();
            transaction.commit();
            session.close();
            return deletedCount > 0 ? true : false;
        }
        catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.error("fail to delete the image", e);
            session.close();
        }
        return false;
    }

    //TODO add user-image mapping
    @Override
    public List<Image> getBy(User user) {
        Transaction transaction = null;
        String hql = "From Image ima LEFT JOIN FETCH ima.user as u where u.id = :Id";
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            Query<Image> query = session.createQuery(hql);
            query.setParameter("Id", user.getId());
            List<Image> result = query.list();
            transaction.commit();
            session.close();
            return result;
        }
        catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.error("fail to get images by user", e);
            session.close();
        }
        return null;
    }

    @Override
    public List<Image> getBy(Recipe recipe) {
        Transaction transaction = null;
        String hql = "From Image ima LEFT JOIN FETCH ima.recipe as u where u.id = :Id";
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            Query<Image> query = session.createQuery(hql);
            query.setParameter("Id", recipe.getId());
            List<Image> result = query.list();
            transaction.commit();
            session.close();
            return result;
        }
        catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.error("fail to get images by user", e);
            session.close();
        }
        return null;
    }
}
