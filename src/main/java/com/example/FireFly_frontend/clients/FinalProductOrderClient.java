package com.example.FireFly_frontend.clients;

import com.example.FireFly_frontend.dtos.FinalProductOrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "fire-fly-finalProductOrder", url = "${backend.base-url}/finalProductOrder")
public interface FinalProductOrderClient {

    @PostMapping("/create")
    void createFinalProductOrder(@RequestParam Long finalProductId, @RequestParam int requiredQuantity, @RequestHeader("Authorization") String auth);

    @GetMapping("/all")
    List<FinalProductOrderDTO> findAllFinalProductOrders(@RequestHeader("Authorization") String auth);


}
