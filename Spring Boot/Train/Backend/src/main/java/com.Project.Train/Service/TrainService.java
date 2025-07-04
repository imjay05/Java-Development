package com.Project.Train.Service;

import com.Project.Train.Entity.Train;
import com.Project.Train.Repo.TrainRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {

    private TrainRepository trainRepository;

    public TrainService(TrainRepository trainRepository){
        this.trainRepository = trainRepository;
    }

    public List<Train> getAllTrains(){
        return  trainRepository.findAll();
    }

    public  Train addTrain(Train train){
        return  trainRepository.save(train);
    }
}
