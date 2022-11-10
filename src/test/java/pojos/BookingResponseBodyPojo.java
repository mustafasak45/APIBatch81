package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)//Bu annotation aynı levelde bilinmeyen verileri  görmezden gelerek diğer verilerin bu class tipinde Pojo class'a çevrilmesine yarıyor.
public class BookingResponseBodyPojo {
 /*   {
        "bookingid": 16,
         "booking" :{
        "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
            "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
        }
        "additionalneeds": "Breakfast"
    }
    }*/
    private Integer bookingid;
    private BookingPojo booking;

    public BookingResponseBodyPojo() {
    }

    public BookingResponseBodyPojo(Integer bookingid, BookingPojo booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public BookingPojo getBooking() {
        return booking;
    }

    public void setBooking(BookingPojo booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "BookingResponseBodyPojo{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
