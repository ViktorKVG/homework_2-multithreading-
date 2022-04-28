package ru.digitalhabbits.homework2.impl;

import com.google.common.primitives.Chars;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class LetterCounterImpl implements Callable<Map<Character, Long>> {
    private final String line;

    public LetterCounterImpl(String line) {
        this.line = line;
    }

    @Override
    public Map<Character, Long> call() throws Exception {
        List<Character> chars = Chars.asList(line.toCharArray());
        return chars.stream().collect(HashMap::new, (map, key) -> {
            if(map.containsKey(key))
                map.put(key, map.get(key) + 1L);
            else
                map.put(key, 1L);
        }, HashMap::putAll);
    }
}
