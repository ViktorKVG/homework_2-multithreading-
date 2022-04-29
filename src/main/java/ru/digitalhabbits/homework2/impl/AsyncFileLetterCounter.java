package ru.digitalhabbits.homework2.impl;

import ru.digitalhabbits.homework2.FileLetterCounter;
import ru.digitalhabbits.homework2.FileReader;

import java.io.File;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class AsyncFileLetterCounter implements FileLetterCounter {

    private final FileReader fileReader = new FileReaderImpl();
    private final ExecutorService executor = Executors.newCachedThreadPool();

    @Override
    public Map<Character, Long> count(File input) {
        return fileReader.readLines(input)
                .map(String::toLowerCase)
                .map(s -> s.replaceAll("\\s", ""))
                .map(s -> executor.submit(new LetterCounterImpl(s)))
                .map(this::futureToMap)
                .map(map -> executor.submit(new LetterCountMergerImpl(map)))
                .map(this::futureToMap)
                .filter(Objects::nonNull)
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1 = v2));
    }

    private Map<Character, Long> futureToMap(Future<Map<Character, Long>> future) {
        Map<Character, Long> result = null;
        try {
            result = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }
}
