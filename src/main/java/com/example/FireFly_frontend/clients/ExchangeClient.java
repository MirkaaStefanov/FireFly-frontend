package com.example.FireFly_frontend.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "fire-fly-exchange", url = "${backend.base-url}/exchange")
public interface ExchangeClient {

    @GetMapping("/try")
    Double exchangeEuroToTRY();

}
