package com.example.ApplicationsProcessor.services;


import com.example.ApplicationsProcessor.models.User;
import com.example.ApplicationsProcessor.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {

  private final IUserRepository userRepository;

  @Autowired
  public UserService(IUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Transactional
  public void save(User user) {
    userRepository.save(user);
  }

  @Transactional
  public void update(int id, User user) {
    user.setId(id);
    userRepository.save(user);
  }

  public User findById(int id) {
    return userRepository.findById(id).get();
  }
}
