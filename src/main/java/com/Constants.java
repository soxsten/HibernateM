package com;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
class Constants {
    static final String HIBERNATE_CONFIG_XML = "hibernate.cfg.xml";
    static final String HIBERNATE_TEST_CONFIG_XML = "hibernate-test.cfg.xml";

    class Credentials {
        static final String URL = "jdbc:mysql://localhost:3306/hibernate?useSSL=false&serverTimezone=UTC";
        static final String USER = "root";
        static final String PASSWORD = "mysql";
    }
}
