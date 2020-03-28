package com.olmero.construction.tender;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TenderRepository extends JpaRepository<Tender, Integer> {
    List<Tender> findByIssuerId(Integer id);
}
