package com.Project.Train.Service;

import com.Project.Train.Entity.TrainSchedule;
import com.Project.Train.Repo.TrainScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainSearchService {

    private TrainScheduleRepository trainScheduleRepository;

    public TrainSearchService(TrainScheduleRepository trainScheduleRepository){
        this.trainScheduleRepository = trainScheduleRepository;
    }
    public List<TrainSchedule> findTrainByStationCode(String sourceCode, String destinationCode) {
        return trainScheduleRepository
                .findBySource_StationCodeIgnoreCaseAndDestination_StationCodeIgnoreCase(sourceCode, destinationCode);
    }


    public List<TrainSchedule> findTrainByStationName(String sourceName, String destinationName) {
        return trainScheduleRepository
                .findBySource_StationNameIgnoreCaseAndDestination_StationNameIgnoreCase(sourceName, destinationName);
    }
}
