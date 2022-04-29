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

public class E2ETests {

    @Test
    void async_file_letter_counting_should_return_predicted_count() {
        var file = getFile("test.txt");
        var counter = new AsyncFileLetterCounter(new MockFileReader(), Executors.newCachedThreadPool());

        Map<Character, Long> count = counter.count(file);

        assertThat(count).containsOnly(
                entry('a', 2697L),
                entry('b', 2683L),
                entry('c', 2647L),
                entry('d', 2613L),
                entry('e', 2731L),
                entry('f', 2629L)
        );
    }

    //@SneakyThrows
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
