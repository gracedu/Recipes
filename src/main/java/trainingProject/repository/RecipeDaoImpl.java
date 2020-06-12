package trainingProject.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import trainingProject.model.Recipe;
import trainingProject.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class RecipeDaoImpl implements RecipeDao {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Recipe save(Recipe recipe) {
        Transaction transaction = null;  //transaction for DAO
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.save(recipe);
            transaction.commit();
            session.close();
            return recipe;

        }
        catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("failure to insert record", e);
            session.close();
            return null;
        }
    }

    @Override
    public List<Recipe> getRecipes() {
        String hql = "FROM Recipe"; //SELECT r FROM Recipe r
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.openSession();
        List<Recipe> result = new ArrayList<>();
        try {
            Query query = s.createQuery(hql);
            result = query.list();
            s.close();
        }
        catch (HibernateException e) {
            logger.error("failure to retrieve data record", e);
            s.close();
        }
        return result;
    }

    @Override
    public Recipe getBy(Long recipeId) {
        return null;
    }

    @Override
    public boolean delete(Recipe recipe) {
        String hql = "DELETE Recipe as rec where rec.id = :Id"; //:Id is a placeholder
        int deletedCount = 0;
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            Query<Recipe> query = session.createQuery(hql);
            query.setParameter("Id", recipe.getId());
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
    public Recipe getRecipeEagerBy(Long id) {
        String hql = "";
        return null;

    }
}