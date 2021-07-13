package patientintake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("dateTime")
@DisplayName("DateTimeConverter should")
class DateTimeConverterShould {

  @Test
  @DisplayName("convert expected date time pattern in string correctly")
  void convertCorrectPatternToDateTime() {
    LocalDateTime result = DateTimeConverter.convertStringToDateTime("07/12/2021 4:00 pm",
        LocalDate.of(2021, 7, 12));
    assertEquals(result, LocalDateTime.of(2021, 7, 12, 16, 0));
  }

  @Test
  @DisplayName("throw exception if enternd pattern of string incorrect")
  void throwExceptionIfIncorrectPatternProvided() {
    Throwable error = assertThrows(RuntimeException.class,
        () -> DateTimeConverter.convertStringToDateTime("07/12/2021 400 pm",
            LocalDate.of(2021, 7, 12)));
    assertEquals("Unable to create date time from: [07/12/2021 400 pm], please enter with " +
        "format [M/d/yyyy h:mm a], Text '07/12/2021 400 PM' could not be parsed at index 14", error.getMessage());
  }

  @Nested
  @DisplayName("convert string with 'today' keyword")
  class TodayTests {
    @Test
    @DisplayName("correctly")
    void convertTodayStringCorrectly() {
      LocalDate today = LocalDate.of(2021, 7, 12);
      LocalDateTime result = DateTimeConverter.convertStringToDateTime("today 4:00 pm",
          today);
      assertEquals(result, LocalDateTime.of(2021, 7, 12, 16, 0),
          () -> "Failed to convert 'today' string to expected date-time, 'today' passed was: " + today);
    }

    @Test
    @DisplayName("regardless of case")
    void convertTodayStringCorrectlyCaseInsensitive() {
      LocalDate today = LocalDate.of(2021, 7, 12);
      LocalDateTime result = DateTimeConverter.convertStringToDateTime("TODAY 4:00 pm",
          today);
      assertEquals(result, LocalDateTime.of(2021, 7, 12, 16, 0),
          () -> "Failed to convert 'today' string to expected date-time, 'today' passed was: " + today);
    }
  }
}