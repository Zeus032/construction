package com.olmero.construction.tender;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path="/tender")
public class TenderController {

    private final TenderService tenderService;

    @PostMapping("/add")
    public ResponseEntity<Tender> addNewTender (@RequestBody Tender tender) {
        Tender addedTender = tenderService.addNewTender(tender);

        return ResponseEntity.ok(addedTender);
    }

    @GetMapping
    public ResponseEntity<List<Tender>> getAllTenders() {
        return ResponseEntity.ok(tenderService.getAllTenders());
    }

    @GetMapping("/issuer")
    public ResponseEntity<List<Tender>> getTendersForIssuer(@RequestParam Integer id) {
        return ResponseEntity.ok(tenderService.getTendersForIssuer(id));
    }
}
