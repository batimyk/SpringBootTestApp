package ua.batimyk.springboot.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by N on 02-Oct-16.
 * SpringBootTestApp
 */
@Repository
@Transactional
public class FeatureDAO {

    public void create(Feature feature) throws SQLException {
        getSession().save(feature);
    }

    @SuppressWarnings("unchecked")
    public List<Feature> getAll() {
        return (List<Feature>) getSession().createQuery("from Feature").list();
    }

    public void update(Feature feature) throws SQLException {
        getSession().update(feature);
    }

    public void delete(Feature feature) {
        getSession().delete(feature);
    }

    public Feature getByRenderingEngine(String renderingEngine) {
        return (Feature) getSession().createQuery(
                "from features where rendering_engine = :rendering_engine")
                .setParameter("rendering_engine", renderingEngine)
                .uniqueResult();
    }

    public Feature getById(long id) {
        Feature feature;
        try {
             feature = (Feature) getSession().get(Feature.class, id);
        }
        catch (Exception e){
            return null;
        }
        return feature;
    }

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

}
