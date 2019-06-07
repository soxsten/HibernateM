package com;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
class DataUtil {
    static <T> List<T> getListOf(Class<T> clazz) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(clazz);
        Root<T> root = cq.from(clazz);
        Query query = session.createQuery(cq);
        List<T> authorList = query.getResultList();
        session.close();
        return authorList;
    }
}
