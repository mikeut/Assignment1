package com.miha.assignment1.dto;

public record TaxationResponse(
    Double possibleReturnAmount,
    Double possibleReturnAmountBefTax,
    Double possibleReturnAmountAfterTax,
    Double taxRate,
    Double taxAmount
) {
}
