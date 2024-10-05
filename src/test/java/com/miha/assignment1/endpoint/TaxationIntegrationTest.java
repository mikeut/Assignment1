package com.miha.assignment1.endpoint;

import com.miha.assignment1.db.CountryRepository;
import com.miha.assignment1.db.TaxationRepository;
import com.miha.assignment1.db.TraderRepository;
import com.miha.assignment1.db.entity.CountryEntity;
import com.miha.assignment1.db.entity.TaxationEntity;
import com.miha.assignment1.db.entity.TaxationType;
import com.miha.assignment1.db.entity.TraderEntity;
import com.miha.assignment1.dto.TaxationResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaxationIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TaxationRepository taxationRepository;

    @Autowired
    private TraderRepository traderRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Test
    void getTaxationForGeneralTax() {
        var taxation = taxationRepository.save(new TaxationEntity(TaxationType.GENERAL_TAX, 10.0));
        var country = countryRepository.save(new CountryEntity("Country", "C", taxation));
        traderRepository.save(new TraderEntity(1L, "Trader", country));

        TaxationResponse response = restTemplate.getForObject("http://localhost:" + port + "/taxation?traderId=1&playedAmount=5&odd=1.5",
            TaxationResponse.class);

        Assertions.assertNotNull(response);
        assertEquals(6.75, response.possibleReturnAmount());
        assertEquals(6.75, response.possibleReturnAmountAfterTax());
        assertEquals(7.5, response.possibleReturnAmountBefTax());
        assertEquals(0.75, response.taxAmount());
        assertEquals(10.0, response.taxRate());
    }

    @Test
    void getTaxationForGeneralFlat() {
        var taxation = taxationRepository.save(new TaxationEntity(TaxationType.GENERAL_FLAT, 2.0));
        var country = countryRepository.save(new CountryEntity("Country", "C", taxation));
        traderRepository.save(new TraderEntity(1L, "Trader", country));

        TaxationResponse response = restTemplate.getForObject("http://localhost:" + port + "/taxation?traderId=1&playedAmount=5&odd=1.5",
            TaxationResponse.class);

        Assertions.assertNotNull(response);
        assertEquals(5.5, response.possibleReturnAmount());
        assertEquals(5.5, response.possibleReturnAmountAfterTax());
        assertEquals(7.5, response.possibleReturnAmountBefTax());
        assertEquals(2, response.taxAmount());
        assertEquals(2, response.taxRate());
    }

    @Test
    void getTaxationForWinningsTax() {
        var taxation = taxationRepository.save(new TaxationEntity(TaxationType.WINNINGS_TAX, 10.0));
        var country = countryRepository.save(new CountryEntity("Country", "C", taxation));
        traderRepository.save(new TraderEntity(1L, "Trader", country));

        TaxationResponse response = restTemplate.getForObject("http://localhost:" + port + "/taxation?traderId=1&playedAmount=5&odd=1.5",
            TaxationResponse.class);

        Assertions.assertNotNull(response);
        assertEquals(7.25, response.possibleReturnAmount());
        assertEquals(7.25, response.possibleReturnAmountAfterTax());
        assertEquals(7.5, response.possibleReturnAmountBefTax());
        assertEquals(0.25, response.taxAmount());
        assertEquals(10, response.taxRate());
    }

    @Test
    void getTaxationForWinningsFlat() {
        var taxation = taxationRepository.save(new TaxationEntity(TaxationType.WINNINGS_FLAT, 1.0));
        var country = countryRepository.save(new CountryEntity("Country", "C", taxation));
        traderRepository.save(new TraderEntity(1L, "Trader", country));

        TaxationResponse response = restTemplate.getForObject("http://localhost:" + port + "/taxation?traderId=1&playedAmount=5&odd=1.5",
            TaxationResponse.class);

        Assertions.assertNotNull(response);
        assertEquals(6.5, response.possibleReturnAmount());
        assertEquals(6.5, response.possibleReturnAmountAfterTax());
        assertEquals(7.5, response.possibleReturnAmountBefTax());
        assertEquals(1, response.taxAmount());
        assertEquals(1, response.taxRate());
    }

    @Test
    void getTaxationForTraderNotFound() {
        TaxationResponse response = restTemplate.getForObject("http://localhost:" + port + "/taxation?traderId=1&playedAmount=5&odd=1.5",
            TaxationResponse.class);

        Assertions.assertNull(response);
    }
}