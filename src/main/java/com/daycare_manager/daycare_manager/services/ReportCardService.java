package com.daycare_manager.daycare_manager.services;

import com.daycare_manager.daycare_manager.daos.ReportCardRepository;
import com.daycare_manager.daycare_manager.daos.UsersRepository;
import com.daycare_manager.daycare_manager.model.Child;
import com.daycare_manager.daycare_manager.model.ReportCard;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportCardService {
    private ReportCardRepository reportCardRepository;

    private List<ReportCard> reportCards;


    public ReportCardService(ReportCardRepository reportCardRepository) {
        this.reportCardRepository = reportCardRepository;
    }

    public ReportCard save(ReportCard reportCard) {
        reportCardRepository.save(reportCard);
        return reportCard;
    }

    public void update(ReportCard reportCard) {
        reportCardRepository.save(reportCard);
    }

    public ReportCard findOne(long id) {
        return reportCardRepository.findOne(id);
    }

    public Iterable<ReportCard> findByChild(Child child) {
        return reportCardRepository.findByChild(child);
    }
}
