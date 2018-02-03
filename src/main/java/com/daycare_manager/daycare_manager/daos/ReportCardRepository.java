package com.daycare_manager.daycare_manager.daos;

import com.daycare_manager.daycare_manager.model.Child;
import com.daycare_manager.daycare_manager.model.ReportCard;
import com.daycare_manager.daycare_manager.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReportCardRepository extends CrudRepository<ReportCard, Long>{
    List<ReportCard> findByChild(Child child);
}
