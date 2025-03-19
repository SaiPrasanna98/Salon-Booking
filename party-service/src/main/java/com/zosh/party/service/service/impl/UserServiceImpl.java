package com.zosh.party.service.service.impl;

import com.zosh.party.service.exception.UserException;
import com.zosh.party.service.modal.User;
import com.zosh.party.service.repository.UserRepository;
import com.zosh.party.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) throws UserException {
        Optional<User> otp = userRepository.findById(id);
        if (otp.isPresent()) {
            return otp.get();
        }
        throw new UserException("User not found");
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) throws Exception {
        Optional<User> otp=userRepository.findById(id);
        if(otp.isEmpty()){
            throw new Exception("user not found with id"+id);
        }
        userRepository.deleteById(otp.get().getId());


    }

    @Override
    public User updateUser(Long id, User user) throws UserException {
        Optional<User> otp=userRepository.findById(id);
        if (otp.isEmpty()) {
            throw new UserException("user not found with id"+id);

        }
        User existingUser=otp.get();
        existingUser.setFullName(user.getFullName());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());
        return userRepository.save(existingUser);
    }
}
