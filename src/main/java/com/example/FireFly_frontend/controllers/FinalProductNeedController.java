package com.example.FireFly_frontend.controllers;

import com.example.FireFly_frontend.clients.ExchangeClient;
import com.example.FireFly_frontend.clients.FinalProductClient;
import com.example.FireFly_frontend.clients.FinalProductNeedClient;
import com.example.FireFly_frontend.clients.MidProductClient;
import com.example.FireFly_frontend.dtos.FinalProductDTO;
import com.example.FireFly_frontend.dtos.FinalProductNeedDTO;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/finalProductNeed")
public class FinalProductNeedController {

    private final FinalProductNeedClient finalProductNeedClient;
    private final FinalProductClient finalProductClient;
    private final MidProductClient midProductClient;
    private final ExchangeClient exchangeClient;

    @GetMapping("/create/{id}")
    public String form(@PathVariable Long id, Model model, HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute("sessionToken");
        List<MidProductDTO> midProducts = midProductClient.findAll(token);
        List<FinalProductNeedDTO> allProducts = finalProductNeedClient.findAllForFinalProduct(id, token);

        Iterator<MidProductDTO> iterator = midProducts.iterator();
        while (iterator.hasNext()) {
            MidProductDTO midProductDTO = iterator.next();
            for (FinalProductNeedDTO finalProductNeed : allProducts) {
                if (finalProductNeed.getMidProduct().equals(midProductDTO)) {
                    iterator.remove();
                    break;
                }
            }
        }
        model.addAttribute("finalProductId", id);
        model.addAttribute("midProducts", midProducts);
        return "FinalProductNeed/form";
    }

    @PostMapping("/submit")
    public String save(@RequestParam Long finalProductId, @RequestParam Long midProductId, @RequestParam int quantity, HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute("sessionToken");
        finalProductNeedClient.save(finalProductId, midProductId, quantity, token);
        return "redirect:/finalProductNeed/all/" + finalProductId;
    }

    @GetMapping("/all/{id}")
    public String findAllForFinalProduct(@PathVariable Long id, Model model, HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute("sessionToken");
        List<FinalProductNeedDTO> allProducts = finalProductNeedClient.findAllForFinalProduct(id, token);
        Double tryExchangeRate = exchangeClient.exchangeEuroToTRY();
        FinalProductDTO finalProductDTO = finalProductClient.findById(id, token);
        Double finalCost = finalProductNeedClient.calculateCost(id,token);
        finalProductDTO.setFinalCost(finalCost);
        finalProductDTO.setTryFinalCost(finalCost*tryExchangeRate);
        model.addAttribute("finalProductId", id);
        model.addAttribute("products", allProducts);
        model.addAttribute("finalProduct", finalProductDTO);
        return "FinalProductNeed/allForProduct";
    }

    @GetMapping("/edit/{id}")
    public String update(@PathVariable Long id, Model model, HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute("sessionToken");
        FinalProductNeedDTO finalProductDTO = finalProductNeedClient.findById(id, token);
        model.addAttribute("product", finalProductDTO);
        return "FinalProductNeed/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateSubmit(@PathVariable Long id, @ModelAttribute FinalProductNeedDTO product, HttpServletRequest request) throws IOException {
        String token = (String) request.getSession().getAttribute("sessionToken");
        FinalProductNeedDTO finalProductDTO = finalProductNeedClient.findById(id, token);
        finalProductNeedClient.update(id, product, token);
        return "redirect:/finalProductNeed/all/"+finalProductDTO.getFinalProduct().getId();
    }

    @PostMapping("/delete/{id}")
    public String updateSubmit(@PathVariable Long id, HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute("sessionToken");
        FinalProductNeedDTO finalProductDTO = finalProductNeedClient.findById(id, token);
        finalProductNeedClient.delete(id, token);
        return "redirect:/finalProductNeed/all/"+finalProductDTO.getFinalProduct().getId();
    }



}
