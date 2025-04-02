package com.example.FireFly_frontend.clients;


import com.example.FireFly_frontend.dtos.FirstProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "fire-fly-firstProduct", url = "${backend.base-url}/firstProduct")
public interface FirstProductClient {

    @PostMapping("/save")
    FirstProductDTO save(@RequestBody FirstProductDTO productDTO);

    @GetMapping("/all")
    List<FirstProductDTO> findAll();

    @GetMapping("/findById/{id}")
    FirstProductDTO findById(@RequestParam Long id);
}
