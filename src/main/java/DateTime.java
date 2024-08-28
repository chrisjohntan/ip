import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DateTime {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MM dd yyyy, HH:mm a");

    private final LocalDateTime dateTime;

    private DateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public static DateTime of(String input) throws CloudException{
        try {
            return new DateTime(LocalDateTime.parse(input, INPUT_FORMAT));
        } catch (DateTimeParseException e) {
            throw new CloudException("Invalid date-time format: " + e.getMessage());
        }
    }

    public String formatSave() {
        return this.dateTime.format(INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return this.dateTime.format(OUTPUT_FORMAT);
    }
}
