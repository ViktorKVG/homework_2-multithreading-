package ru.digitalhabbits.homework2.impl;

import com.google.common.primitives.Chars;
import lombok.RequiredArgsConstructor;
import ru.digitalhabbits.homework2.LetterCounter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class LetterCounterImpl implements LetterCounter {

    private final String line;

    @Override
    public Map<Character, Long> call() {
        List<Character> chars = Chars.asList(
                line.replaceAll("\\s", "")
                        .toCharArray());
        return chars.stream()
                .filter(Character::isLetter)
                .map(Character::toLowerCase)
                .collect(HashMap::new, this::countLetters, HashMap::putAll);
    }

    private void countLetters(Map<Character, Long> map, Character key) {
        if(map.containsKey(key)) {
            map.put(key, map.get(key) + 1L);
        }
        else {
            map.put(key, 1L);
        }
    }
}
