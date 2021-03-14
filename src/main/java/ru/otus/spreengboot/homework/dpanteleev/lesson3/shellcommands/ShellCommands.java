package ru.otus.spreengboot.homework.dpanteleev.lesson3.shellcommands;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spreengboot.homework.dpanteleev.lesson3.service.PollService;

@ShellComponent
public class ShellCommands {

    private final PollService pollService;

    public ShellCommands(PollService pollService) {
        this.pollService = pollService;
    }

    @ShellMethod(key = {"s", "S", "start", "Start"}, value = "start poll")
    public void start() {
        pollService.startPoll();
    }

//    @ShellMethod(key = {}, value = "")
}
