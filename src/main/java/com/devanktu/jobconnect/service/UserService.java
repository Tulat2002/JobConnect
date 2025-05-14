package com.devanktu.jobconnect.service;


import com.devanktu.jobconnect.domain.User;

import java.util.List;

public interface UserService {

    User handleCreateUser(User user);

    void handleDeleteUser(long id);

    User fetchUserById(long id);

    List<User> fetchAllUser();

    User handleUpdateUser(User reqUser);

    User handleGetUserByUsername(String username);

}