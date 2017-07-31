package hr.mstuban.homeexchange.domain;

/**
 * Created by mstuban on 31/07/17.
 */
public class Country {

    private String name;

    private String code;

    public Country(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
