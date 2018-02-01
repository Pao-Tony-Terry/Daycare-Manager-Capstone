package com.daycare_manager.daycare_manager.services;

import com.daycare_manager.daycare_manager.daos.ReportCardRepository;
import com.daycare_manager.daycare_manager.daos.UsersRepository;
import com.daycare_manager.daycare_manager.model.ReportCard;
import org.springframework.stereotype.Service;

@Service
public class ReportCardService {
    private ReportCardRepository reportCardRepository;

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
}