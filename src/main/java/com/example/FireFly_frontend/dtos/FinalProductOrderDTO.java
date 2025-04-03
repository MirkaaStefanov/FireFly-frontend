package com.example.FireFly_frontend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinalProductOrderDTO {

    private Long id;
    private FinalProductDTO finalProduct;
    private int quantity;
    private boolean done;
    private boolean deleted;

}
