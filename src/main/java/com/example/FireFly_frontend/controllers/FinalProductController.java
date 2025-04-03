package com.example.FireFly_frontend.controllers;

import com.example.FireFly_frontend.clients.ExchangeClient;
import com.example.FireFly_frontend.clients.FinalProductClient;
import com.example.FireFly_frontend.clients.MidProductClient;
import com.example.FireFly_frontend.dtos.FinalProductDTO;
import com.example.FireFly_frontend.dtos.MidProductDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/finalProduct")
public class FinalProductController {
    private final FinalProductClient finalProductClient;
    private final ExchangeClient exchangeClient;

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new FinalProductDTO());
        return "FinalProduct/form";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute FinalProductDTO product,HttpServletRequest request) throws IOException {
        String token = (String) request.getSession().getAttribute("sessionToken");
        byte[] fileBytes = product.getMultipartFile().getBytes();
        String encodedImage = Base64.getEncoder().encodeToString(fileBytes);
        product.setImage(encodedImage);
        finalProductClient.save(product,token);
        return "redirect:/finalProduct/all";
    }

    @GetMapping("/all")
    public String all(Model model, HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute("sessionToken");
        Double tryExchangeRate = exchangeClient.exchangeEuroToTRY();
        List<FinalProductDTO> products = finalProductClient.findAll(token);
        model.addAttribute("products", products);
        model.addAttribute("exchangeRate", tryExchangeRate);
        return "FinalProduct/all";
    }
}
