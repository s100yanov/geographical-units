package org.training.geographical_units.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.training.geographical_units.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
}
