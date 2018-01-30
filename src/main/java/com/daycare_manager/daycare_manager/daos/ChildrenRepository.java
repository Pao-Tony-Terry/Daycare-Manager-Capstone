package com.daycare_manager.daycare_manager.daos;

import com.daycare_manager.daycare_manager.model.Child;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildrenRepository extends CrudRepository<Child, Long> {
    //    "Long" is the type of the primary key.
    // HQL
    // query methods:

//    Child findByUsername(String username);

}
