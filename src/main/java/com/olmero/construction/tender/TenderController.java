package com.olmero.construction.tender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/tender")
public class TenderController {

    @Autowired
    private TenderRepository tenderRepository;

    @PostMapping(path="/add")
    public @ResponseBody
    String addNewTender (@RequestBody Tender tender) {

        Tender t = new Tender();
        t.setName(tender.getName());
        t.setIssuerId(tender.getIssuerId());
        t.setDescription(tender.getDescription());
        tenderRepository.save(t);

        return "Saved";
    }

    @GetMapping
    public @ResponseBody Iterable<Tender> getAllTenders() {
        return tenderRepository.findAll();
    }
}
