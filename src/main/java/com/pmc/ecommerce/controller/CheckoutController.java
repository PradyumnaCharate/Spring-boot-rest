package com.pmc.ecommerce.controller;

import com.pmc.ecommerce.dto.Purchase;
import com.pmc.ecommerce.dto.PurchaseResponse;
import com.pmc.ecommerce.service.CheckoutService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {
    //we want to use method placeorder from checkout service so we should have that instance of checkoutService
    //so in constructor we are injecting that checkoutService whule creating this class object
    private CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    //we have purchase object coming from frontend in request body
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {

        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);

        return purchaseResponse;
    }

}









