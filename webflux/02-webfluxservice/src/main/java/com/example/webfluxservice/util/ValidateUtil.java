package com.example.webfluxservice.util;

import com.example.webfluxservice.exception.StudentException;

import java.util.stream.Stream;

public class ValidateUtil {
    private static final String[] INVALIDE_NAMES = {"admin", "administrator"};

    public static void valideName(String name) {
        Stream.of(INVALIDE_NAMES).filter(item -> item.equalsIgnoreCase(name)).findAny()
                .ifPresent(item -> {
                    throw new StudentException("name", item, "使用了非法姓名");
                });
    }
}
