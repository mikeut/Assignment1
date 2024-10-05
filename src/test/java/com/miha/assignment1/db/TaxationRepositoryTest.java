package com.miha.assignment1.db;

import com.miha.assignment1.db.entity.CountryEntity;
import com.miha.assignment1.db.entity.TaxationEntity;
import com.miha.assignment1.db.entity.TaxationType;
import com.miha.assignment1.db.entity.TraderEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TaxationRepositoryTest {

    @Autowired
    private TaxationRepository taxationRepository;

    @Autowired
    private TraderRepository traderRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Test
    void getTaxationForTrader() {
        var taxation = taxationRepository.save(new TaxationEntity(TaxationType.GENERAL_FLAT, 10.0));
        var country = countryRepository.save(new CountryEntity("Country", "C", taxation));
        traderRepository.save(new TraderEntity(1L, "Trader", country));
        var result = taxationRepository.getTaxationForTrader(1L);
        assertEquals(taxation, result);
    }
}