package com.ecom.novren.model;

import lombok.*;
@Data
public class PaymentDetails {

    private String paymentId;                       // Payment gateway transaction ID
    private String sslCommerzPaymentLinkId;        // SSLCommerz link ID
    private String sslCommerzPaymentLinkRefId;     // SSLCommerz reference ID
    private String sslCommerzPaymentLinkStatus;    // Status of the payment link
    private String sslCommerzPaymentLinkExtraId;   // Optional additional identifier
    private String status;                          // General status (e.g., SUCCESS, FAILED)
}
