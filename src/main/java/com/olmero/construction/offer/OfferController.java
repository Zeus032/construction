package com.olmero.construction.offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
