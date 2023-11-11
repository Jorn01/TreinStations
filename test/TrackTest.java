import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TrackTest {

    @Test
    public void testValidTrack() {
        // Valid input values
        assertDoesNotThrow(() -> new track("ABC", "BAC", 1));
    }

    @Test
    public void testNullDeparture() {
        assertThrows(AssertionError.class, () -> new track(null, "B", 1));
    }

    @Test
    public void testBlankDeparture() {
        assertThrows(AssertionError.class, () -> new track("", "B", 1));
    }

    @Test
    public void testInvalidDeparture() {
        assertThrows(AssertionError.class, () -> new track("Invalid123", "B", 1));
    }

    @Test
    public void testNullArrival() {
        assertThrows(AssertionError.class, () -> new track("A", null, 1));
    }

    @Test
    public void testBlankArrival() {
        assertThrows(AssertionError.class, () -> new track("A", "", 1));
    }

    @Test
    public void testInvalidArrival() {
        assertThrows(AssertionError.class, () -> new track("A", "Invalid123", 1));
    }

    @Test
    public void testInvalidDuration() {
        assertThrows(AssertionError.class, () -> new track("A", "B", -1));
    }

    @Test
    public void testToString() {
        track track = new track("A", "B", 1);
        Assertions.assertEquals("track{departure='A', arrival='B', duration=1}", track.toString());
    }
}
