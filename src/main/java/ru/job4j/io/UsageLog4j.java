package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Artur";
        String surname = "Pirozhkov";
        byte age = 40;
        String sex = "man";
        int numberPassport = 12345678;
        boolean temp = true;
        LOG.debug("User info name : {}, surname : {}, age : {}, sex : {}, numberPassport : {}, temp : {}",
                name, surname, age, sex, numberPassport, temp);
    }
}
