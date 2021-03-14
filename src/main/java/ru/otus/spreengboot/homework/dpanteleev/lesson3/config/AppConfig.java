package ru.otus.spreengboot.homework.dpanteleev.lesson3.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@ConfigurationProperties(prefix = "app")
@Component
public class AppConfig {

    private String path;

    private int count;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
