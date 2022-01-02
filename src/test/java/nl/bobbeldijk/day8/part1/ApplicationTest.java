package nl.bobbeldijk.day8.part1;

import nl.bobbeldijk.util.AnswerNotFoundException;
import nl.bobbeldijk.util.InputFile;
import nl.bobbeldijk.util.InputReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationTest {

    private nl.bobbeldijk.day8.part1.Application application;

    @BeforeEach
    void setUp() {
        application = new nl.bobbeldijk.day8.part1.Application();
    }

    @Test
    void calculateAnswerTest() throws AnswerNotFoundException {
        var testInput = InputReader.readStreamFromInputFile(InputFile.DAY8, true, "test")
                .collect(Collectors.toList());

        assertEquals(26, application.calculateAnswer(testInput));
    }
}