package com.example.FireFly_frontend.controllers;

import com.example.FireFly_frontend.clients.FirstProductClient;
import com.example.FireFly_frontend.dtos.FirstProductDTO;
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
@RequestMapping("/firstProduct")
public class FirstProductController {

    private final FirstProductClient firstProductClient;

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new FirstProductDTO());
        return "FirstProduct/form";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute FirstProductDTO product) throws IOException {
        byte[] fileBytes = product.getMultipartFile().getBytes();
        String encodedImage = Base64.getEncoder().encodeToString(fileBytes);
        product.setImage(encodedImage);
        firstProductClient.save(product);
        return "redirect:/firstProduct/all";
    }

    @GetMapping("/all")
    public String all(Model model) {
        List<FirstProductDTO> products = firstProductClient.findAll();
        model.addAttribute("products", products);
        return "FirstProduct/all";
    }
}
