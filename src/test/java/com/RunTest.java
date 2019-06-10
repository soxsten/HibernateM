package com;

import com.dto.CarDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.Constants.HIBERNATE_TEST_CONFIG_XML;

class RunTest {

    @BeforeEach
    void setUp() {
        HibernateUtil.setupConfig(HIBERNATE_TEST_CONFIG_XML);
    }

    @Test
    void should_return_expected_carDtos() throws IOException {
        //given
        SchemaGenerator.main(new String[]{HIBERNATE_TEST_CONFIG_XML});
        ObjectMapper mapper = new ObjectMapper();
        File resourcePath = new File("src/test/resources/task21-cardto-list.json");
        List<CarDto> expected = mapper.readValue(resourcePath, new TypeReference<List<CarDto>>() {
        });
        //when
        List<CarDto> actual = Run.task21();
        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}