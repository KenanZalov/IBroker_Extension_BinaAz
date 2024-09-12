package org.example.ibroker.repository;

import org.example.ibroker.entity.GeneralSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralRepository extends JpaRepository<GeneralSearch, Long> {
}
