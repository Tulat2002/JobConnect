package com.devanktu.jobconnect.service.impl;

import com.devanktu.jobconnect.domain.User;
import com.devanktu.jobconnect.repository.UserRepository;
import com.devanktu.jobconnect.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User handleCreateUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public void handleDeleteUser(long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public User fetchUserById(long id) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
    }

    @Override
    public List<User> fetchAllUser() {
        return this.userRepository.findAll();
    }

    @Override
    public User handleUpdateUser(User reqUser) {
        User currentUser = this.fetchUserById(reqUser.getId());
        if (currentUser != null) {
            //currentUser.setEmail(reqUser.getEmail());
            //currentUser.setPassword(reqUser.getPassword());
            currentUser.setName(reqUser.getName());
            currentUser.setGender(reqUser.getGender());
            currentUser.setAge(reqUser.getAge());
            currentUser.setAddress(reqUser.getAddress());
            //update
            currentUser = this.userRepository.save(currentUser);
        }
        return currentUser;
    }
}
