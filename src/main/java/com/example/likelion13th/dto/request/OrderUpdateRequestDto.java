package com.example.likelion13th.dto.request;

import com.example.likelion13th.enums.DeliverStatus;
import lombok.Getter;

@Getter
public class OrderUpdateRequestDto {
    private DeliverStatus deliverStatus;
    private String recipient;
    private String phonenumber;
    private String streetaddress;
    private String detailedaddress;
    private String postalCode;
}
