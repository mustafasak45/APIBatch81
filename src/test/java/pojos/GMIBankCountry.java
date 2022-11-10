package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class GMIBankCountry {
/*    "country": {
                "id": 3,
                "name": "USA",
                "states": null
    }*/
    private Integer id;
    private String name;
    private  Boolean states;

    public GMIBankCountry() {
    }

    public GMIBankCountry(Integer id, String name, Boolean states) {
        this.id = id;
        this.name = name;
        this.states = states;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStates() {
        return states;
    }

    public void setStates(Boolean states) {
        this.states = states;
    }

    @Override
    public String toString() {
        return "GMIBankCountry{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", states=" + states +
                '}';
    }
}
