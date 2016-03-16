package api;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

/**
 *
 * @author ChristopherBorum
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(String json) {
        EntityManager em = getEntityManager();
        T entity = new Gson().fromJson(json, entityClass);
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    protected abstract EntityManager getEntityManager();

    public String findAll() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        List res = getEntityManager().createQuery(cq).getResultList();
        return new Gson().toJson(res);
    }

    public String findAllCriteria(String columnField, Object object) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root sm = cq.from(entityClass);
        cq.where(cb.equal(sm.get(columnField), object));
        List res = getEntityManager().createQuery(cq).getResultList();
        return new Gson().toJson(res);
    }
    
    public String findAllColumns(String values) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root sm = cq.from(entityClass);
        String[] value = values.split(",");
        List<Selection> selectionList = new ArrayList();
        for (String s : value) {
            selectionList.add(sm.get(s));
        }
        cq.multiselect(selectionList);
        List res = getEntityManager().createQuery(cq).getResultList();
        return new Gson().toJson(res);
    }
    
    public String findAllColumnsId(String values, int id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root sm = cq.from(entityClass);
        String[] value = values.split(",");
        List<Selection> selectionList = new ArrayList();
        for (String s : value) {
            selectionList.add(sm.get(s));
        }
        cq.multiselect(selectionList).where(cb.equal(sm.get("id"), id));
        List res = getEntityManager().createQuery(cq).getResultList();
        return new Gson().toJson(res);
    }

    public String find(int id) {
        return new Gson().toJson(getEntityManager().find(entityClass, id));
    }

}
