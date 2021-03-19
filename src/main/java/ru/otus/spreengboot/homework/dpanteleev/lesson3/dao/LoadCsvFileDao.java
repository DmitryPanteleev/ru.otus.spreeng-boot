package ru.otus.spreengboot.homework.dpanteleev.lesson3.dao;

import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;

public interface LoadCsvFileDao {

    /**
     * Загружает файл с вопросами
     * @return CSVRecord
     */
    Iterable<CSVRecord> getCsvFile() throws FileNotFoundException;

}
