//package vnpt.movie_booking_be.controller;
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import vnpt.movie_booking_be.dto.request.TicketRequest;
//import vnpt.movie_booking_be.models.Ticket;
//import vnpt.movie_booking_be.service.VNPayServiceimpl;
//
//import java.util.Date;
//import java.util.List;
//
//@RestController
//public class TicketController {
//    @Autowired
//    private VNPayServiceimpl vnPayService;
//    @GetMapping("/vnpay-payment-app")
//    public TicketRequest handlePayment(HttpServletRequest request) {
//        int paymentStatus = vnPayService.orderReturn(request);
//
//        String orderInfo = request.getParameter("vnp_OrderInfo");
//        String paymentTime = request.getParameter("vnp_PayDate");
//        String transactionId = request.getParameter("vnp_TransactionNo");
//
//        String totalPrice = request.getParameter("vnp_Amount");
//        if (totalPrice != null && totalPrice.length() > 2) {
//            totalPrice = totalPrice.substring(0, totalPrice.length() - 2);
//        }
//
//        String[] infoParts = orderInfo.split("/");
//        String moviename = infoParts.length > 0 ? infoParts[0] : "";
//        String ngaychiu = infoParts.length > 1 ? infoParts[1] : "";
//        String giochiu = infoParts.length > 2 ? infoParts[2] : "";
//        String giomua = infoParts.length > 3 ? infoParts[3] : "";
//        String userName = infoParts.length > 4 ? infoParts[4] : "";
//        String phong = infoParts.length > 5 ? infoParts[5] : "";
//        String ghe = infoParts.length > 6 ? infoParts[6] : "";
//TicketRequest ticketRequest = new TicketRequest();
//
//        ticketRequest.setOrderInfo(orderInfo);
//        ticketRequest.setTotalPrice(totalPrice);
//        ticketRequest.setPaymentTime(paymentTime);
//        ticketRequest.setTransactionId(transactionId);
//        ticketRequest.setMoviename(moviename);
//        ticketRequest.setNgaychiu(ngaychiu);
//        ticketRequest.setGiochiu(giochiu);
//        ticketRequest.setGiomua(giomua);
//        ticketRequest.setUserName(userName);
//        ticketRequest.setPhong(phong);
//        ticketRequest.setGhe(ghe);
//
//        return ticketRequest;
//    }
//
//
//
//
//
//}
