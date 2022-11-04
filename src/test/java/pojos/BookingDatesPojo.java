package pojos;

public class BookingDatesPojo {

    //Tüm Keyler icin variable'lar olusturuyoruz

    private String checkin;
    private String checkout;

    //Tüm parametrelilerle ve parametresiz construstor olusturuyoruz

    public BookingDatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }
    public BookingDatesPojo() {
    }

    //3) Public Getter ve Setter methodlarını olusturuyoruz

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    //4) toString() methodunu olusturuyoruz

    @Override
    public String toString() {
        return "BookingDatesPojo" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'';
    }
}
