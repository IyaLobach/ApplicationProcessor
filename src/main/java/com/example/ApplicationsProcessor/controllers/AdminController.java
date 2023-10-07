package com.example.ApplicationsProcessor.controllers;

import com.example.ApplicationsProcessor.dto.ApplicationDTO;
import com.example.ApplicationsProcessor.dto.UserDTO;
import com.example.ApplicationsProcessor.models.Application;
import com.example.ApplicationsProcessor.models.Status;
import com.example.ApplicationsProcessor.models.User;
import com.example.ApplicationsProcessor.services.ApplicationService;
import com.example.ApplicationsProcessor.services.RoleService;
import com.example.ApplicationsProcessor.services.UserService;
import com.example.ApplicationsProcessor.util.ErrorResponse;
import com.example.ApplicationsProcessor.util.UserException;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

  @Autowired
  private UserService userService;

  @Autowired
  private ApplicationService applicationService;

  @Autowired
  private RoleService roleService;

  @Autowired
  private ModelMapper modelMapper;


  @PatchMapping("/{adminId}/users/{userId}/appoint")
  public ResponseEntity<HttpStatus> appoint(@PathVariable("userId") int userId) {
    userService.updateRole(userId);
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @GetMapping("/{adminId}/user")
  public ResponseEntity<List<UserDTO>> show() {
    List<User> userList = userService.findAll();
    ArrayList<UserDTO> userDTOList = new ArrayList<>();
    for (User user : userList)
     userDTOList.add(modelMapper.map(user, UserDTO.class));
    return new ResponseEntity<>(userDTOList, HttpStatus.OK);
  }

  @ExceptionHandler
  private ResponseEntity<ErrorResponse> handlerException(
     UserException userException) {
    ErrorResponse errorResponse = new ErrorResponse(
        userException.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

}
