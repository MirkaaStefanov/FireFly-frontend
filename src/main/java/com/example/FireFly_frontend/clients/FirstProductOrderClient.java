package com.example.FireFly_frontend.clients;

import com.example.FireFly_frontend.dtos.FirstProductOrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "fire-fly-firstProductOrder", url = "${backend.base-url}/firstProductOrder")
public interface FirstProductOrderClient {

    @GetMapping("/all")
    List<FirstProductOrderDTO> findAllFirstProductOrders(@RequestHeader("Authorization") String auth);
}
