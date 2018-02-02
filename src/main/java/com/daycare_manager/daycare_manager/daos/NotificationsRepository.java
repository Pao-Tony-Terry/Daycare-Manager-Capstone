package com.daycare_manager.daycare_manager.daos;

import com.daycare_manager.daycare_manager.model.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationsRepository extends CrudRepository<Notification, Long>{

}
