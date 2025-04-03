package com.example.FireFly_frontend.controllers;

import com.example.FireFly_frontend.clients.FirstProductOrderClient;
import com.example.FireFly_frontend.dtos.FirstProductOrderDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/firstProductOrder")
public class FirstProductOrderController {

    private final FirstProductOrderClient firstProductOrderClient;

    @GetMapping("/all")
    public String allFinalProductOrders(Model model, HttpServletRequest request){
        String token = (String) request.getSession().getAttribute("sessionToken");
        List<FirstProductOrderDTO> finalProductOrderDTOS = firstProductOrderClient.findAllFirstProductOrders(token);
        model.addAttribute("allProducts", finalProductOrderDTOS);
        return "FirstProductOrder/all";
    }

}
