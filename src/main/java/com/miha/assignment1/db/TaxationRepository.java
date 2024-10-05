package com.miha.assignment1.db;

import com.miha.assignment1.db.entity.TaxationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TaxationRepository extends JpaRepository<TaxationEntity, Long> {

    @Query(value = "SELECT ta.* FROM trader t inner join country c on t.country_id = c.id inner join taxation ta on c.taxation_id = ta.id where t.id = ?1", nativeQuery = true)
    TaxationEntity getTaxationForTrader(Long traderId);
}
