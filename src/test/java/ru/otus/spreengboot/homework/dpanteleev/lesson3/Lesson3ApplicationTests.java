package ru.otus.spreengboot.homework.dpanteleev.lesson3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spreengboot.homework.dpanteleev.lesson3.domain.Answer;
import ru.otus.spreengboot.homework.dpanteleev.lesson3.service.QuestionService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Сервис вопросов")
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@ExtendWith(SpringExtension.class)
class Lesson3ApplicationTests {

    @Autowired
    QuestionService qs;

    @Test
    void contextLoads() {
    }

    @DisplayName("считывает все вопросы из CSV файла")
    @Test()
    void testGetQuestionsListSize() {
        assertEquals(8, qs.size());
    }

    @DisplayName(" сохраняет ответ под нужным номером и имеет тотже текст")
    @Test
    void testSaveAnswer(){
        String testAnswer = "answer";
        int answerNumber = 0;
        qs.setAnswer(new Answer(testAnswer), answerNumber);
        assertEquals(testAnswer, qs.getAnswerTextByNumber(answerNumber));
    }

}
