package com.util;

import com.dto.CondenserMicrophoneDto;
import com.dto.MicrophoneDto;
import com.entity.CondenserMicrophone;
import com.entity.Microphone;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.util.Constants.HIBERNATE_TEST_CONFIG_XML;

@TestInstance(Lifecycle.PER_CLASS)
class DataUtilTest {

    @BeforeAll
    void setUp() {
        HibernateUtil.setupConfig(HIBERNATE_TEST_CONFIG_XML);
        SchemaGenerator.main(new String[]{HIBERNATE_TEST_CONFIG_XML});
    }

    @Test
    void should_return_expected_condenser_dto_list() throws IOException {
        //given
        ObjectMapper mapper = new ObjectMapper();
        DataUtil util = new DataUtil();
        File resourcePath = new File("src/test/resources/data-util-test/condenser-microphone-dto-list.json");
        Object expected = mapper.readValue(resourcePath, new TypeReference<List<CondenserMicrophoneDto>>() {
        });

        //when
        List<Microphone> microphones = util.filterFor(Microphone.class, CondenserMicrophone.class);
        List<MicrophoneDto> actual = mapper.convertValue(microphones,
                new TypeReference<List<CondenserMicrophoneDto>>() {
                });

        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
