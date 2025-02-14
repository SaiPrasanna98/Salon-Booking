package com.zosh.party.service.repository;


import com.zosh.party.service.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

}

