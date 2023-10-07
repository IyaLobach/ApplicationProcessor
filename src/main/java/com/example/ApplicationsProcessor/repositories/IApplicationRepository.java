package com.example.ApplicationsProcessor.repositories;


import com.example.ApplicationsProcessor.models.Application;
import com.example.ApplicationsProcessor.models.Status;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IApplicationRepository extends JpaRepository<Application, Integer> {
  List<Application> findAllByUserId(int id);
  List<Application> findAllByStatus(Status status);
}
