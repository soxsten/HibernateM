package com.util;

import com.dto.CarDto;
import com.dto.DynamicMicrophoneDto;
import com.dto.EasyCarDto;
import com.entity.DynamicMicrophone;
import com.entity.EasyCar;
import com.entity.Microphone;
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
import java.util.stream.Collectors;

import static com.util.Constants.Credentials.*;

@SuppressWarnings("unchecked")
@Component
public class DataUtil {
    private final ObjectMapper mapper = new ObjectMapper();

    private <T> List<T> getListOf(Class<T> clazz) {
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

    public List<CarDto> task21() {
        List<EasyCar> list;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            list = session
                    .createQuery("from EasyCar", EasyCar.class)
                    .getResultList();
        }
        return mapper.convertValue(list,
                new TypeReference<List<CarDto>>() {
                });
    }

    public List<DynamicMicrophoneDto> task2223() {
        List<Microphone> microphones = getListOf(Microphone.class);
        List<Microphone> list = microphones.stream()
                .filter(microphone -> microphone instanceof DynamicMicrophone)
                .collect(Collectors.toList());
        return mapper.convertValue(list,
                new TypeReference<List<DynamicMicrophoneDto>>() {
                });
    }

    public List<EasyCarDto> task24() {
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

    <E, F extends E> List<E> filterFor(Class<E> entityType, Class<F> filter) {
        List<E> microphones = this.getListOf(entityType);
        int middle = microphones.size() / 2;
        FilterTask<F, E> firstTask = new FilterTask<>(
                microphones.subList(0, middle), filter);
        firstTask.fork();

        FilterTask<F, E> secondTask = new FilterTask<>(
                microphones.subList(middle + 1, microphones.size() - 1), filter);
        List<E> secondTaskResult = secondTask.compute();
        List<E> firstTaskResult = firstTask.join();

        List<E> result = new ArrayList<>(firstTaskResult);
        result.addAll(secondTaskResult);
        return result;
    }
}
