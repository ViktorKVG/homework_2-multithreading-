package ru.digitalhabbits.homework2;

import org.junit.jupiter.api.Test;
import ru.digitalhabbits.homework2.impl.AsyncFileLetterCounter;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import static com.google.common.io.Resources.getResource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

public class E2ETestComplicated {

    @Test
    void async_file_letter_counting_should_count_only_letters() {
        var file = getFile("test_2.txt");
        var counter = new AsyncFileLetterCounter(new MockFileReader(), Executors.newCachedThreadPool());

        Map<Character, Long> count = counter.count(file);

        assertThat(count).containsOnly(
                entry('a', 4L),
                entry('b', 1L),
                entry('c', 2L),
                entry('d', 3L),
                entry('e', 3L),
                entry('f', 1L),
                entry('g', 2L),
                entry('h', 1L),
                entry('i', 5L),
                entry('l', 2L),
                entry('m', 2L),
                entry('n', 5L),
                entry('o', 3L),
                entry('p', 1L),
                entry('r', 1L),
                entry('s', 7L),
                entry('t', 5L),
                entry('u', 1L),
                entry('w', 1L),
                entry('y', 2L)
        );
    }

    private File getFile(String name) {
        File file = null;
        try {
            file =new File(getResource(name).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return file;
    }

    static class MockFileReader implements FileReader {
        @Override

        public Stream<String> readLines(File file) {
            Stream<String> lineStream = null;
            try {
                lineStream = Files.lines(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return lineStream;
        }
    }
}

