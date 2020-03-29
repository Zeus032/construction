package com.olmero.construction.offer;

import com.olmero.construction.tender.Tender;
import com.olmero.construction.tender.TenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OfferService {

    private final OfferRepository offerRepository;
    private final TenderRepository tenderRepository;

    public Offer addNewOffer(Offer offer) {
        offer.setAccepted(null);
        Optional<Tender> tender = tenderRepository.findById(offer.getTenderId());
        tender.ifPresent(value -> offer.setIssuerId(value.getIssuerId()));
        return offerRepository.save(offer);
    }

    public List<Offer> getOffersForTender(Integer id) {
        return offerRepository.findByTenderId(id);
    }

    public List<Offer> findByBidderIdAndTenderId(Integer bidderId, Integer tenderId) {
        return offerRepository.findByBidderIdAndTenderId(bidderId, tenderId);
    }

    public List<Offer> findByBidderId(Integer id) {
        return offerRepository.findByBidderId(id);
    }

    public Offer findBestOfferAndCloseTender(Integer tenderId) {
        List<Offer> offerForTenderList = offerRepository.findByTenderId(tenderId);
        Offer maxOffer = offerForTenderList.get(0);
        if (maxOffer.getAccepted() != null)
            return null;

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

        return maxOffer;
    }
}
