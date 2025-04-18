package com.example.FireFly_frontend.clients;

import com.example.FireFly_frontend.dtos.FinalProductDTO;
import com.example.FireFly_frontend.dtos.MidProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    MidProductDTO findById(@PathVariable Long id,@RequestHeader("Authorization") String auth);

    @PutMapping("/edit/{id}")
    MidProductDTO update(@PathVariable Long id, @RequestBody MidProductDTO midProductDTO, @RequestHeader("Authorization") String auth);

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable Long id, @RequestHeader("Authorization") String auth);
}
