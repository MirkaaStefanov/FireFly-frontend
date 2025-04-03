package com.example.FireFly_frontend.dtos;

import com.example.FireFly_frontend.enums.MaterialType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FirstProductDTO {


    private Long id;
    private String image;
    private String name;
    private String description;
    private double price;
    @JsonIgnore
    private double tryPrice;

    private int quantity;
    private MaterialType materialType;
    @JsonIgnore
    private MultipartFile multipartFile;

}
