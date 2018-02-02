package com.daycare_manager.daycare_manager.services;

import com.daycare_manager.daycare_manager.daos.UsersRepository;
import com.daycare_manager.daycare_manager.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Iterable<User> findAll() {
        return usersRepository.findAll();
    }


    // new method to find all

    public User save(User user) {
        usersRepository.save(user);
        return user;
    }

    public User findOne(long id) {
        return usersRepository.findOne(id);

    }

    public void update(User user) {
        usersRepository.save(user);
    }

    public void delete(long id) {
        usersRepository.delete(id);

    }

}
