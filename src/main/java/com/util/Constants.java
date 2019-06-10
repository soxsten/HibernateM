package com.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public
class Constants {
    static final String HIBERNATE_CONFIG_XML = "hibernate.cfg.xml";
    public static final String HIBERNATE_TEST_CONFIG_XML = "hibernate-test.cfg.xml";

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    class Credentials {
        static final String URL = "jdbc:mysql://localhost:3306/hibernate?useSSL=false&serverTimezone=UTC";
        static final String USER = "root";
        static final String PASSWORD = "mysql";
    }
}
