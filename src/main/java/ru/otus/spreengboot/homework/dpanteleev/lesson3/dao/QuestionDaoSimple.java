package ru.otus.spreengboot.homework.dpanteleev.lesson3.dao;

import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.otus.spreengboot.homework.dpanteleev.lesson3.domain.Answer;
import ru.otus.spreengboot.homework.dpanteleev.lesson3.domain.Question;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository("questionDaoSimple")
public class QuestionDaoSimple implements QuestionDao {

    private static final String WRONGANSWER = "wrong_answer";
    private static final String CORRECTANSWER = "correct_answer";
    private static final String QUESTION = "question";

    private final LoadCsvFileDao records;

    public QuestionDaoSimple(LoadCsvFileDao records) {
        this.records = records;
    }

    @Override
    public List<Question> getQustionsList() {
        if (questionList.isEmpty()) {
            try {
                parseCSVFile(records.getCsvFile());
            } catch (FileNotFoundException e) {
                logger.error(e.getMessage() + " " + Arrays.toString(e.getStackTrace()));
            }
        }
        return questionList;
    }

    @Override
    public long size() {
        if (questionList.isEmpty()) {
            try {
                parseCSVFile(records.getCsvFile());
            } catch (FileNotFoundException e) {
                logger.error(e.getMessage() + " " + Arrays.toString(e.getStackTrace()));
            }
        }
        return questionList.size();
    }

    /**
     * Список вопросов с возможными вариантами ответов
     */
    private List<Question> questionList = new ArrayList<>();

    /**
     * Обрабатываем данные полученные из записей CSV
     *
     * @param records
     */
    private void parseCSVFile(Iterable<CSVRecord> records) {
        //Получаю лист вопросов с возможными вариантами ответов
        for (CSVRecord record : records) {
            Question q;
            if ((q = csvToQuestion(record)) != null) {
                questionList.add(q);
            }
        }
    }

    /**
     * Принимает CSV строку и возвращает вопрос с возможными вариантами ответа
     *
     * @param record запись в CSV файле
     * @return Question
     */
    private Question csvToQuestion(CSVRecord record) {
        // Проверяем на наличие вопроса
        if (record.get(QUESTION).isEmpty() || record.get(QUESTION).isBlank()) return null;
        //Возможные варианты ложных ответов
        List<Answer> wrongAnswers = new ArrayList<>();
        // Возможные варианты правильных ответов
        List<Answer> correctAnswers = new ArrayList<>();
        // Если запись содержит ответы добавляем их
        for (int i = 0; i < record.size(); i++) {
            if (record.get(i).isEmpty()) continue;
            if (record.getParser().getHeaderNames().get(i).equals(CORRECTANSWER)) {
                correctAnswers.add(new Answer(record.get(i)));
            } else if (record.getParser().getHeaderNames().get(i).equals(WRONGANSWER)) {
                wrongAnswers.add(new Answer(record.get(i)));
            }
        }
        // Если запись содержит вопрос добавляем его
        return new Question(
                record.get(QUESTION),
                wrongAnswers,
                correctAnswers);
    }
    private Logger logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
}
