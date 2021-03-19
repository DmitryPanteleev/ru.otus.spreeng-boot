package ru.otus.spreengboot.homework.dpanteleev.lesson3.service;

import ru.otus.spreengboot.homework.dpanteleev.lesson3.domain.Answer;

import java.util.List;

public interface QuestionService {
    /**
     * Находим вопрос по его номеру
     * Если вопрос не найден бросает исключение
     *
     * @param number номер вопроса в списке
     * @return Вопрос из списка под заданным номером
     */
    String getQuestionTextByNumber(int number) throws ArrayIndexOutOfBoundsException;

    /**
     * Находит ответ по его номеру или бросает исключение если нет такого ответ
     *
     * @param number номер вопросана который дан ответ
     * @return текст ответа
     */
    String getAnswerTextByNumber(int number) throws ArrayIndexOutOfBoundsException;

    /**
     * Типо рандомно расположенные варианты ответов
     *
     * @param number
     * @return List<Answer>
     */
    List<Answer> randomAnswerList(int number);

    /**
     * Запись ответа
     *
     * @param answer Ответ
     * @param number Номер вопроса
     */
    void setAnswer(Answer answer, int number);

    /**
     * Возращает количество вопросов в списке
     *
     * @return long
     */
    long size();

    /**
     * Зачёт не зачёт
     *
     * @return true если количество правильных ответов больше или равно проходному
     */
    boolean isOffset();

    void printResult();
}
