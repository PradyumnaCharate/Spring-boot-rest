package com.pmc.ecommerce.dto;

import lombok.Data;

@Data
public class PurchaseResponse {
    //adding final so that lombok will generate constructor for this.
    private final String orderTrackingNumber;

}
