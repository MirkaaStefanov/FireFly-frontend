package com.example.FireFly_frontend.clients;

import com.example.FireFly_frontend.dtos.FinalProductDTO;
import com.example.FireFly_frontend.dtos.MidProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "fire-fly-midProduct", url = "${backend.base-url}/midProduct")
public interface MidProductClient {
    @PostMapping("/save")
    MidProductDTO save(@RequestBody MidProductDTO productDTO);

    @GetMapping("/all")
    List<MidProductDTO> findAll();

}
