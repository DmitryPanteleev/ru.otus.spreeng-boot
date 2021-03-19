package ru.otus.spreengboot.homework.dpanteleev.lesson3.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.otus.spreengboot.homework.dpanteleev.lesson3.config.AppConfig;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.Arrays;

@Repository
public class LoadCsvFileDaoSimple implements LoadCsvFileDao{

    /**
     * Путь к файлу
     */
    private String pathToFile;

    public LoadCsvFileDaoSimple(AppConfig config) {
        this.pathToFile = config.getPath();
    }

    @Override
    public Iterable<CSVRecord> getCsvFile() throws FileNotFoundException {
        try {
            //Читаем файл с вопросами
            Reader in = new FileReader(Paths.get(pathToFile).toAbsolutePath().toString());
            //Парсим вопросы
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
            return records;
        } catch (IOException e) {
            logger.error(e.getMessage() + " " + Arrays.toString(e.getStackTrace()));
        }
        throw new FileNotFoundException("Не смогли прочесть CSV файл с вопросами");
    }

    private Logger logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

}
