package com.example.ApplicationsProcessor.services;

import com.example.ApplicationsProcessor.models.Application;
import com.example.ApplicationsProcessor.models.Status;
import com.example.ApplicationsProcessor.models.User;
import com.example.ApplicationsProcessor.repositories.IApplicationRepository;
import com.example.ApplicationsProcessor.util.ApplicationException;
import java.util.ArrayList;
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
  public void submit (int id) {
    Optional<Application> updateApplication = applicationRepository.findById(id);
    if (updateApplication.isEmpty()) {
      throw new ApplicationException("Не найдена заявка для отправки");
    }
    if (!updateApplication.get().getStatus().getTitle().equals("Черновик")) {
      throw new ApplicationException("Заявка уже отправлена");
    }
    updateApplication.get().setStatus(Status.SUBMITTED);
    applicationRepository.save(updateApplication.get());
  }

  @Transactional
  public void accept(int id) {
    Optional<Application> updateApplication = applicationRepository.findById(id);
    if (updateApplication.isEmpty()) {
      throw new ApplicationException("Не найдена заявка для отправки");
    }
    if (updateApplication.get().getStatus().getTitle().equals("Черновик") || updateApplication.get().getStatus().getTitle().equals("Отклонена")) {
      throw new ApplicationException("Заявка не может быть принята");
    }
    updateApplication.get().setStatus(Status.ACCEPTED);
    applicationRepository.save(updateApplication.get());
  }

  @Transactional
  public void reject(int id) {
    Optional<Application> updateApplication = applicationRepository.findById(id);
    if (updateApplication.isEmpty()) {
      throw new ApplicationException("Не найдена заявка для отправки");
    }
    if (updateApplication.get().getStatus().getTitle().equals("Черновик") || updateApplication.get().getStatus().getTitle().equals("Принята")) {
      throw new ApplicationException("Заявка не может быть отклонена");
    }
    updateApplication.get().setStatus(Status.REJECTED);
    applicationRepository.save(updateApplication.get());
  }

  @Transactional
  public void updateText(int id, String newText) {
    Optional<Application> updateApplication = applicationRepository.findById(id);
    if (updateApplication.isEmpty()) {
      throw new ApplicationException("Не найдена заявка для отправки");
    }
    if (!updateApplication.get().getStatus().getTitle().equals("Черновик")) {
      throw new ApplicationException("Заявка уже отправлена");
    }
    updateApplication.get().setText(newText);
    applicationRepository.save(updateApplication.get());
  }

  public List<Application> showApplicationByUserId(int userId) {
    return applicationRepository.findAllByUserId(userId);
  }

  public List<Application> showApplicationByStatus(Status status) {
    return applicationRepository.findAllByStatus(status);
  }

}
