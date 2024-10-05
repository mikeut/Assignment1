package com.miha.assignment1.service;

import com.miha.assignment1.db.TaxationRepository;
import com.miha.assignment1.db.entity.TaxationEntity;
import com.miha.assignment1.dto.TaxationResponse;
import org.springframework.stereotype.Service;

@Service
public class TaxationService {

    private final TaxationRepository taxationRepository;

    public TaxationService(TaxationRepository taxationRepository) {
        this.taxationRepository = taxationRepository;
    }

    public TaxationResponse getTaxationForTrader(Long traderId, Double playedAmount, Double odd) {
        var totalWinnings = playedAmount * odd;
        var winnings = totalWinnings - playedAmount;

        TaxationEntity taxationForTrader = taxationRepository.getTaxationForTrader(traderId);

        if (taxationForTrader == null) {
            return null;
        }

        return switch (taxationForTrader.getTaxationType()) {
            case GENERAL_TAX -> {
                var tax = totalWinnings * taxationForTrader.getTaxRate() / 100;
                yield new TaxationResponse(
                    totalWinnings - tax,
                    totalWinnings,
                    totalWinnings - tax,
                    taxationForTrader.getTaxRate(),
                    tax
                );
            }
            case GENERAL_FLAT -> new TaxationResponse(
                totalWinnings - taxationForTrader.getTaxRate(),
                totalWinnings,
                totalWinnings - taxationForTrader.getTaxRate(),
                taxationForTrader.getTaxRate(),
                taxationForTrader.getTaxRate()
            );
            case WINNINGS_TAX -> new TaxationResponse(
                totalWinnings - winnings * taxationForTrader.getTaxRate() / 100,
                totalWinnings,
                totalWinnings - winnings * taxationForTrader.getTaxRate() / 100,
                taxationForTrader.getTaxRate(),
                winnings * taxationForTrader.getTaxRate() / 100
            );
            case WINNINGS_FLAT -> {
                var winnings1 = winnings - taxationForTrader.getTaxRate();
                yield new TaxationResponse(
                    playedAmount + winnings1,
                    totalWinnings,
                    playedAmount + winnings1,
                    taxationForTrader.getTaxRate(),
                    taxationForTrader.getTaxRate()
                );
            }
        };
    }
}
