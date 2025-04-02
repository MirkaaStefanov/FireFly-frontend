package com.example.FireFly_frontend.controllers;

import com.example.FireFly_frontend.clients.FinalProductClient;
import com.example.FireFly_frontend.clients.FinalProductNeedClient;
import com.example.FireFly_frontend.clients.MidProductClient;
import com.example.FireFly_frontend.dtos.FinalProductDTO;
import com.example.FireFly_frontend.dtos.FinalProductNeedDTO;
import com.example.FireFly_frontend.dtos.MidProductDTO;
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
@RequestMapping("/finalProductNeed")
public class FinalProductNeedController {

    private final FinalProductNeedClient finalProductNeedClient;
    private final FinalProductClient finalProductClient;
    private final MidProductClient midProductClient;

    @GetMapping("/create/{id}")
    public String form(@PathVariable Long id, Model model) {
        List<MidProductDTO> midProducts = midProductClient.findAll();
        List<FinalProductNeedDTO> allProducts = finalProductNeedClient.findAllForFinalProduct(id);

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
    public String save(@RequestParam Long finalProductId, @RequestParam Long midProductId, @RequestParam int quantity) {
        finalProductNeedClient.save(finalProductId, midProductId, quantity);
        return "redirect:/finalProductNeed/all/" + finalProductId;
    }
    @GetMapping("/all/{id}")
    public String findAllForFinalProduct(@PathVariable Long id, Model model){
        List<FinalProductNeedDTO> allProducts = finalProductNeedClient.findAllForFinalProduct(id);
        FinalProductDTO finalProductDTO = finalProductClient.findById(id);
        model.addAttribute("finalProductId", id);
        model.addAttribute("products", allProducts);
        model.addAttribute("finalProduct", finalProductDTO);
        return "FinalProductNeed/allForProduct";
    }


}
