package ru.otus.spreengboot.homework.dpanteleev.lesson3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spreengboot.homework.dpanteleev.lesson3.dao.CustomInputStream;
import ru.otus.spreengboot.homework.dpanteleev.lesson3.dao.CustomPrintStream;
import ru.otus.spreengboot.homework.dpanteleev.lesson3.service.UserIOService;
import ru.otus.spreengboot.homework.dpanteleev.lesson3.service.UserIOServiceImpl;

import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Input Output Service")
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@ExtendWith(SpringExtension.class)
public class UserIOTest {


    private static final String testText = "test text)";
    private UserIOService ioService;
    private ByteArrayOutputStream bot;

    @BeforeEach()
    void setUp() {
        bot = new ByteArrayOutputStream();
        ioService = new UserIOServiceImpl(new CustomInputStream(System.in), new CustomPrintStream(bot));
    }

    @DisplayName("Записывает строку")
    @Test
    void testWriteUserIOService() {
        ioService.printString(testText);
        assertEquals(testText + "\n", bot.toString());
    }
}
