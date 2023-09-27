import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.opentest4j.AssertionFailedError;

import java.util.stream.Stream;


public class CalculateScoresTest {
    public static final String ASSERTION_FAILED = "Assertion failed for input=\"%s\",";
    private static final Logger logger = LogManager.getLogger(CalculateScoresTest.class);
    public static final String TESTING_POSITIVE_CASE = "Testing positive case for input=\"%s\" - success";
    public static final String TESTING_NEGATIVE_CASE = "Testing negative case for input=\"%s\" - success";
    public static final String TESTING_EMPTY_INPUT = "Testing empty input for input=\"%s\" - success";

    @Test
    public void smokeTest() {
        int[] expected = new int[]{1, 1, 1};
        String input = "ABC";
        try {
            Assertions.assertArrayEquals(expected, Main.calculateScores(input));
        } catch (AssertionFailedError e) {
            logger.error(String.format(ASSERTION_FAILED, input) + e.getMessage());
            throw e;
        }
    }

    @ParameterizedTest
    @MethodSource("positiveData")
    public void positiveCases(String input, int[] expected) {
        try {
            Assertions.assertArrayEquals(expected, Main.calculateScores(input));
            logger.info(String.format(TESTING_POSITIVE_CASE, input));
        } catch (AssertionFailedError e) {
            logger.error(String.format(ASSERTION_FAILED, input) + e.getMessage());
            throw e;
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "АВС", "sdfsdf34534#$%"}) //here ABC are Russian letters
    public void negativeCases(String input) {
        try {
            Exception exception = Assertions.assertThrows(RuntimeException.class, () -> Main.calculateScores(input));
            String expectedMessage = "Wrong symbols";
            String actualMessage = exception.getMessage();
            Assertions.assertTrue(actualMessage.contains(expectedMessage));
            logger.info(String.format(TESTING_NEGATIVE_CASE, input));
        } catch (AssertionFailedError e) {
            logger.error(String.format(ASSERTION_FAILED, input) + e.getMessage());
            throw e;
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    public void emptyInputTest(String input) {
        try {
            Exception exception = Assertions.assertThrows(RuntimeException.class, () -> Main.calculateScores(input));
            String expectedMessage = "Empty input string";
            String actualMessage = exception.getMessage();
            Assertions.assertTrue(actualMessage.contains(expectedMessage));
            logger.info(String.format(TESTING_EMPTY_INPUT, input));
        } catch (AssertionFailedError e) {
            logger.error(String.format(ASSERTION_FAILED, input) + e.getMessage());
            throw e;
        }
    }

    private static Stream<Arguments> positiveData() {
        return Stream.of(
                Arguments.of("A", new int[]{1, 0, 0}),
                Arguments.of("BB", new int[]{0, 2, 0}),
                Arguments.of("CCC", new int[]{0, 0, 3}),
                Arguments.of("ABBACCCCAC", new int[]{3, 2, 5}),
                Arguments.of(" ABBAC CCCAC  ", new int[]{3, 2, 5})
        );
    }
}
