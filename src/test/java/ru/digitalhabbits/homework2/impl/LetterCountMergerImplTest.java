package ru.digitalhabbits.homework2.impl;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class LetterCountMergerImplTest {

    private static final Map<Character, Long> map1 = Map.of(
            'a', 2L,
            'b', 1L,
            'c', 4L);
    private static final Map<Character, Long> map2 = Map.of(
            'b', 2L,
            'c', 7L,
            'd', 5L);

    @Test
    void maps_should_be_merged_correctly() {
        LetterCountMergerImpl merger = new LetterCountMergerImpl(map1);
        merger.call();

        merger = new LetterCountMergerImpl(map2);
        Map<Character, Long> result = merger.call();


        assertThat(result).containsOnly(
                entry('a', 2L),
                entry('b', 3L),
                entry('c', 11L),
                entry('d', 5L)
        );
    }
}