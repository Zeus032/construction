package com.olmero.construction.offer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path="/offer")
public class OfferController {

    private final OfferService offerService;

    @PostMapping("/add")
    public ResponseEntity<Offer> addNewOffer (@RequestBody Offer offer) {
        Offer addedOffer = offerService.addNewOffer(offer);

        return ResponseEntity.ok(addedOffer);
    }

    @GetMapping("/tender")
    public ResponseEntity<List<Offer>> getOffersForTender(@RequestParam Integer id) {
        return ResponseEntity.ok(offerService.getOffersForTender(id));
    }

    @GetMapping
    public ResponseEntity<List<Offer>> getOffersBidderSubmittedForSpecificTender(@RequestParam Integer bidderId,
                                                          @RequestParam Integer tenderId) {
        return ResponseEntity.ok(offerService.findByBidderIdAndTenderId(bidderId, tenderId));
    }

    @GetMapping("/bidder")
    public ResponseEntity<List<Offer>> getOffersBidderSubmittedForAllTender(@RequestParam Integer id) {
        return ResponseEntity.ok(offerService.findByBidderId(id));
    }

    @GetMapping("/close")
    public ResponseEntity<Offer> findBestOfferAndCloseTender(@RequestParam Integer tenderId) {

        Offer bestOffer = offerService.findBestOfferAndCloseTender(tenderId);

        if (null != bestOffer) {
            return ResponseEntity.ok(bestOffer);
        }

        return ResponseEntity.notFound().build();

    }
}
