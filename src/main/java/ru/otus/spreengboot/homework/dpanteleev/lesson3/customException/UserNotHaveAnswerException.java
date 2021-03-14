package ru.otus.spreengboot.homework.dpanteleev.lesson3.customException;

public class UserNotHaveAnswerException extends NullPointerException {

    /**
     * Пользователь не дал ответа
     * @param s Текст ошибки
     */
    public UserNotHaveAnswerException(String s) {
        super(s);
    }
}
