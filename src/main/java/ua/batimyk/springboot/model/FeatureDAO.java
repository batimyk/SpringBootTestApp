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

    public Feature getById(long id) {
        return getSession().get(Feature.class, id);
    }


    public List<Object> getByRenderingEngine() {

        return getSession().createSQLQuery(
                "SELECT \n" +
                        "    rendering_engine as rendering_engine,\n" +
                        "    GROUP_CONCAT(DISTINCT browser\n" +
                        "        SEPARATOR ', ') as browser,\n" +
                        "    GROUP_CONCAT(DISTINCT platforms\n" +
                        "        SEPARATOR ', ') as platforms,\n" +
                        "    GROUP_CONCAT(DISTINCT engine_version\n" +
                        "        SEPARATOR ', ') as engine_versions,\n" +
                        "    GROUP_CONCAT(DISTINCT css_grade\n" +
                        "        SEPARATOR ', ') as css_grades\n" +
                        "FROM\n" +
                        "    Features\n" +
                        "GROUP BY rendering_engine")
                .list();
    }


    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

}
