package com;

import com.dto.CarDto;
import com.dto.DynamicMicrophoneDto;
import com.dto.EasyCarDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.DataUtil;
import com.util.HibernateUtil;
import com.util.SchemaGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.util.Constants.HIBERNATE_TEST_CONFIG_XML;
import static org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class RunTest {

    @BeforeAll
    void setUp() {
        HibernateUtil.setupConfig(HIBERNATE_TEST_CONFIG_XML);
        SchemaGenerator.main(new String[]{HIBERNATE_TEST_CONFIG_XML});
    }

    @Test
    void should_return_expected_carDtos() throws IOException {
        //given
        ObjectMapper mapper = new ObjectMapper();
        File resourcePath = new File("src/test/resources/run-test/task21-cardto-list.json");
        List<CarDto> expected = mapper.readValue(resourcePath, new TypeReference<List<CarDto>>() {
        });

        //when
        List<CarDto> actual = new DataUtil().task21();

        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void should_return_expected_dynamicMicrophones() throws IOException {
        //given
        ObjectMapper mapper = new ObjectMapper();
        File resourcePath = new File("src/test/resources/run-test/task22-dynamicMicrophoneDto-list.json");
        List<DynamicMicrophoneDto> expected = mapper.readValue(resourcePath, new TypeReference<List<DynamicMicrophoneDto>>() {
        });

        //when
        List<DynamicMicrophoneDto> actual = new DataUtil().task2223();

        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void should_return_expected_easycar_list() throws IOException {
        //given
        ObjectMapper mapper = new ObjectMapper();
        File resourcePath = new File("src/test/resources/run-test/task24-easycar-list.json");
        List<EasyCarDto> expected = mapper.readValue(resourcePath, new TypeReference<List<EasyCarDto>>() {
        });

        //when
        List<EasyCarDto> actual = new DataUtil().task24();

        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}