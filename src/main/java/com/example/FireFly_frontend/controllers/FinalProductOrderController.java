package com.example.FireFly_frontend.controllers;

import com.example.FireFly_frontend.clients.FinalProductClient;
import com.example.FireFly_frontend.clients.FinalProductOrderClient;
import com.example.FireFly_frontend.dtos.FinalProductDTO;
import com.example.FireFly_frontend.dtos.FinalProductOrderDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/finalProductOrder")
public class FinalProductOrderController {

    private final FinalProductOrderClient finalProductOrderClient;
    private final FinalProductClient finalProductClient;

    @GetMapping("/create")
    public String createFinalProductOrder(Model model, HttpServletRequest request){
        String token = (String) request.getSession().getAttribute("sessionToken");
        List<FinalProductDTO> finalProductList = finalProductClient.findAll(token);
        model.addAttribute("finalProducts", finalProductList);
        return "FinalProductOrder/form";
    }

    @PostMapping("/create")
    public String submit(@RequestParam Long id, @RequestParam int quantity, HttpServletRequest request){
        String token = (String) request.getSession().getAttribute("sessionToken");
        finalProductOrderClient.createFinalProductOrder(id,quantity,token);
        return "redirect:/finalProductOrder/all";
    }

    @GetMapping("/all")
    public String allFinalProductOrders(Model model, HttpServletRequest request){
        String token = (String) request.getSession().getAttribute("sessionToken");
        List<FinalProductOrderDTO> finalProductOrderDTOS = finalProductOrderClient.findAllFinalProductOrders(token);
        model.addAttribute("allProducts", finalProductOrderDTOS);
        return "FinalProductOrder/all";
    }


}
