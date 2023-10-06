package com.example.ApplicationsProcessor.services;

import com.example.ApplicationsProcessor.models.Application;
import com.example.ApplicationsProcessor.models.Status;
import com.example.ApplicationsProcessor.models.User;
import com.example.ApplicationsProcessor.repositories.IApplicationRepository;
import com.example.ApplicationsProcessor.util.ApplicationNotCreatedException;
import com.example.ApplicationsProcessor.util.ApplicationNotSubmittedException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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

  @Transactional
  public void create(Application application, User creator) {
    application.setUser(creator);
    application.setStatus(Status.DRAFT);
    application.setDate(new Date());
    creator.addApplication(application);
    applicationRepository.save(application);
  }

  @Transactional
  public void updateStatus(int id) {
    Optional<Application>  updateApplication = applicationRepository.findById(id);
    if (updateApplication.isEmpty())
      throw new ApplicationNotSubmittedException("Не найдена заявка для отправки");
    updateApplication.get().setStatus(Status.SUBMITTED);
    applicationRepository.save(updateApplication.get());
  }

  @Transactional
  public void updateText(int id, String newText) {
    Optional<Application>  updateApplication = applicationRepository.findById(id);
    if (updateApplication.isEmpty())
      throw new ApplicationNotSubmittedException("Не найдена заявка для отправки");
    updateApplication.get().setText(newText);
    applicationRepository.save(updateApplication.get());
  }

  public List<Application> showApplication(int userId) {
    return applicationRepository.findAllByUserId(userId);
  }

}
