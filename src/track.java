import java.util.regex.Pattern;

public class track {
    private String departure;
    private String arrival;
    private int duration;

    public track(String departure, String arrival, int duration) {
        Pattern departurePattern = Pattern.compile("^[a-zA-Z]{1,6}$");
        Pattern postcode = Pattern.compile("[1-9][0-9]{3}[A-Z]{2}");
        assert departure != null : "departure must not be null";
        assert !departure.isBlank() : "departure must not be blank";
        assert departurePattern.matcher(departure).matches() : "departure must be a string with 1 to 5 characters";
        Pattern arrivalPattern = Pattern.compile("^[a-zA-Z]{1,6}$");
        assert arrival != null : "arrival must not be null";
        assert !arrival.isBlank() : "arrival must not be blank";
        assert arrivalPattern.matcher(arrival).matches() : "arrival must be a string with 1 to 5 characters";
        assert duration >= 0 : "duration must be a positive integer";
        this.departure = departure;
        this.arrival = arrival;
        this.duration = duration;
    }

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "track{" + "departure='" + departure + '\'' + ", arrival='" + arrival + '\'' + ", duration=" + duration + '}';
    }
}
