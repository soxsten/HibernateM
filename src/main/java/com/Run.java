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
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.stream.Collectors;

import static com.DataUtil.getListOf;

public class Run {
    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
//        task21();
//        task2223();
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

    static List<DynamicMicrophoneDto> task2223() {
        List<Microphone> microphones = getListOf(Microphone.class);
        List<Microphone> list = microphones.stream()
                .filter(microphone -> microphone instanceof DynamicMicrophone)
                .collect(Collectors.toList());
        return mapper.convertValue(list,
                new TypeReference<List<DynamicMicrophoneDto>>() {
                });
    }

    static List<EasyCarDto> task24() {
        StopWatch timer = new StopWatch();
        timer.start();
        DataUtil util = new DataUtil();
        util.task24();
        timer.stop();
        System.out.println(timer.getTotalTimeSeconds());
        return util.task24();
    }
}
