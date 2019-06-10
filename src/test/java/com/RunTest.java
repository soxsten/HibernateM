package com;

import com.dto.CarDto;
import com.dto.DynamicMicrophoneDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.Constants.HIBERNATE_TEST_CONFIG_XML;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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
        List<CarDto> actual = Run.task21();
        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void should_return_expected_dynamicMicrophones() throws IOException {
        //given
        ObjectMapper mapper = new ObjectMapper();
        File resourcePath = new File("src/test/resources/run-test/task22-DynamicMicrophoneDto-list.json");
        List<DynamicMicrophoneDto> expected = mapper.readValue(resourcePath, new TypeReference<List<DynamicMicrophoneDto>>() {
        });

        //when
        List<DynamicMicrophoneDto> actual = Run.task22();

        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}