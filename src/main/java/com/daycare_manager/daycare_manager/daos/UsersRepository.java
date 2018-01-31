package com.daycare_manager.daycare_manager.daos;

import com.daycare_manager.daycare_manager.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
    //    "Long" is the type of the primary key.

    // HQL
    // query methods:

    User findByUsername(String username);

}
