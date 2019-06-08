package com;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
class Constants {
    static final String HIBERNATE_CONFIG_XML = "hibernate.cfg.xml";
    static final String HIBERNATE_TEST_CONFIG_XML = "hibernate-test.cfg.xml";
}
