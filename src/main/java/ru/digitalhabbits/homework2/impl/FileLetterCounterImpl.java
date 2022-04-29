package ru.digitalhabbits.homework2.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class FileLetterCounterImpl implements Callable <Map<Character, Long>> {

    private static final Map<Character, Long> accumulator = new HashMap<>();
    private final Map<Character, Long> mapToAdd;

   public FileLetterCounterImpl(Map<Character, Long> mapToAdd) {
       this.mapToAdd = mapToAdd;
   }

    @Override
    public Map<Character, Long> call() throws Exception {
        mapToAdd.forEach((key, value) ->
                accumulator.merge(key, value, Long::sum));
        return new HashMap<>(accumulator);
    }
}
