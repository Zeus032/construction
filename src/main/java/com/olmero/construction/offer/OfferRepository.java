package com.olmero.construction.offer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Integer> {
    List<Offer> findByTenderId(Integer id);
    List<Offer> findByBidderIdAndTenderId(Integer bidderId,Integer tenderId);
    List<Offer> findByBidderId(Integer id);
}
