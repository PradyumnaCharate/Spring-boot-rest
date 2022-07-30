package com.pmc.ecommerce.service;

import com.pmc.ecommerce.dto.Purchase;
import com.pmc.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    //place order method will take Purchase DTO (from frontend) as argument and will return response of
    //dto purchase response
    PurchaseResponse placeOrder(Purchase purchase);
}
