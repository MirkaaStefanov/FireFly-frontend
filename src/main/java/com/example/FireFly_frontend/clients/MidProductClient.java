package com.example.FireFly_frontend.clients;

import com.example.FireFly_frontend.dtos.FinalProductDTO;
import com.example.FireFly_frontend.dtos.MidProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "fire-fly-midProduct", url = "${backend.base-url}/midProduct")
public interface MidProductClient {
    @PostMapping("/save")
    MidProductDTO save(@RequestBody MidProductDTO productDTO,@RequestHeader("Authorization") String auth);

    @GetMapping("/all")
    List<MidProductDTO> findAll(@RequestHeader("Authorization") String auth);

    @GetMapping("/findById/{id}")
    MidProductDTO findById(@RequestParam Long id,@RequestHeader("Authorization") String auth);
}
