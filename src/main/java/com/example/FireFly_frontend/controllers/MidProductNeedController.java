package com.example.FireFly_frontend.controllers;

import com.example.FireFly_frontend.clients.ExchangeClient;
import com.example.FireFly_frontend.clients.FinalProductNeedClient;
import com.example.FireFly_frontend.clients.FirstProductClient;
import com.example.FireFly_frontend.clients.MidProductClient;
import com.example.FireFly_frontend.clients.MidProductNeedClient;
import com.example.FireFly_frontend.dtos.FinalProductNeedDTO;
import com.example.FireFly_frontend.dtos.FirstProductDTO;
import com.example.FireFly_frontend.dtos.MidProductDTO;
import com.example.FireFly_frontend.dtos.MidProductNeedDTO;
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

import java.util.Iterator;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/midProductNeed")
public class MidProductNeedController {

    private final MidProductNeedClient midProductNeedClient;
    private final MidProductClient midProductClient;
    private final FirstProductClient firstProductClient;
    private final ExchangeClient exchangeClient;

    @GetMapping("/create/{id}")
    public String form(@PathVariable Long id, Model model, HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute("sessionToken");
        List<FirstProductDTO> firstProducts = firstProductClient.findAll(token);
        List<MidProductNeedDTO> allProducts = midProductNeedClient.findAllForFinalProduct(id,token);

        Iterator<FirstProductDTO> iterator = firstProducts.iterator();
        while (iterator.hasNext()) {
            FirstProductDTO firstProductDTO = iterator.next();
            for (MidProductNeedDTO midProductNeed : allProducts) {
                if (midProductNeed.getFirstProduct().equals(firstProductDTO)) {
                    iterator.remove();
                    break;
                }
            }
        }
        model.addAttribute("midProductId", id);
        model.addAttribute("firstProducts", firstProducts);
        return "MidProductNeed/form";
    }

    @PostMapping("/submit")
    public String save(@RequestParam Long midProductId, @RequestParam Long firstProductId, @RequestParam int quantity, HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute("sessionToken");
        midProductNeedClient.save(midProductId, firstProductId, quantity,token);
        return "redirect:/midProductNeed/all/" + midProductId;
    }

    @GetMapping("/all/{id}")
    public String findAllForFinalProduct(@PathVariable Long id, Model model,HttpServletRequest request){
        String token = (String) request.getSession().getAttribute("sessionToken");
        List<MidProductNeedDTO> allProducts = midProductNeedClient.findAllForFinalProduct(id,token);
        Double tryExchangeRate = exchangeClient.exchangeEuroToTRY();
        MidProductDTO midProductDTO = midProductClient.findById(id,token);
        Double finalCost = midProductNeedClient.calculateCost(id,token);
        midProductDTO.setTryPrice(midProductDTO.getPrice()*tryExchangeRate);
        midProductDTO.setFinalCost(finalCost);
        midProductDTO.setTryFinalCost(finalCost*tryExchangeRate);
        model.addAttribute("midProductId", id);
        model.addAttribute("products", allProducts);
        model.addAttribute("midProduct", midProductDTO);
        return "MidProductNeed/allForProduct";
    }

}
