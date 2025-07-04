package com.Project.Train.Repo;

import com.Project.Train.Entity.TrainSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainScheduleRepository extends JpaRepository<TrainSchedule, Long> {

    List<TrainSchedule> findBySource_StationCodeIgnoreCaseAndDestination_StationCodeIgnoreCase(String sourceCode, String destinationCode);

    List<TrainSchedule> findBySource_StationNameIgnoreCaseAndDestination_StationNameIgnoreCase(String sourceName, String destinationName);
}

