package com.example.ApplicationsProcessor.repositories;


import com.example.ApplicationsProcessor.models.Application;
import com.example.ApplicationsProcessor.models.Status;
import java.awt.print.Pageable;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IApplicationRepository extends JpaRepository<Application, Integer> {
  List<Application> findAllByUserId(int id);
  List<Application> findAllByStatus(Status status);
  Page<Application> findAllByStatus(Status status, PageRequest pageRequest, Sort sort);
  Page<Application> findByUserId(int id, PageRequest pageRequest, Sort sort);
  List<Application> findAllByStatusAndUserNameContaining(Status status, String name);
  Page<Application> findAllByStatusAndUserNameContaining(Status status, String name, PageRequest pageRequest, Sort sort);
}
