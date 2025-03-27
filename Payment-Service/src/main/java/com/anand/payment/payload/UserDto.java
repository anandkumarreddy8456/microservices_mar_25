package com.anand.payment.payload;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String fullName;
    private String email;
}
