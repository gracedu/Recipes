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
import trainingProject.model.User;

import java.util.ArrayList;
import java.util.List;

//TODO TEST
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
        return null;
    }

    @Override
    public boolean delete(Image image) {
        return false;
    }

    @Override
    public List<Image> getBy(User user) {
        return null;
    }
}
