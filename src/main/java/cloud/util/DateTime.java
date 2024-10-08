package cloud.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import cloud.exception.DateFormatException;

/**
 * Represents a date and time with a specific format.
 * <p>
 * A <code>DateTime</code> object handles date and time information, providing methods
 * to parse datetime strings and format them for saving or display.
 * </p>
 */
public class DateTime {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");

    private final LocalDateTime dateTime;

    private DateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Returns a DateTime object for the corresponding input string
     *
     * @param input date and time string in the format of "dd/MM/yyyy HH:mm"
     * @return a DateTime object
     * @throws DateFormatException If input format is invalid
     */
    public static DateTime of(String input) throws DateFormatException {
        try {
            return new DateTime(LocalDateTime.parse(input, INPUT_FORMAT));
        } catch (DateTimeParseException e) {
            throw new DateFormatException("Invalid date-time format: Try again with dd/MM/yyyy HH:mm");
        }
    }

    /**
     * Returns a string representation of the DateTime object in a savable format
     *
     * @return formatted DateTime string
     */
    public String formatSave() {
        return this.dateTime.format(INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return this.dateTime.format(OUTPUT_FORMAT);
    }
}
