package com.olmero.construction.tender;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TenderService {

    private final TenderRepository tenderRepository;


    public Tender addNewTender(Tender tender) {
        return tenderRepository.save(tender);
    }

    public List<Tender> getAllTenders() {
        return tenderRepository.findAll();
    }

    public List<Tender> getTendersForIssuer(Integer id) {
        return tenderRepository.findByIssuerId(id);
    }
}
