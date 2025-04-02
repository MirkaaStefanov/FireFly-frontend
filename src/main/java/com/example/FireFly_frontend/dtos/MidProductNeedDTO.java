package com.example.FireFly_frontend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MidProductNeedDTO {

    private Long id;
    private MidProductDTO midProduct;
    private FirstProductDTO firstProduct;
    private int quantity;

}
