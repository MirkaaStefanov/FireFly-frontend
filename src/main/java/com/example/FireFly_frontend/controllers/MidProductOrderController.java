package com.example.FireFly_frontend.controllers;

import com.example.FireFly_frontend.clients.FinalProductClient;
import com.example.FireFly_frontend.clients.FinalProductOrderClient;
import com.example.FireFly_frontend.clients.MidProductClient;
import com.example.FireFly_frontend.clients.MidProductOrderClient;
import com.example.FireFly_frontend.dtos.FinalProductDTO;
import com.example.FireFly_frontend.dtos.FinalProductOrderDTO;
import com.example.FireFly_frontend.dtos.MidProductDTO;
import com.example.FireFly_frontend.dtos.MidProductOrderDTO;
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
@RequestMapping("/midProductOrder")
public class MidProductOrderController {

    private final MidProductOrderClient midProductOrderClient;
    private final MidProductClient midProductClient;

    @GetMapping("/create")
    public String createFinalProductOrder(Model model, HttpServletRequest request){
        String token = (String) request.getSession().getAttribute("sessionToken");
        List<MidProductDTO> finalProductList = midProductClient.findAll(token);
        model.addAttribute("finalProducts", finalProductList);
        return "MidProductOrder/form";
    }

    @PostMapping("/create")
    public String submit(@RequestParam Long id, @RequestParam int quantity, HttpServletRequest request){
        String token = (String) request.getSession().getAttribute("sessionToken");
        midProductOrderClient.createFromMidProductOrder(id,quantity,token);
        return "redirect:/midProductOrder/all";
    }

    @GetMapping("/all")
    public String allFinalProductOrders(Model model, HttpServletRequest request){
        String token = (String) request.getSession().getAttribute("sessionToken");
        List<MidProductOrderDTO> finalProductOrderDTOS = midProductOrderClient.findAllMidProductOrders(token);
        model.addAttribute("allProducts", finalProductOrderDTOS);
        return "MidProductOrder/all";
    }


}
