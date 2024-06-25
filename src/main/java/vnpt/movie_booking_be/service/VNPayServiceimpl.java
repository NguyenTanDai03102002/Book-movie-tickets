package vnpt.movie_booking_be.service;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vnpt.movie_booking_be.config.VNPayConfig;
import vnpt.movie_booking_be.dto.response.SeatTicketResponse;
import vnpt.movie_booking_be.dto.response.TicketResponse;
import vnpt.movie_booking_be.mapper.TicketMapper;
import vnpt.movie_booking_be.models.*;
import vnpt.movie_booking_be.repository.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class VNPayServiceimpl  {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ScreeningRepository screeningRepository;
    @Autowired
    private AuditoriumRepository auditoriumRepository;
    public String createOrder(int total, String orderInfor, String urlReturn){
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String vnp_TxnRef = VNPayConfig.getRandomNumber(8);
        String vnp_IpAddr = "127.0.0.1";
        String vnp_TmnCode = VNPayConfig.vnp_TmnCode;
        String orderType = "order-type";

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(total*100));
        vnp_Params.put("vnp_CurrCode", "VND");

        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", orderInfor);
        vnp_Params.put("vnp_OrderType", orderType);

        String locate = "vn";
        vnp_Params.put("vnp_Locale", locate);

        urlReturn += VNPayConfig.vnp_Returnurl;
        vnp_Params.put("vnp_ReturnUrl", urlReturn);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                try {
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    //Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VNPayConfig.hmacSHA512(VNPayConfig.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VNPayConfig.vnp_PayUrl + "?" + queryUrl;
        return paymentUrl;
    }

    public int orderReturn(HttpServletRequest request){
        Map fields = new HashMap();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = null;
            String fieldValue = null;
            try {
                fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
                fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }
        String signValue = VNPayConfig.hashAllFields(fields);
        if (signValue.equals(vnp_SecureHash)) {
            if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return -1;
        }
    }


    //    @Autowired
//    private UserRepository userRepository;
    public Ticket createTicket(int total, List<Integer> seatIds, int screeningId, int userId, int movieId) {
        Ticket ticket = new Ticket();
        ticket.setTotal(total);
        ticket.setOrderTime(new Date());
        ticket.setStatus(0);
        ticket.setPaymentMethod(PaymentMethod.VNPAY);

        // Retrieve movie, screening, and user from their respective repositories
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Movie not found"));
        Screening screening = screeningRepository.findById(screeningId).orElseThrow(() -> new RuntimeException("Screening not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        ticket.setMovie(movie);
        ticket.setScreening(screening);
        ticket.setUser(user);

        Set<Seat> seats = new HashSet<>();
        for (Integer seatId : seatIds) {
            Seat seat = seatRepository.findById(seatId).orElseThrow(() -> new RuntimeException("Seat not found"));
            seats.add(seat);
        }
        ticket.setSeats(seats);

        ticket = ticketRepository.save(ticket);
        seatRepository.saveAll(seats); // Save seats after associating them with the ticket

        return ticket;
    }

    @Autowired
    private TicketMapper ticketMapper;

    public List<TicketResponse> getTicketsByUserId(int userId) {
        return ticketRepository.findByUserId(userId)
                .stream()
                .map(ticket -> ticketMapper.toTicketResponse(ticket))
                .collect(Collectors.toList());
    }
    public void deleteTicketById(int ticketId) {
        // Kiểm tra xem vé có tồn tại không trước khi xóa
        if (!ticketRepository.existsById(ticketId)) {
            throw new RuntimeException("Ticket not found");
        }

        // Xóa vé từ repository
        ticketRepository.deleteById(ticketId);
    }

    @Transactional
    public void updateQRCodeByTicketId(int ticketId, String qrCodeUrl) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + ticketId));

        ticket.setQrcode(qrCodeUrl);

        ticketRepository.save(ticket);
    }


    public TicketResponse getTicketById(int ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        return ticketMapper.toTicketResponse(ticket);
    }

}
