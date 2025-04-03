package com.example.FireFly_frontend.clients;

import com.example.FireFly_frontend.dtos.MidProductOrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "fire-fly-midProductOrder", url = "${backend.base-url}/midProductOrder")
public interface MidProductOrderClient {

    @PostMapping("/create")
    void createFromMidProductOrder(@RequestParam Long midProductId, @RequestParam int requiredQuantity, @RequestHeader("Authorization") String auth);

    @GetMapping("/all")
    List<MidProductOrderDTO> findAllMidProductOrders(@RequestHeader("Authorization") String auth);

}
