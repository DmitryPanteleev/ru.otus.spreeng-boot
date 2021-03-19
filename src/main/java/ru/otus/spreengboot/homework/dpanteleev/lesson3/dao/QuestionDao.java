package ru.otus.spreengboot.homework.dpanteleev.lesson3.dao;


import ru.otus.spreengboot.homework.dpanteleev.lesson3.domain.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> getQustionsList();

    long size();

}
