package com.example.FireFly_frontend.clients;

import com.example.FireFly_frontend.dtos.FinalProductDTO;
import com.example.FireFly_frontend.dtos.FinalProductNeedDTO;
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

@FeignClient(name = "fire-fly-finalProductNeed", url = "${backend.base-url}/finalProductNeed")
public interface FinalProductNeedClient {

    @PostMapping("/save")
    FinalProductNeedDTO save(@RequestParam Long finalProductId, @RequestParam Long midProductId, @RequestParam int quantity, @RequestHeader("Authorization") String auth);

    @GetMapping("/all/{id}")
    List<FinalProductNeedDTO> findAllForFinalProduct(@PathVariable Long id, @RequestHeader("Authorization") String auth);

    @GetMapping("/cost/{id}")
    Double calculateCost(@PathVariable Long id, @RequestHeader("Authorization") String auth);

    @PutMapping("/edit/{id}")
    FinalProductNeedDTO update(@PathVariable Long id, @RequestBody FinalProductNeedDTO finalProductNeedDTO, @RequestHeader("Authorization") String auth);

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable Long id, @RequestHeader("Authorization") String auth);

    @GetMapping("/findById/{id}")
    FinalProductNeedDTO findById(@PathVariable Long id, @RequestHeader("Authorization") String auth);

}
