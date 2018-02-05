package com.daycare_manager.daycare_manager.services;

import com.daycare_manager.daycare_manager.daos.ChildrenRepository;
import com.daycare_manager.daycare_manager.model.Child;
import com.daycare_manager.daycare_manager.model.User;
import org.springframework.stereotype.Service;

import java.awt.event.ItemListener;
import java.util.List;

@Service
public class ChildService {

    private ChildrenRepository childrenRepository;

    public ChildService(ChildrenRepository childrenRepository) {
        this.childrenRepository = childrenRepository;
    }

    public Iterable<Child> findAll(){
        return childrenRepository.findAll();

    }

    public Child findOne(long id) {
        return childrenRepository.findOne(id);

    }

    public Child save(Child child) {
        childrenRepository.save(child);
        return child;
    }

    public void delete(long id) {
        childrenRepository.delete(id);

    }




    public Iterable<Child> findByParent(User parent) {
        return childrenRepository.findByParent(parent);
    }

}
