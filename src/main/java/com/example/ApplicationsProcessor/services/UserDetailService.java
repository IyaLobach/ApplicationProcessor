package com.example.ApplicationsProcessor.services;

import com.example.ApplicationsProcessor.repositories.IUserRepository;
import com.example.ApplicationsProcessor.security.UserDetail;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {


  private final IUserRepository userRepository;

  @Autowired
  public UserDetailService(
      IUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<com.example.ApplicationsProcessor.models.User> user = userRepository.findByEmail(username);
    if (user.isEmpty()) {
      throw new UsernameNotFoundException("User not found");
    }
    return new UserDetail(user.get());
  }
}
