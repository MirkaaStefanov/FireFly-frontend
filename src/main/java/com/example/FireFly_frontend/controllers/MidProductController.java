package com.example.FireFly_frontend.controllers;

import com.example.FireFly_frontend.clients.ExchangeClient;
import com.example.FireFly_frontend.clients.FirstProductClient;
import com.example.FireFly_frontend.clients.MidProductClient;
import com.example.FireFly_frontend.dtos.FirstProductDTO;
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
import java.util.Base64;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/midProduct")
public class MidProductController {
    private final MidProductClient midProductClient;
    private final ExchangeClient exchangeClient;

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new MidProductDTO());
        return "MidProduct/form";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute MidProductDTO product, HttpServletRequest request) throws IOException {
        String token = (String) request.getSession().getAttribute("sessionToken");
        byte[] fileBytes = product.getMultipartFile().getBytes();
        String encodedImage = Base64.getEncoder().encodeToString(fileBytes);
        product.setImage(encodedImage);
        midProductClient.save(product,token);
        return "redirect:/midProduct/all";
    }

    @GetMapping("/all")
    public String all(Model model,HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute("sessionToken");
        Double tryExchangeRate = exchangeClient.exchangeEuroToTRY();
        List<MidProductDTO> products = midProductClient.findAll(token);
        for(MidProductDTO midProduct : products){
            midProduct.setTryPrice(midProduct.getPrice()*tryExchangeRate);
        }
        model.addAttribute("exchangeRate", tryExchangeRate);
        model.addAttribute("products", products);
        return "MidProduct/all";
    }
}
