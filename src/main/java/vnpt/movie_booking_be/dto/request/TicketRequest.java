package vnpt.movie_booking_be.dto.request;

public class TicketRequest {
    private String orderInfo;
    private String totalPrice;
  //  private String paymentTime;
    private String transactionId;
    private String moviename;
    private String ngaychiu;
    private String giochiu;
    private String giomua;
    private String userName;
    private String phong;
    private String ghe;

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

//    public String getPaymentTime() {
//        return paymentTime;
//    }
//
//    public void setPaymentTime(String paymentTime) {
//        this.paymentTime = paymentTime;
//    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public String getNgaychiu() {
        return ngaychiu;
    }

    public void setNgaychiu(String ngaychiu) {
        this.ngaychiu = ngaychiu;
    }

    public String getGiochiu() {
        return giochiu;
    }

    public void setGiochiu(String giochiu) {
        this.giochiu = giochiu;
    }

    public String getGiomua() {
        return giomua;
    }

    public void setGiomua(String giomua) {
        this.giomua = giomua;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public String getGhe() {
        return ghe;
    }

    public void setGhe(String ghe) {
        this.ghe = ghe;
    }
}
