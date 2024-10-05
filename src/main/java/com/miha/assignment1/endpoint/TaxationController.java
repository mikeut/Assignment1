package com.miha.assignment1.endpoint;

import com.miha.assignment1.dto.TaxationResponse;
import com.miha.assignment1.service.TaxationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaxationController {

    private final TaxationService taxationService;

    public TaxationController(TaxationService taxationService) {
        this.taxationService = taxationService;
    }

    @GetMapping("/taxation")
    public TaxationResponse getTaxation(@RequestParam Long traderId, @RequestParam Double playedAmount, @RequestParam Double odd) {
        return taxationService.getTaxationForTrader(traderId, playedAmount, odd);
    }
}
