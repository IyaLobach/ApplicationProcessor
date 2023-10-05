package com.example.ApplicationsProcessor.repositories;

import com.example.ApplicationsProcessor.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

}
