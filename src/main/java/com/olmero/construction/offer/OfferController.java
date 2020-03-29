package com.olmero.construction.offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/offer")
public class OfferController {

    @Autowired
    private OfferRepository offerRepository;

    @PostMapping(path="/add")
    public @ResponseBody
    String addNewTender (@RequestBody Offer offer) {
        offerRepository.save(offer);

        return "saved";
    }

    @GetMapping(path="/tender")
    public @ResponseBody
    List<Offer> getOffersForTender(@RequestParam Integer id) {
        return offerRepository.findByTenderId(id);
    }

    @GetMapping
    public @ResponseBody
    List<Offer> getOffersBidderSubmittedForSpecificTender(@RequestParam Integer bidderId,
                                                          @RequestParam Integer tenderId) {
        return offerRepository.findByBidderIdAndTenderId(bidderId, tenderId);
    }

    @GetMapping(path="/bidder")
    public @ResponseBody
    List<Offer> getOffersBidderSubmittedForAllTender(@RequestParam Integer id) {
        return offerRepository.findByBidderId(id);
    }

    @GetMapping(path="/close")
    public @ResponseBody
    String closeTender(@RequestParam Integer tenderId) {

        List<Offer> offerForTenderList = offerRepository.findByTenderId(tenderId);
        Offer maxOffer = offerForTenderList.get(0);
        if (maxOffer.getAccepted() != null)
            return "Tender already closed";

        for (Offer o : offerForTenderList) {
            if (o.getAmount() > maxOffer.getAmount())
                maxOffer = o;
        }

        maxOffer.setAccepted(true);
        offerForTenderList.remove(maxOffer);
        offerRepository.save(maxOffer);

        for (Offer o : offerForTenderList) {
            o.setAccepted(false);
            offerRepository.save(o);
        }

        return "Tender closed";
    }
}
