package vnpt.movie_booking_be.controller;

import com.google.zxing.WriterException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vnpt.movie_booking_be.dto.response.SeatResponse;
import vnpt.movie_booking_be.dto.response.TicketResponse;
import vnpt.movie_booking_be.dto.response.Urlrespone;
import vnpt.movie_booking_be.dto.response.VourcherRespone;
import vnpt.movie_booking_be.models.Ticket;
import vnpt.movie_booking_be.service.QRCODE;
import vnpt.movie_booking_be.service.VNPayServiceimpl;
import vnpt.movie_booking_be.service.VourcherService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class VNpayController {
    @Autowired
    private VNPayServiceimpl vnPayService;
    @Autowired
    private QRCODE qrcode;
    @Autowired
    private VourcherService voucherService;

    @GetMapping("")
    public String home() {
        return "index";
    }
    @PostMapping("/submitOrder")
    public ResponseEntity<String> submitOrder(@RequestParam("amount") int orderTotal,  // don gia
                              @RequestParam("seatIds") List<Integer> seatIds,  // lay ra tenghe
                              @RequestParam("screeningId") int screeningId,  // -> phongid, name id
                              @RequestParam("userId") int userId,  // user name
                              @RequestParam("movieId") int movieId,
                                              @RequestParam("voucherID") int vourcherID,// movie name
                              HttpServletRequest request) {
        // Xử lý các seatIds để tạo chuỗi seat_ID
        StringBuilder seatIDBuilder = new StringBuilder();
        for (Integer seatId : seatIds) {
            seatIDBuilder.append(seatId).append(",");
        }
        String seat_ID ="";
        if (seatIDBuilder.length() > 0) {
            seatIDBuilder.setLength(seatIDBuilder.length() - 1);
            seat_ID = seatIDBuilder.toString();
        }


        // Tạo orderInfo từ các tham số đầu vào
        String orderInfo = movieId + "/" + screeningId + "/" + userId + "/" + orderTotal +"/" +vourcherID +"/" + seat_ID;

        // Xác định baseUrl
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = vnPayService.createOrder(orderTotal, orderInfo, baseUrl);

        // Chuyển hướng người dùng đến vnpayUrl
        return ResponseEntity.ok(vnpayUrl) ;
    }
//    @PostMapping("/submitOrder")
//    public ResponseEntity<Urlrespone> submitOrder(@RequestParam("amount") int orderTotal,  // don gia
//                                                  @RequestParam("seatIds") List<Integer> seatIds,  // lay ra tenghe
//                                                  @RequestParam("screeningId") int screeningId,  // -> phongid, name id
//                                                  @RequestParam("userId") int userId,  // user name
//                                                  @RequestParam("movieId") int movieId,  // movie name
//                                                  HttpServletRequest request) {
//        // Xử lý các seatIds để tạo chuỗi seat_ID
//        StringBuilder seatIDBuilder = new StringBuilder();
//        for (Integer seatId : seatIds) {
//            seatIDBuilder.append(seatId).append(",");
//        }
//        String seat_ID ="";
//        if (seatIDBuilder.length() > 0) {
//            seatIDBuilder.setLength(seatIDBuilder.length() - 1);
//            seat_ID = seatIDBuilder.toString();
//        }
//
//
//        // Tạo orderInfo từ các tham số đầu vào
//        String orderInfo = movieId + "/" + screeningId + "/" + userId + "/" + orderTotal + "/" + seat_ID;
//
//        // Xác định baseUrl
//        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
//        String vnpayUrl = vnPayService.createOrder(orderTotal, orderInfo, baseUrl);
//        Urlrespone response = new Urlrespone(vnpayUrl);
//
//        // Chuyển hướng người dùng đến vnpayUrl
//        return ResponseEntity.ok(response);
//
//        // Chuyển hướng người dùng đến vnpayUrl
//       // return "redirect:" + vnpayUrl;
//    }
    @GetMapping("/vnpay-payment")
    public String handlePayment(HttpServletRequest request, Model model) throws IOException, WriterException {
        int paymentStatus = vnPayService.orderReturn(request);
        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");
if(paymentStatus == 1)  // thanh toan thành công
{

        // Lấy các thông tin từ request


        // Chuyển đổi giá trị totalPrice
        if (totalPrice != null && totalPrice.length() > 2) {
            totalPrice = totalPrice.substring(0, totalPrice.length() - 2);
        }

        // Xây dựng URL cho QR code
        StringBuffer url = request.getRequestURL();
        String queryString = request.getQueryString();
        if (queryString != null) {
            url.append("?").append(queryString);
        }
        String urlqr = url.toString();
        String qrCode = qrcode.generateQRCodeAndUpload(urlqr, 300, 300);

        // Tách orderInfo để lấy các thông tin cần thiết
        String[] infoParts = orderInfo.split("/");
        String movieID = infoParts.length > 0 ? infoParts[0] : "";
        String screeningID = infoParts.length > 1 ? infoParts[1] : "";
        String userID = infoParts.length > 2 ? infoParts[2] : "";
        String orderTotal = infoParts.length > 3 ? infoParts[3] : "";
    String Vourcher_ID = infoParts.length > 4 ? infoParts[4] : "";
        String seat_ID = infoParts.length > 5 ? infoParts[5] : "";

        int movieId = Integer.parseInt(movieID);
        int screeningId = Integer.parseInt(screeningID);
        int userId = Integer.parseInt(userID);
        int orderTotalInt = Integer.parseInt(orderTotal);
        int Voucher_Id = Integer.parseInt(Vourcher_ID);
        vnPayService.updateUserMembershipByRankPrice(userId,orderTotalInt);  // thanh toan ok nhận vào user id và tiền đơn hàng cập nhật lại tiền user đã dùng , cập nhật lại membership

        // Xử lý danh sách seat IDs
        String[] listSeatID = seat_ID.split(",");
        List<Integer> listSeatIds = new ArrayList<>();
        for (String seatID : listSeatID) {
            try {
                int seatIdInt = Integer.parseInt(seatID);
                listSeatIds.add(seatIdInt);
            } catch (NumberFormatException e) {
                System.err.println("Invalid seat ID: " + seatID);
            }
        }
        // Tạo vé và cập nhật QR code
        Ticket ticket = vnPayService.createTicket(orderTotalInt, listSeatIds, screeningId, userId, movieId,Voucher_Id);
        int ticketId = ticket.getId();
        vnPayService.updateQRCodeByTicketId(ticketId, qrCode);
        TicketResponse ticketResponse = vnPayService.getTicketById(ticketId);
        String userName = ticketResponse.getUserName();
        String movieTitile = ticketResponse.getMovieTitle();
        String audi = ticketResponse.getAuditoriumName();
        LocalDate date = ticketResponse.getScreeningDate();
        LocalTime time = ticketResponse.getScreeningStartTime();
        List<SeatResponse> seatResponses = ticketResponse.getSeats();
        String ghe = "";
        for (SeatResponse seatResponse : seatResponses) {
            ghe += seatResponse.getRow_Seat() + seatResponse.getNumber_Seat() +",";
        }
        int total = ticketResponse.getTotal();
        String qrcode = ticketResponse.getQrcode();
        // Thêm các thông tin vào model để hiển thị trong view
    model.addAttribute("userName", userName);
        model.addAttribute("giochiu", time);
        model.addAttribute("ghe", ghe);
        model.addAttribute("phong", audi);
        model.addAttribute("moviename", movieTitile);
        model.addAttribute("ngaychiu", date);
        model.addAttribute("totalPrice", total);
        model.addAttribute("phong", audi);
        model.addAttribute("qrcode", qrcode);}

        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("paymentTime", paymentTime);
        model.addAttribute("transactionId", transactionId);
        return paymentStatus == 1 ? "ordersuccess" : "orderfail";

    }
}

