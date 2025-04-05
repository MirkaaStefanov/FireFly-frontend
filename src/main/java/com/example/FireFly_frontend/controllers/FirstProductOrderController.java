package com.example.FireFly_frontend.controllers;

import com.example.FireFly_frontend.clients.FirstProductClient;
import com.example.FireFly_frontend.clients.FirstProductOrderClient;
import com.example.FireFly_frontend.dtos.FinalProductDTO;
import com.example.FireFly_frontend.dtos.FirstProductDTO;
import com.example.FireFly_frontend.dtos.FirstProductOrderDTO;
import com.example.FireFly_frontend.enums.MaterialType;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/firstProductOrder")
public class FirstProductOrderController {

    private final FirstProductOrderClient firstProductOrderClient;
    private final FirstProductClient firstProductClient;

    @GetMapping("/create")
    public String createFinalProductOrder(Model model, HttpServletRequest request){
        String token = (String) request.getSession().getAttribute("sessionToken");
        List<FirstProductDTO> firstProductList = firstProductClient.findAll(token);
        model.addAttribute("firstProducts", firstProductList);
        return "FirstProductOrder/form";
    }

    @PostMapping("/create")
    public String submit(@RequestParam Long id, @RequestParam int quantity, HttpServletRequest request){
        String token = (String) request.getSession().getAttribute("sessionToken");
        firstProductOrderClient.createFirstProductOrder(id,quantity,token);
        return "redirect:/firstProductOrder/all";
    }

    @GetMapping("/all")
    public String allFinalProductOrders(Model model, HttpServletRequest request){
        String token = (String) request.getSession().getAttribute("sessionToken");
        List<FirstProductOrderDTO> finalProductOrderDTOS = firstProductOrderClient.findAllFirstProductOrders(token);
        model.addAttribute("allProducts", finalProductOrderDTOS);
        return "FirstProductOrder/all";
    }

    @GetMapping("/all-metal")
    public String allFinalProductOrdersThatAreMetal(Model model, HttpServletRequest request){
        String token = (String) request.getSession().getAttribute("sessionToken");
        List<FirstProductOrderDTO> finalProductOrderDTOS = firstProductOrderClient.findAllFirstProductOrders(MaterialType.METAL,token);
        model.addAttribute("allProducts", finalProductOrderDTOS);
        return "FirstProductOrder/all-metals";
    }
}
