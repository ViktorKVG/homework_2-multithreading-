package ru.digitalhabbits.homework2.impl;

import lombok.RequiredArgsConstructor;
import ru.digitalhabbits.homework2.LetterCountMerger;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class LetterCountMergerImpl implements LetterCountMerger {

    private static final Map<Character, Long> accumulator = new HashMap<>();
    private final Map<Character, Long> mapToAdd;

    @Override
    public Map<Character, Long> call() {
        mapToAdd.forEach((key, value) ->
                accumulator.merge(key, value, Long::sum));
        return new HashMap<>(accumulator);
    }
}
