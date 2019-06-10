package com;

import com.dto.CarDto;
import com.dto.DynamicMicrophoneDto;
import com.dto.EasyCarDto;
import com.entity.DynamicMicrophone;
import com.entity.EasyCar;
import com.entity.Microphone;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.Constants.Credentials.*;
import static com.DataUtil.getListOf;

public class Run {
    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        task21();
        task22();
        task24();
    }

    static List<CarDto> task21() {
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

    static List<DynamicMicrophoneDto> task22() {
        List<Microphone> microphones = getListOf(Microphone.class);
        List<Microphone> list = microphones.stream()
                .filter(microphone -> microphone instanceof DynamicMicrophone)
                .collect(Collectors.toList());
        return mapper.convertValue(list,
                new TypeReference<List<DynamicMicrophoneDto>>() {
                });
    }

    static List<EasyCarDto> task24() {
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
