package ru.digitalhabbits.homework2.impl;

import lombok.RequiredArgsConstructor;
import ru.digitalhabbits.homework2.FileLetterCounter;
import ru.digitalhabbits.homework2.FileReader;

import java.io.File;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AsyncFileLetterCounter implements FileLetterCounter {

    private final FileReader fileReader;
    private final ExecutorService executor;

    @Override
    public Map<Character, Long> count(File input) {
        return fileReader.readLines(input)
                .map(c -> executor.submit(new LetterCounterImpl(c)))
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
