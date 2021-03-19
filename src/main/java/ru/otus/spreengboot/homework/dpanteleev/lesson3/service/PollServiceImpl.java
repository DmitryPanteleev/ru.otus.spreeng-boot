package ru.otus.spreengboot.homework.dpanteleev.lesson3.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spreengboot.homework.dpanteleev.lesson3.domain.Answer;

import java.util.Locale;

@Service
public class PollServiceImpl implements PollService {

    private final QuestionService qs;
    private final MessageSource ms;
    private final UserIOService ioService;

    public PollServiceImpl(QuestionService qs, MessageSource ms, UserIOService ioService) {
        this.qs = qs;
        this.ms = ms;
        this.ioService = ioService;
    }

    @Override
    public void startPoll() {
        // Ответьте на вопрос
        System.out.println("\n" + ms.getMessage(
                "poll-start-question", null
                , Locale.getDefault()));
        //Вопрос / ответ
        for (int i = 0; i < qs.size(); i++) {
            // Задать вопрос
            ioService.printString(qs.getQuestionTextByNumber(i) + " " + qs.randomAnswerList(i));
            // Получить ответ
            // Сохранить ответ
            qs.setAnswer(new Answer(ioService.readLine()), i);
        }
        // Печать результатов
        qs.printResult();
        if (qs.isOffset()) {
            ioService.printString(ms.getMessage("poll-offset",null, Locale.getDefault()));
        } else {
            ioService.printString(ms.getMessage("poll-notoffset",null, Locale.getDefault()));
        }
        ioService.printString(ms.getMessage("app-help-start",null, Locale.getDefault()));
        ioService.printString(ms.getMessage("app-help-help",null, Locale.getDefault()));
    }
}
