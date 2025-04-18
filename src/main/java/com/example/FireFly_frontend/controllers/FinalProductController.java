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
import org.springframework.web.bind.annotation.PathVariable;
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
    public String submit(@ModelAttribute FinalProductDTO product, HttpServletRequest request) throws IOException {
        String token = (String) request.getSession().getAttribute("sessionToken");
        byte[] fileBytes = product.getMultipartFile().getBytes();
        String encodedImage = Base64.getEncoder().encodeToString(fileBytes);
        product.setImage(encodedImage);
        finalProductClient.save(product, token);
        return "redirect:/finalProduct/all";
    }

    @GetMapping("/all")
    public String all(Model model, HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute("sessionToken");
        Double tryExchangeRate = exchangeClient.exchangeEuroToTRY();
        List<FinalProductDTO> products = finalProductClient.findAll(token);
        for (FinalProductDTO finalProduct : products) {
            finalProduct.setTryPrice(finalProduct.getPrice() * tryExchangeRate);
        }
        model.addAttribute("products", products);
        model.addAttribute("exchangeRate", tryExchangeRate);
        return "FinalProduct/all";
    }

    @GetMapping("/edit/{id}")
    public String update(@PathVariable Long id, Model model, HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute("sessionToken");
        FinalProductDTO finalProductDTO = finalProductClient.findById(id, token);
        model.addAttribute("product", finalProductDTO);
        return "FinalProduct/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateSubmit(@PathVariable Long id, @ModelAttribute FinalProductDTO product, HttpServletRequest request) throws IOException {
        String token = (String) request.getSession().getAttribute("sessionToken");

        FinalProductDTO existingProduct = finalProductClient.findById(id, token);

        if (product.getMultipartFile() != null && !product.getMultipartFile().isEmpty()) {
            byte[] fileBytes = product.getMultipartFile().getBytes();
            String encodedImage = Base64.getEncoder().encodeToString(fileBytes);
            product.setImage(encodedImage);
        } else {

            product.setImage(existingProduct.getImage());
        }

        finalProductClient.update(id, product, token);
        return "redirect:/finalProduct/all";
    }

    @PostMapping("/delete/{id}")
    public String updateSubmit(@PathVariable Long id, HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute("sessionToken");
        finalProductClient.delete(id, token);
        return "redirect:/finalProduct/all";
    }

}
