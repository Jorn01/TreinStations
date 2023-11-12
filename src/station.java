import java.util.regex.Pattern;

public class station {
    private int id;
    private String code;
    private String name_Medium;
    private String country;
    private double latitude;
    private double longitude;

    public station(int id, String code, String name_Medium, String country, double latitude, double longitude) {
        Pattern codePattern = Pattern.compile("[A-Z]{1,6}");

        assert id > 0 : "id must be greater than 0";
        assert code != null : "code must not be null";
        assert !code.isBlank() : "code must not be blank";
        assert codePattern.matcher(code).matches() : "code must be a string with 1 to 5 characters";
        assert name_Medium != null : "name_Medium must not be null";
        assert !name_Medium.isBlank() : "name_Medium must not be blank";
        assert country != null : "country must not be null";
        assert !country.isBlank() : "country must not be blank";
        assert country.length() <= 4;
        assert latitude > -90 && latitude < 90;
        assert longitude > -180 && longitude < 180;
        this.name_Medium = name_Medium.replace("\"", "").replace("'", "");

        this.id = id;
        this.code = code;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    public String getCode() {
        return code;
    }

    public String getCountry() {
        return country;
    }

    public String getName_Medium() {
        return name_Medium;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "station{" +
                "id=" + id +
                ", code='" + getCode() + '\'' +
                ", name_Medium='" + getName_Medium() + '\'' +
                ", country='" + getCountry() + '\'' +
                ", latitude=" + getLatitude() +
                ", longitude=" + getLongitude() +
                '}';
    }
}
