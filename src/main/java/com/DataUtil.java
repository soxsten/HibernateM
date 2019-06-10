package com;

import com.dto.EasyCarDto;
import com.entity.EasyCar;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.Constants.Credentials.*;

@SuppressWarnings("unchecked")
@Component
class DataUtil {
    static <T> List<T> getListOf(Class<T> clazz) {
        List<T> authorList;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery(clazz);
            Root<T> root = cq.from(clazz);
            Query query = session.createQuery(cq);
            authorList = query.getResultList();
        }
        return authorList;
    }

    List<EasyCarDto> task24() {
        ObjectMapper mapper = new ObjectMapper();
        List<EasyCar> cars = new ArrayList<>();
        String query = "select id, created, updated, name, description from easycar";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                Date created = resultSet.getDate(2);
                Date updated = resultSet.getDate(3);
                String name = resultSet.getString(4);
                String description = resultSet.getString(5);

                EasyCar car = new EasyCar();
                car.setId(id);
                car.setCreated(created);
                car.setUpdated(updated);
                car.setName(name);
                car.setDescription(description);
                cars.add(car);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return mapper.convertValue(cars,
                new TypeReference<List<EasyCarDto>>() {
                });
    }
}
