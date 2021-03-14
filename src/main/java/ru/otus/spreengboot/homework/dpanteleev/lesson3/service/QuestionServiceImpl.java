package ru.otus.spreengboot.homework.dpanteleev.lesson3.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import ru.otus.spreengboot.homework.dpanteleev.lesson3.config.AppConfig;
import ru.otus.spreengboot.homework.dpanteleev.lesson3.dao.QuestionDao;
import ru.otus.spreengboot.homework.dpanteleev.lesson3.domain.Answer;
import ru.otus.spreengboot.homework.dpanteleev.lesson3.domain.Question;

import java.util.ArrayList;
import java.util.List;

@EnableConfigurationProperties(AppConfig.class)
@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;

    /**
     * Количество правильных ответов для зачёта
     */
    private int offset;

    public QuestionServiceImpl(QuestionDao dao, AppConfig config) {
        this.dao = dao;
        this.offset = config.getCount();
    }

    public String getQuestionTextByNumber(int number) throws ArrayIndexOutOfBoundsException {
        if (number < 0 || number > dao.size()) {
            throw new ArrayIndexOutOfBoundsException("Нет такого номера вопроса");
        }
        return dao.getQustionsList().get(number).getQuestion();
    }
    public String getAnswerTextByNumber(int number) throws ArrayIndexOutOfBoundsException {
        if (number < 0 || number > dao.size()) {
            throw new ArrayIndexOutOfBoundsException("Нет такого номера вопроса");
        }
        return dao.getQustionsList().get(number).getUserAnswer().getAnswer();
    }

    public void setAnswer(Answer answer, int number) {
        dao.getQustionsList().get(number).setUserAnswer(answer);
        logger.info("Записан ответ: " + answer.toString());
    }

    /**
     * Печатает результат
     */
    public void printResult() {
        for (Question q :
                dao.getQustionsList()) {
            try {
                System.out.println("Вопрос: " + q.getQuestion());
                System.out.println("Ответ: " + q.getUserAnswer());
                if (!q.getCorrectAnswers().isEmpty()) {
                    if (q.isRightAnswer()) {
                        System.out.println("Верный ответ");
                    } else {
                        System.out.println("Ложный ответ");
                    }
                }
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Answer> randomAnswerList(int number) {
        List<Answer> list = new ArrayList<>();
        list.addAll(dao.getQustionsList().get(number).getCorrectAnswers());
        list.addAll(dao.getQustionsList().get(number).getWrongAnswers());
        return list;
    }

    /**
     * зачёт
     *
     * @return true если зачёт
     */
    public boolean isOffset() {
        int count = 0;
        for (Question q :
                dao.getQustionsList()) {
            if (q.isHaveAnswer() && !q.getCorrectAnswers().isEmpty() && q.isRightAnswer()) {
                count++;
            }
        }
        return offset <= count;
    }

    public long size() {
        return dao.size();
    }

    Logger logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
}
