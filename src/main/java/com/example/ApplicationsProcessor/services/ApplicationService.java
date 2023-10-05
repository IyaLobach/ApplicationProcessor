package com.example.ApplicationsProcessor.services;

import com.example.ApplicationsProcessor.repositories.IApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ApplicationService {

  private final IApplicationRepository applicationRepository;

  @Autowired
  public ApplicationService(IApplicationRepository applicationRepository) {
    this.applicationRepository = applicationRepository;
  }

}
