package ru.otus.spreengboot.homework.dpanteleev.lesson3.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    Logger logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

    //Пока делаю без логгера, как понимаю в буте будет свой логгер и нет смысла подключать его сейчас
    @Around("execution(* ru.otus.spreengboot.homework.dpanteleev.lesson3.dao.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable{

        logger.debug("Класс: " + joinPoint.getTarget().getClass().getSimpleName()
                + " Метод: " + joinPoint.getSignature().getName()
                + " Начал работу");
        long start = System.currentTimeMillis();
        Object res = joinPoint.proceed();
        logger.debug("Класс: " + joinPoint.getTarget().getClass().getSimpleName() + " Окончил работу за " +
                (System.currentTimeMillis() - start) + " миллисекунд");
        return res;
    }
}
