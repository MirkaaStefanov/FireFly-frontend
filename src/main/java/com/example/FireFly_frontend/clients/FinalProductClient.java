package com.example.FireFly_frontend.clients;

import com.example.FireFly_frontend.dtos.FinalProductDTO;
import com.example.FireFly_frontend.dtos.FirstProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "fire-fly-finalProduct", url = "${backend.base-url}/finalProduct")
public interface FinalProductClient {

    @PostMapping("/save")
    FinalProductDTO save(@RequestBody FinalProductDTO productDTO);

    @GetMapping("/all")
    List<FinalProductDTO> findAll();
}
