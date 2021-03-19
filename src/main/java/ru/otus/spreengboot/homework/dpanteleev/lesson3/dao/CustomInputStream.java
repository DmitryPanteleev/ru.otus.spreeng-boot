package ru.otus.spreengboot.homework.dpanteleev.lesson3.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class CustomInputStream {
    private final InputStream in;

    public CustomInputStream(@Value("#{ T(System).in }") InputStream in) {
        this.in = in;
    }

    public InputStream getIn() {
        return in;
    }
}
