package com.olmero.construction.tender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/tender")
public class TenderController {

    @Autowired
    private TenderRepository tenderRepository;

    @PostMapping(path="/add")
    public @ResponseBody
    String addNewTender (@RequestParam String name) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Tender t = new Tender();
        t.setTenderName(name);
        tenderRepository.save(t);
        return "Saved";
    }

    @GetMapping
    public @ResponseBody Iterable<Tender> getAllTenders() {
        return tenderRepository.findAll();
    }
}
