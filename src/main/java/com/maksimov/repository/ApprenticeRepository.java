package com.maksimov.repository;

import com.maksimov.model.Apprentice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Konstantin Maksimov
 * ApprenticeRepository- interface realize the interaction of the Apprentice with the database
 */
public interface ApprenticeRepository extends JpaRepository<Apprentice, Long> {
}
