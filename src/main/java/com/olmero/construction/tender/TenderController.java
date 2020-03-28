package com.olmero.construction.tender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(path="/issuer")
    public @ResponseBody
    List<Tender> getTendersForIssuer(@RequestParam Integer id) {
        return tenderRepository.findByIssuerId(id);
    }
}
