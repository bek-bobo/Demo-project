package com.example.demo.user.repository;


import com.example.demo.core.repository.CoreRepository;
import com.example.demo.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CoreRepository<User, UUID> {


}
