package com.example.ApplicationsProcessor.services;


import com.example.ApplicationsProcessor.models.Role;
import com.example.ApplicationsProcessor.models.RoleEnum;
import com.example.ApplicationsProcessor.models.User;
import com.example.ApplicationsProcessor.repositories.IUserRepository;
import com.example.ApplicationsProcessor.util.UserException;
import java.util.List;
import java.util.Optional;
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

  @Transactional
  public void updateRole(int id) {
    Optional<User> user = userRepository.findById(id);
    if (user.isEmpty()) {
      throw new UserException("Пользователь не найден");
    }
    List<Role> roles = user.get().getRole();
    for (Role role : roles) {
      if (role.getRole().getTitle().equals("Оператор")) {
        return;
      }
    }
    roles.add(new Role(RoleEnum.OPERATOR));
  }

  public User findById(int id) {
    return userRepository.findById(id).get();
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }
}
