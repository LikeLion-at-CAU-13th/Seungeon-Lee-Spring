package com.example.likelion13th.dto.request;

import com.example.likelion13th.enums.DeliverStatus;
import lombok.Getter;

@Getter
public class OrderUpdateRequestDto {
    private DeliverStatus deliverStatus;
    private String recipient;
    private String phoneNumber;
    private String streetAddress;
    private String detailedAddress;
    private String postalCode;
}
