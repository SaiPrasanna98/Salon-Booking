package com.zosh.party.service.service;

import com.zosh.party.service.exception.UserException;
import com.zosh.party.service.modal.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id) throws UserException;
    List<User> getAllUsers();
    void deleteUser(Long id) throws Exception;
    User updateUser(Long id,User user) throws UserException;

    User getUserBYId(Long id);
}
