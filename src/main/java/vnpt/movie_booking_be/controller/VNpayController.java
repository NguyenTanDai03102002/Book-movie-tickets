package vnpt.movie_booking_be.controller;


import com.google.zxing.WriterException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vnpt.movie_booking_be.dto.request.TicketRequest;
import vnpt.movie_booking_be.models.Movie;
import vnpt.movie_booking_be.models.Screening;
import vnpt.movie_booking_be.models.Ticket;
import vnpt.movie_booking_be.repository.MovieRepository;
import vnpt.movie_booking_be.repository.ScreeningRepository;
import vnpt.movie_booking_be.service.QRCODE;
import vnpt.movie_booking_be.service.VNPayServiceimpl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;


// @CrossOrigin(origins = "*")

@org.springframework.stereotype.Controller

//@CrossOrigin("*")
//@RestController
//@org.springframework.stereotype.Controller
public class VNpayController {
    @Autowired
    private VNPayServiceimpl vnPayService;
    @Autowired
    private QRCODE qrcode;
@Autowired
private MovieRepository movieRepository;


    @Autowired
    private ScreeningRepository screeningRepository;
    @GetMapping("")
    public String home() {
        return "index";
    }


@PostMapping("/submitOrder")
public String submitOrder(@RequestParam("amount") int orderTotal,
                          @RequestParam("seatIds") List<Integer> seatIds,
                          @RequestParam("screeningId") int screeningId,
                          @RequestParam("userId") int userId,
                          @RequestParam("movieId") int movieId,

//                          @RequestParam("cinema") String cinema,
//                          @RequestParam("showTime") String showTime,
                          HttpServletRequest request) {

    String moviename = vnPayService.getMovieTitleById(movieId);
    LocalDate date = vnPayService.getDateById(screeningId);
    LocalTime time = vnPayService.getStartTimeById(screeningId);
    LocalDateTime ordertime = LocalDateTime.now();
 String username = vnPayService.username(userId);
    String ghe = "";
    String phong ="";
    for (Integer seatId : seatIds) {
        ghe += vnPayService.nameseat(seatId) + " ,";
phong = vnPayService.audi(seatId);
    }
    if (!ghe.isEmpty()) {
        ghe = ghe.substring(0, ghe.length() - 1); // Xóa ký tự "/" cuối cùng
    }
    //List<String> seatNames = vnPayService.nameseat(seatIds);
    Ticket ticket = vnPayService.createTicket(orderTotal, seatIds, screeningId, userId, movieId);
    int ticketId = ticket.getId();
    HttpSession session = request.getSession();
    session.setAttribute("idticket", ticketId);
    session.setAttribute("moviename", moviename);
    session.setAttribute("date", date);
    session.setAttribute("time", time);
    session.setAttribute("timeorder", ordertime);

    String orderInfo = moviename+"/" + date.toString() + "/" + time.toString()+"/" +ordertime.toString()+"/"+username+"/"+phong +"/"+ticketId+"/"+ghe;

    String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
    String vnpayUrl = vnPayService.createOrder(orderTotal, orderInfo, baseUrl);
    return "redirect:" + vnpayUrl;
}
    @GetMapping("/vnpay-payment")
    public String handlePayment(HttpServletRequest request, Model model) throws IOException, WriterException {
        int paymentStatus = vnPayService.orderReturn(request);


//
        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
//
        String totalPrice = request.getParameter("vnp_Amount");
        if (totalPrice != null && totalPrice.length() > 2) {
            totalPrice = totalPrice.substring(0, totalPrice.length() - 2);
//            totalPrice = totalPrice+="VND ";
        }
        StringBuffer url = request.getRequestURL();


        String queryString = request.getQueryString();


        if (queryString != null) {
            url.append("?").append(queryString);
        }
String urlqr = url.toString();

      String qrco = qrcode.generateQRCodeAndUpload(urlqr,300,300);

        String[] infoParts = orderInfo.split("/");
        String moviename = infoParts.length > 0 ? infoParts[0] : "";
        String ngaychiu = infoParts.length > 1 ? infoParts[1] : "";
        String giochiu = infoParts.length > 2 ? infoParts[2] : "";
        String giomua = infoParts.length > 3 ? infoParts[3] : "";
        String userName = infoParts.length > 4 ? infoParts[4] : "";
        String phong = infoParts.length > 5 ? infoParts[5] : "";
        String ticketid = infoParts.length > 6 ? infoParts[6] : "";
        String ghe = infoParts.length > 7 ? infoParts[7] : "";
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("paymentTime", paymentTime);
        model.addAttribute("transactionId", transactionId);
        model.addAttribute("moviename", moviename);
        model.addAttribute("ngaychiu", ngaychiu);
        model.addAttribute("giochiu", giochiu);
        model.addAttribute("giomua", giomua);
        model.addAttribute("userName", userName);
        model.addAttribute("phong", phong);
        model.addAttribute("ghe", ghe);
        model.addAttribute("url", url.toString());
        model.addAttribute("qrco", qrco);
        vnPayService.updateQRCodeByTicketId(Integer.parseInt(ticketid),qrco);
        return paymentStatus == 1 ? "ordersuccess" : "orderfail";

    }

    @GetMapping("/vnpay-payment-app")
    public TicketRequest handleAppPayment(HttpServletRequest request) {
        int paymentStatus = vnPayService.orderReturn(request);

        String orderInfo = request.getParameter("vnp_OrderInfo");
      //  String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");

        String totalPrice = request.getParameter("vnp_Amount");
        if (totalPrice != null && totalPrice.length() > 2) {
            totalPrice = totalPrice.substring(0, totalPrice.length() - 2);
        }

        String[] infoParts = orderInfo.split("/");
        String moviename = infoParts.length > 0 ? infoParts[0] : "";
        String ngaychiu = infoParts.length > 1 ? infoParts[1] : "";
        String giochiu = infoParts.length > 2 ? infoParts[2] : "";
        String giomua = infoParts.length > 3 ? infoParts[3] : "";
        String userName = infoParts.length > 4 ? infoParts[4] : "";
        String phong = infoParts.length > 5 ? infoParts[5] : "";
        String ghe = infoParts.length > 6 ? infoParts[6] : "";
        TicketRequest ticketRequest = new TicketRequest();

        ticketRequest.setOrderInfo(orderInfo);
        ticketRequest.setTotalPrice(totalPrice);
      //  ticketRequest.setPaymentTime(paymentTime);
        ticketRequest.setTransactionId(transactionId);
        ticketRequest.setMoviename(moviename);
        ticketRequest.setNgaychiu(ngaychiu);
        ticketRequest.setGiochiu(giochiu);
        ticketRequest.setGiomua(giomua);
        ticketRequest.setUserName(userName);
        ticketRequest.setPhong(phong);
        ticketRequest.setGhe(ghe);

        return ticketRequest;
    }



}
