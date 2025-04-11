package com.example.FireFly_frontend.clients;

import com.example.FireFly_frontend.dtos.FinalProductNeedDTO;
import com.example.FireFly_frontend.dtos.MidProductNeedDTO;
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

import java.io.IOException;
import java.util.List;

@FeignClient(name = "fire-fly-midProductNeed", url = "${backend.base-url}/midProductNeed")
public interface MidProductNeedClient {
    @PostMapping("/save")
    MidProductNeedDTO save(@RequestParam Long midProductId, @RequestParam Long firstProductId, @RequestParam int quantity, @RequestHeader("Authorization") String auth);

    @GetMapping("/all/{id}")
    List<MidProductNeedDTO> findAllForFinalProduct(@PathVariable Long id, @RequestHeader("Authorization") String auth);

    @GetMapping("/cost/{id}")
    Double calculateCost(@PathVariable Long id, @RequestHeader("Authorization") String auth);

    @PutMapping("/edit/{id}")
    MidProductNeedDTO update(@PathVariable Long id, @RequestBody MidProductNeedDTO midProductNeedDTO, @RequestHeader("Authorization") String auth);

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable Long id, @RequestHeader("Authorization") String auth);

    @GetMapping("/findById/{id}")
    MidProductNeedDTO findById(@PathVariable Long id, @RequestHeader("Authorization") String auth);

}
