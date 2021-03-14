package ru.otus.spreengboot.homework.dpanteleev.lesson3.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spreengboot.homework.dpanteleev.lesson3.domain.Answer;

import java.util.Locale;
import java.util.Scanner;

@Service
public class PollServiceImpl implements PollService {

    private final QuestionService qs;
    private final MessageSource ms;
    private final Scanner scanner = new Scanner(System.in);

    public PollServiceImpl(QuestionService qs, MessageSource ms) {
        this.qs = qs;
        this.ms = ms;
    }

    public void startPoll() {
        // Ответьте на вопрос
        System.out.println("\n" + ms.getMessage(
                "poll-start-question", null
                , Locale.getDefault()));
        //Вопрос / ответ
        for (int i = 0; i < qs.size(); i++) {
            // Задать вопрос
            System.out.println(qs.getQuestionTextByNumber(i) + " " + qs.randomAnswerList(i));
            // Получить ответ
            // Сохранить ответ
            qs.setAnswer(new Answer(scanner.nextLine()), i);
        }
        // Печать результатов
        qs.printResult();
        if (qs.isOffset()) {
            System.out.println(ms.getMessage("poll-offset",null, Locale.getDefault()));
        } else {
            System.out.println(ms.getMessage("poll-notoffset",null, Locale.getDefault()));
        }
        System.out.println(ms.getMessage("app-help-start",null, Locale.getDefault()));
        System.out.println(ms.getMessage("app-help-help",null, Locale.getDefault()));
    }
}
