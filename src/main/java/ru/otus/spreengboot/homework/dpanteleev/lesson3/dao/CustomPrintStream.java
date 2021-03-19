package ru.otus.spreengboot.homework.dpanteleev.lesson3.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.OutputStream;

@Component
public class CustomPrintStream {

    private final OutputStream out;

    public CustomPrintStream(@Value("#{ T(System).out }") OutputStream out) {
        this.out = out;
    }

    public OutputStream getOut() {
        return out;
    }
}
