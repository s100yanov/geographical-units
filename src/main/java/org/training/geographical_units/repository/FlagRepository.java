package org.training.geographical_units.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.training.geographical_units.model.Flag;

@Repository
public interface FlagRepository extends JpaRepository<Flag, Integer> {

    Flag findByName(String name);
}
