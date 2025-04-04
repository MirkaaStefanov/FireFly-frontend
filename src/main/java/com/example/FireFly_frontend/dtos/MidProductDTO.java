package com.example.FireFly_frontend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MidProductDTO {
    private Long id;
    private String image;
    private String name;
    private String description;
    private double price;
    @JsonIgnore
    private double tryPrice;
    @JsonIgnore
    private double finalCost;
    @JsonIgnore
    private double tryFinalCost;
    private int quantity;
    @JsonIgnore
    private MultipartFile multipartFile;
    private boolean deleted;

}
