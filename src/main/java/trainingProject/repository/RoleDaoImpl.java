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
import trainingProject.model.Role;
import trainingProject.model.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao{
    @Autowired
    private SessionFactory sessionFactory;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Role getRoleByName(String name) {
        String hql = "FROM Role as r where lower(r.name) = :Name";
        Session session = sessionFactory.openSession();
        try {
            Query<Role> query = session.createQuery(hql);
            query.setParameter("Name", name);
            Role result = query.uniqueResult();
            session.close();
            return result;
        } catch (HibernateException e) {
            logger.error("failure to retrieve data record",e);
            session.close();
            return null;
        }
    }

    @Override
    public List<Role> findAllRoles() {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        String hql = "FROM Role";
        List<Role> result = new ArrayList<>();
        try {
            Query query = session.createQuery(hql); //Query<Role> = ...
            result = query.list();
            session.close();
        }
        catch (Exception e) {
            logger.error("failure to retrieve data record", e);
            session.close();
        }
        return result;
    }
}
