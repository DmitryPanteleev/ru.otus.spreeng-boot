package ru.otus.spreengboot.homework.dpanteleev.lesson3.service;

import org.springframework.stereotype.Service;
import ru.otus.spreengboot.homework.dpanteleev.lesson3.dao.CustomInputStream;
import ru.otus.spreengboot.homework.dpanteleev.lesson3.dao.CustomPrintStream;

import java.io.PrintStream;
import java.util.Scanner;

@Service
public class UserIOServiceImpl implements UserIOService {

    private final Scanner scanner;
    private final PrintStream printStream;

    public UserIOServiceImpl(CustomInputStream in, CustomPrintStream out) {
        scanner = new Scanner(in.getIn());
        this.printStream = new PrintStream(out.getOut());
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }

    @Override
    public void printString(String str) {
        printStream.println(str);
    }
}
