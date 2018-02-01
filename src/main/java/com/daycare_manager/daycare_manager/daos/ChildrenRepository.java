package com.daycare_manager.daycare_manager.daos;

import com.daycare_manager.daycare_manager.model.Child;
import com.daycare_manager.daycare_manager.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildrenRepository extends CrudRepository<Child, Long> {
    //    "Long" is the type of the primary key.
    // HQL
    // query methods:

//    Child findByUsername(String username);

    List<Child> findByParent(User parent);

    List<Child> findByTeacher(User teacher);


}
