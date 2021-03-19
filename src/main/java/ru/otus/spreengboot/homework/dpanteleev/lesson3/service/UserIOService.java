package ru.otus.spreengboot.homework.dpanteleev.lesson3.service;

public interface UserIOService {

    /**
     * Чтение данных введённых пользователем
     * @return Считанные данные
     */
    String readLine();

    /**
     * Выводит данные для пользователя
     * @param str Строка для вывода
     */
    void printString(String str);

}
