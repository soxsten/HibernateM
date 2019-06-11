package com.util;

import com.entity.CondenserMicrophone;
import com.entity.Microphone;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

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
    void name() {
        //given
        DataUtil util = new DataUtil();
        //when
        List<Microphone> microphones = util.filterFor(Microphone.class, CondenserMicrophone.class);
        //then
        System.out.println();
    }
}