package com;

import com.dto.CarDto;
import com.dto.DynamicMicrophoneDto;
import com.entity.DynamicMicrophone;
import com.entity.EasyCar;
import com.entity.Microphone;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;

import java.util.List;
import java.util.stream.Collectors;

import static com.DataUtil.getListOf;

public class Run {
    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        task21();
        task22();
        // TODO: 07.06.2019 Сделать выборку с помощью JDBC

    }

    private static List<CarDto> task21() {
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

    private static List<DynamicMicrophoneDto> task22() {
        List<Microphone> microphones = getListOf(Microphone.class);
        List<Microphone> list = microphones.stream()
                .filter(microphone -> microphone instanceof DynamicMicrophone)
                .collect(Collectors.toList());
        return mapper.convertValue(list,
                new TypeReference<List<DynamicMicrophoneDto>>() {
                });
    }
}
