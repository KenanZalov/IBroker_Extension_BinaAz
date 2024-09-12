package org.example.ibroker.repository;

import org.example.ibroker.entity.SpecificSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificRepository extends JpaRepository<SpecificSearch, Long> {
}
