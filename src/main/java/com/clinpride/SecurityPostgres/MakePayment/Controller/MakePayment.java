package com.clinpride.SecurityPostgres.MakePayment.Controller;

import com.clinpride.SecurityPostgres.InviteUser.models.InviteUserModel;
import com.clinpride.SecurityPostgres.MakePayment.Model.MakePaymentModel;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import zw.co.paynow.constants.MobileMoneyMethod;
import zw.co.paynow.core.Paynow;
import zw.co.paynow.core.Paynow;
import zw.co.paynow.core.Payment;
import zw.co.paynow.responses.MobileInitResponse;
import zw.co.paynow.responses.StatusResponse;
import zw.co.paynow.responses.WebInitResponse;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
@RestController

@AllArgsConstructor
@RequestMapping("/api/v1/make-payment")
public class MakePayment {
    @PostMapping("/test-me")
    public void sendInvite(@RequestBody MakePaymentModel makePaymentModel) {
        Paynow paynow = new Paynow("16739", "7d15ff0f-9a44-4767-897f-f6b41a33fcc5");
        Payment payment = paynow.createPayment("Invoice 32", "zpmakaza@gmail.com");

        // Passing in the name of the item and the price of the item
        payment.add("Bananas", 2.5);
        payment.add("Apples", 3.4);

        //Initiating the transaction
        //WebInitResponse response = paynow.send(payment);

        //If a mobile transaction,
        MobileInitResponse response = paynow.sendMobile(payment, "0771234567", MobileMoneyMethod.ECOCASH);

        if (response.success()) {
            // Get the instructions to show to the user
            String instructions  = response.instructions();

            // Get the poll URL of the transaction
            String pollUrl = response.pollUrl();
            System.out.println(pollUrl);
            // Check the status of the transaction with the specified pollUrl
            StatusResponse status = paynow.pollTransaction(pollUrl);

            if (status.isPaid()) {
                // Yay! Transaction was paid for
            } else {
                System.out.println("Why you no pay?");
            }
        } else {
            // Something went wrong
            System.out.println(response.errors());
        }
    }
}
