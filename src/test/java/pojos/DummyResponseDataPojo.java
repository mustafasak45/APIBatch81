package pojos;

public class DummyResponseDataPojo {
    private String status;
    private DummyRestApiDataPojo data;
    private String message;

    public DummyResponseDataPojo() {
    }

    public DummyResponseDataPojo(String status, DummyRestApiDataPojo data,String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DummyRestApiDataPojo getData() {
        return data;
    }

    public void setData(DummyRestApiDataPojo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DummyResponseDataPojo{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
