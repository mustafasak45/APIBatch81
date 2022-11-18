package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoRestMetaPojo {
    /*    {
            "meta": null,
            "data": {
                "id": 2508,
                "name": "Sharmila Deshpande VM",
                "email": "deshpande_sharmila_vm@becker.name",
                "gender": "female",
                "status": "active"
    }
    }*/
    private String meta;
    private GoRestDataPojo data;

    public GoRestMetaPojo(String meta, GoRestDataPojo data) {
        this.meta = meta;
        this.data = data;
    }

    public GoRestMetaPojo() {
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public GoRestDataPojo getData() {
        return data;
    }

    public void setData(GoRestDataPojo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GoRestMetaPojo{" +
                "meta='" + meta + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
