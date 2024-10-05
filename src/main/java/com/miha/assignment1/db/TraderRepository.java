package com.miha.assignment1.db;

import com.miha.assignment1.db.entity.TraderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraderRepository extends JpaRepository<TraderEntity, Long> {
}
