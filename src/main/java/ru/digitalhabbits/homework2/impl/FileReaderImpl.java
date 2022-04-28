package ru.digitalhabbits.homework2.impl;

import lombok.SneakyThrows;
import ru.digitalhabbits.homework2.FileReader;
import java.io.File;
import java.nio.file.Files;
import java.util.stream.Stream;

public class FileReaderImpl implements FileReader {

    @Override
    @SneakyThrows
    public Stream<String> readLines(File file) {
        return Files.lines(file.toPath());
    }
}
