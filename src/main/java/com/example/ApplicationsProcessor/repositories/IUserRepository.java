package com.example.ApplicationsProcessor.repositories;

import com.example.ApplicationsProcessor.models.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
  List<User> findByNameContaining(String name);
}
