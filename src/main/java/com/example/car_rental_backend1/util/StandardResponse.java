package com.example.car_rental_backend1.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class StandardResponse {
    private int code;
    private String massage;
    private Object data;
}
