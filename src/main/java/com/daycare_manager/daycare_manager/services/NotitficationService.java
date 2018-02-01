package com.daycare_manager.daycare_manager.services;


import com.daycare_manager.daycare_manager.daos.NotificationsRepository;
import com.daycare_manager.daycare_manager.daos.UsersRepository;
import com.daycare_manager.daycare_manager.model.Notification;
import com.daycare_manager.daycare_manager.model.User;
import org.springframework.stereotype.Service;

@Service
public class NotitficationService {

    private NotificationsRepository notificationsRepository;

    public NotitficationService(NotificationsRepository notificationsRepository) {
        this.notificationsRepository = notificationsRepository;
    }


    public Iterable<Notification> findAll() {
        return notificationsRepository.findAll();
    }



    public Notification save(Notification notification) {
        notificationsRepository.save(notification);
        return notification;
    }

    public Notification findOne(long id) {
        return notificationsRepository.findOne(id);

    }

    public void update(Notification notification) {
        notificationsRepository.save(notification);
    }

    public void delete(long id) {
        notificationsRepository.delete(id);

    }
}
