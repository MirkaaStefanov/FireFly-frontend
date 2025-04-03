package com.example.FireFly_frontend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MidProductOrderDTO {

    private Long id;
    private MidProductDTO midProduct;
    private int quantity;
    private boolean done;
    private boolean deleted;
}
