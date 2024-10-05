package com.miha.assignment1.service;

import com.miha.assignment1.db.TaxationRepository;
import com.miha.assignment1.db.entity.TaxationEntity;
import com.miha.assignment1.db.entity.TaxationType;
import com.miha.assignment1.dto.TaxationResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaxationServiceTest {

    @Mock
    private TaxationRepository taxationRepository;

    @InjectMocks
    private TaxationService taxationService;

    @Test
    void getTaxationForTraderGeneralTax() {
        when(taxationRepository.getTaxationForTrader(1L))
            .thenReturn(new TaxationEntity(TaxationType.GENERAL_TAX, 10.0));

        TaxationResponse response = taxationService.getTaxationForTrader(1L, 5.0, 1.5);
        assertEquals(6.75, response.possibleReturnAmount());
        assertEquals(6.75, response.possibleReturnAmountAfterTax());
        assertEquals(7.5, response.possibleReturnAmountBefTax());
        assertEquals(0.75, response.taxAmount());
        assertEquals(10.0, response.taxRate());
    }

    @Test
    void getTaxationForTraderGeneralFlat() {
        when(taxationRepository.getTaxationForTrader(1L))
            .thenReturn(new TaxationEntity(TaxationType.GENERAL_FLAT, 2.0));

        TaxationResponse response = taxationService.getTaxationForTrader(1L, 5.0, 1.5);
        assertEquals(5.5, response.possibleReturnAmount());
        assertEquals(5.5, response.possibleReturnAmountAfterTax());
        assertEquals(7.5, response.possibleReturnAmountBefTax());
        assertEquals(2, response.taxAmount());
        assertEquals(2, response.taxRate());
    }

    @Test
    void getTaxationForTraderWinningsTax() {
        when(taxationRepository.getTaxationForTrader(1L))
            .thenReturn(new TaxationEntity(TaxationType.WINNINGS_TAX, 10.0));

        TaxationResponse response = taxationService.getTaxationForTrader(1L, 5.0, 1.5);
        assertEquals(7.25, response.possibleReturnAmount());
        assertEquals(7.25, response.possibleReturnAmountAfterTax());
        assertEquals(7.5, response.possibleReturnAmountBefTax());
        assertEquals(0.25, response.taxAmount());
        assertEquals(10, response.taxRate());
    }

    @Test
    void getTaxationForTraderWinningsFlat() {
        when(taxationRepository.getTaxationForTrader(1L))
            .thenReturn(new TaxationEntity(TaxationType.WINNINGS_FLAT, 1.0));

        TaxationResponse response = taxationService.getTaxationForTrader(1L, 5.0, 1.5);
        assertEquals(6.5, response.possibleReturnAmount());
        assertEquals(6.5, response.possibleReturnAmountAfterTax());
        assertEquals(7.5, response.possibleReturnAmountBefTax());
        assertEquals(1, response.taxAmount());
        assertEquals(1, response.taxRate());
    }
}