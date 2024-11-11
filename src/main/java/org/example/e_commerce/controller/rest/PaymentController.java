package org.example.e_commerce.controller.rest;

import lombok.AllArgsConstructor;
import org.example.e_commerce.model.Payment;
import org.example.e_commerce.service.PaymentService;
import org.example.e_commerce.util.ReponseApi;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class PaymentController {

    private final PaymentService paymentService;


    @GetMapping("/payments")
    public List<Payment> getPayments() {
        return paymentService.getObjects();
    }

    @GetMapping("/payments/{id}")
    public Payment getPayment(@PathVariable Long id) {
        return paymentService.getOneObject(id);
    }

    //TODO Implement Stripe Here
    @PostMapping("/payments")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Payment postPayment(@RequestBody Payment payment) {
        return paymentService.postObjectOrUpdate(payment);
    }

    @DeleteMapping("/payments/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletePayment(@PathVariable Long id) {
        paymentService.deleteObject(id);
    }

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ReponseApi<String> paymentNotFoundException() {
        return new ReponseApi<>(null, "Payment Not Found", null);
    }

}
