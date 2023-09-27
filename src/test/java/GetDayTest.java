import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.opentest4j.AssertionFailedError;

import java.util.stream.Stream;

public class GetDayTest {

    private static final Logger logger = LogManager.getLogger(GetDayTest.class);
    public static final String ASSERTION_FAILED = "Assertion failed for input number=%d, ";

    public static final String ASSERTION_FAILED_FOR_NULL_INPUT = "Assertion failed for null input";
    public static final String TESTING_POSITIVE_CASE = "Testing positive case for day number=%d - success";
    public static final String TESTING_NEGATIVE_CASE = "Testing negative case for day number=%d - success";
    public static final String TESTING_EMPTY_CASE = "Testing negative case for null input - success";

    @ParameterizedTest
    @MethodSource("positiveData")
    public void positiveScenarios(int input, String expected) {
        try {
            Assertions.assertEquals(Main.getDay(input), expected);
            logger.info(String.format(TESTING_POSITIVE_CASE, input));
        } catch (AssertionFailedError e) {
            logger.error(String.format(ASSERTION_FAILED, input) + e.getMessage());
            throw e;
        }
    }

    private static Stream<Arguments> positiveData() {
        return Stream.of(
                Arguments.of(1, "Sunday"),
                Arguments.of(2, "Monday"),
                Arguments.of(3, "Tuesday"),
                Arguments.of(4, "Wednesday"),
                Arguments.of(5, "Thursday"),
                Arguments.of(6, "Friday"),
                Arguments.of(7, "Saturday")
        );
    }

    @ParameterizedTest
    @MethodSource("negativeData")
    public void negativeScenarios(int input, String expected) {
        try {
            Assertions.assertEquals(Main.getDay(input), expected);
            logger.info(String.format(TESTING_NEGATIVE_CASE, input));
        } catch (AssertionFailedError e) {
            logger.error(String.format(ASSERTION_FAILED, input) + e.getMessage());
            throw e;
        }
    }

    @Test
    public void nullInputTest() {
        try {
            Assertions.assertThrows(NullPointerException.class, () -> Main.getDay(null));
            logger.info(TESTING_EMPTY_CASE);
        } catch (AssertionFailedError e) {
            logger.error(ASSERTION_FAILED_FOR_NULL_INPUT + e.getMessage());
            throw e;
        }
    }

    private static Stream<Arguments> negativeData() {
        var inputTooSmall = "The number should be equal or larger than 1";
        var inputTooBig = "The number should be equal or smaller than 7";
        return Stream.of(
                Arguments.of(0, inputTooSmall),
                Arguments.of(-2, inputTooSmall),
                Arguments.of(8, inputTooBig),
                Arguments.of(12, inputTooBig)
        );
    }

}
