package com.project.paymentservice.controller;

import com.project.paymentservice.domain.OrderRequest;
import com.project.paymentservice.domain.OrderResponse;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/payment/")
//@CrossOrigin(origins = "http://localhost:4200")
public class RazorPayController {

    private RazorpayClient client;
    private static final String SECRET_ID = "rzp_test_9n1mVy389jphJH";
    private static final String SECRET_KEY = "LBl8F2fP8VBZueLCiodd9KkL";

    @PostMapping("createOrder")
    public OrderResponse createOrder(@RequestBody OrderRequest orderRequest) {
        OrderResponse response = new OrderResponse();
        try {
            client = new RazorpayClient(SECRET_ID, SECRET_KEY);

            Order order = createRazorPayOrder(orderRequest.getAmount());
            System.out.println("---------------------------");
            String orderId = (String) order.get("id");
            System.out.println("Order ID: " + orderId);
            System.out.println("---------------------------");
            response.setRazorpayOrderId(orderId);
            response.setApplicationFee("" + orderRequest.getAmount());

            response.setSecretKey(SECRET_KEY);
            response.setSecretId(SECRET_ID);
            response.setPgName("razor1");

            return response;
        } catch (RazorpayException e) {
            e.printStackTrace();
        }
        return response;
    }

    private Order createRazorPayOrder(BigInteger amount) throws RazorpayException {

        JSONObject options = new JSONObject();
        options.put("amount", amount.multiply(new BigInteger("100")));
        options.put("currency", "INR");
        //options.put("receipt", "txn_123456");
        options.put("payment_capture", 1); // You can enable this if you want to do Auto Capture.
        return client.orders.create(options);
    }
}
