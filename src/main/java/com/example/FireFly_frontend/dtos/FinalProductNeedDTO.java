package com.example.FireFly_frontend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinalProductNeedDTO {

    public Long id;
    private FinalProductDTO finalProduct;
    private MidProductDTO midProduct;
    private int quantity;
    private boolean deleted;

}
