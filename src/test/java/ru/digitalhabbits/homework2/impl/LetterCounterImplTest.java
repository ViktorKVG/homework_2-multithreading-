package ru.digitalhabbits.homework2.impl;

import org.junit.jupiter.api.Test;
import ru.digitalhabbits.homework2.LetterCounter;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.junit.jupiter.api.Assertions.*;

class LetterCounterImplTest {

    private static final String line = "Letters, digits 9 32 2 and signs | $ # ^";

    @Test
    void letter_counter_should_correctly_count_letters() {
        LetterCounterImpl letterCounter = new LetterCounterImpl(line);

        Map<Character, Long> result = letterCounter.call();

        assertThat(result).containsOnly(
                entry('a', 1L),
                entry('r', 1L),
                entry('s', 4L),
                entry('t', 3L),
                entry('d', 2L),
                entry('e', 2L),
                entry('g', 2L),
                entry('i', 3L),
                entry('l', 1L),
                entry('n', 2L)
        );
    }
}