package com.daycare_manager.daycare_manager.services;

import com.daycare_manager.daycare_manager.daos.ChildrenRepository;
import com.daycare_manager.daycare_manager.model.Child;
import org.springframework.stereotype.Service;

@Service
public class ChildService {
    private ChildrenRepository childrenRepository;

    public ChildService(ChildrenRepository childrenRepository) {
        this.childrenRepository = childrenRepository;
    }

    public Child findOne(long id) {
        return childrenRepository.findOne(id);

    }

}
