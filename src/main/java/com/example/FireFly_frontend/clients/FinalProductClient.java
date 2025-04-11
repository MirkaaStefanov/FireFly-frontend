package com.example.FireFly_frontend.clients;

import com.example.FireFly_frontend.dtos.FinalProductDTO;
import com.example.FireFly_frontend.dtos.FirstProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "fire-fly-finalProduct", url = "${backend.base-url}/finalProduct")
public interface FinalProductClient {

    @PostMapping("/save")
    FinalProductDTO save(@RequestBody FinalProductDTO productDTO, @RequestHeader("Authorization") String auth);

    @GetMapping("/all")
    List<FinalProductDTO> findAll(@RequestHeader("Authorization") String auth);

    @GetMapping("/findById/{id}")
    FinalProductDTO findById(@PathVariable Long id, @RequestHeader("Authorization") String auth);

    @PutMapping("/edit/{id}")
    FinalProductDTO update(@PathVariable Long id, @RequestBody FinalProductDTO finalProductDTO, @RequestHeader("Authorization") String auth);

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable Long id, @RequestHeader("Authorization") String auth);

}
