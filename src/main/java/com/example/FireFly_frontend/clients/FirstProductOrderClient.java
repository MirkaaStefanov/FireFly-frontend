package com.example.FireFly_frontend.clients;

import com.example.FireFly_frontend.dtos.FirstProductOrderDTO;
import com.example.FireFly_frontend.enums.MaterialType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "fire-fly-firstProductOrder", url = "${backend.base-url}/firstProductOrder")
public interface FirstProductOrderClient {

    @PostMapping("/create")
    void createFirstProductOrder(@RequestParam Long firstProductId, @RequestParam int requiredQuantity, @RequestHeader("Authorization") String auth);

    @GetMapping("/all")
    List<FirstProductOrderDTO> findAllFirstProductOrders(@RequestHeader("Authorization") String auth);

    @GetMapping("/allByMaterialType")
    List<FirstProductOrderDTO> findAllFirstProductOrders(@RequestParam MaterialType materialType, @RequestHeader("Authorization") String auth);

}
