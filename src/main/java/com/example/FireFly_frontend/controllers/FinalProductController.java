package com.example.FireFly_frontend.controllers;

import com.example.FireFly_frontend.clients.FinalProductClient;
import com.example.FireFly_frontend.clients.MidProductClient;
import com.example.FireFly_frontend.dtos.FinalProductDTO;
import com.example.FireFly_frontend.dtos.MidProductDTO;
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
@RequestMapping("/finalProduct")
public class FinalProductController {
    private final FinalProductClient finalProductClient;

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new FinalProductDTO());
        return "FinalProduct/form";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute FinalProductDTO product) throws IOException {
        byte[] fileBytes = product.getMultipartFile().getBytes();
        String encodedImage = Base64.getEncoder().encodeToString(fileBytes);
        product.setImage(encodedImage);
        finalProductClient.save(product);
        return "redirect:/finalProduct/all";
    }

    @GetMapping("/all")
    public String all(Model model) {
        List<FinalProductDTO> products = finalProductClient.findAll();
        model.addAttribute("products", products);
        return "FinalProduct/all";
    }
}
