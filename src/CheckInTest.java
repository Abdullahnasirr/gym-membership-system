// @author Ethan Chiu and Abdulah Nasir

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CheckInTest {

    @Test
    public void constructor_setsDateCorrectly() {
        CheckIn checkIn = new CheckIn("2026-03-23");

        assertEquals("2026-03-23", checkIn.getCheckInDate());
    }

    @Test
    public void setter_updatesDateCorrectly() {
        CheckIn checkIn = new CheckIn("2026-03-23");

        checkIn.setCheckInDate("2026-03-24");

        assertEquals("2026-03-24", checkIn.getCheckInDate());
    }

    @Test
    public void toString_containsDate() {
        CheckIn checkIn = new CheckIn("2026-03-23");

        String text = checkIn.toString();

        assertTrue(text.contains("2026-03-23"));
    }
}