package com.example.FireFly_frontend.clients;


import com.example.FireFly_frontend.dtos.FirstProductDTO;
import com.example.FireFly_frontend.dtos.MidProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "fire-fly-firstProduct", url = "${backend.base-url}/firstProduct")
public interface FirstProductClient {

    @PostMapping("/save")
    FirstProductDTO save(@RequestBody FirstProductDTO productDTO,@RequestHeader("Authorization") String auth);

    @GetMapping("/all")
    List<FirstProductDTO> findAll(@RequestHeader("Authorization") String auth);

    @GetMapping("/findById/{id}")
    FirstProductDTO findById(@RequestParam Long id,@RequestHeader("Authorization") String auth);

    @PutMapping("/edit/{id}")
    FirstProductDTO update(@RequestParam Long id, @RequestBody FirstProductDTO firstProductDTO, @RequestHeader("Authorization") String auth);

    @DeleteMapping("/delete/{id}")
    void delete(@RequestParam Long id, @RequestHeader("Authorization") String auth);
}
