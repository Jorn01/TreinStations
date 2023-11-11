import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StationTest {

    @Test
    public void testValidstation() {
        // Valid input values
        assertDoesNotThrow(() -> new station(1, "ABC", "Name", "NL", 45.0, 100.0));
    }

    @Test
    public void testInvalidId() {
        assertThrows(AssertionError.class, () -> new station(0, "ABC", "Name", "NL", 45.0, 100.0));
    }

    @Test
    public void testNullCode() {
        assertThrows(AssertionError.class, () -> new station(1, null, "Name", "NL", 45.0, 100.0));
    }

    @Test
    public void testBlankCode() {
        assertThrows(AssertionError.class, () -> new station(1, "", "Name", "NL", 45.0, 100.0));
    }

    @Test
    public void testInvalidCode() {
        assertThrows(AssertionError.class, () -> new station(1, "Invalid123", "Name", "NL", 45.0, 100.0));
    }

    @Test
    public void testNullNameMedium() {
        assertThrows(AssertionError.class, () -> new station(1, "ABC", null, "NL", 45.0, 100.0));
    }

    @Test
    public void testBlankNameMedium() {
        assertThrows(AssertionError.class, () -> new station(1, "ABC", "", "NL", 45.0, 100.0));
    }

    @Test
    public void testNullCountry() {
        assertThrows(AssertionError.class, () -> new station(1, "ABC", "Name", null, 45.0, 100.0));
    }

    @Test
    public void testBlankCountry() {
        assertThrows(AssertionError.class, () -> new station(1, "ABC", "Name", "", 45.0, 100.0));
    }

    @Test
    public void testInvalidCountryLength() {
        assertThrows(AssertionError.class, () -> new station(1, "ABC", "Name", "THISCOUNTRYNAMEISTOLONG", 45.0, 100.0));
    }

    @Test
    public void testInvalidLatitude() {
        assertThrows(AssertionError.class, () -> new station(1, "ABC", "Name", "NL", -91.0, 100.0));
    }

    @Test
    public void testInvalidLongitude() {
        assertThrows(AssertionError.class, () -> new station(1, "ABC", "Name", "NL", 45.0, 190.0));
    }

    @Test
    public void testToStringReturnsSomething() {
        station s = new station(1, "ABC", "Name", "NL", 45.0, 100.0);
        assertNotEquals("", s.toString());
    }

    @Test
    public void testToStringReturnsCorrectString() {
        station s = new station(1, "ABC", "Name", "NL", 45.0, 100.0);
        assertEquals("station{id=1, code='ABC', name_Medium='Name', country='NL', latitude=45.0, longitude=100.0}", s.toString());
    }

    @Test
    public void testGetCodeReturnsCorrectString() {
        station s = new station(1, "ABC", "Name", "NL", 45.0, 100.0);
        assertEquals("ABC", s.getCode());
    }
}
