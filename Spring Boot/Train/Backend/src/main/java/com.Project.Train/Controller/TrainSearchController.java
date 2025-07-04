package com.Project.Train.Controller;

import com.Project.Train.Entity.TrainSchedule;
import com.Project.Train.Service.TrainSearchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@CrossOrigin
public class TrainSearchController {
    private final TrainSearchService trainSearchService;

    public TrainSearchController(TrainSearchService trainSearchService) {
        this.trainSearchService = trainSearchService;
    }

    @GetMapping("/by-code")
    public List<TrainSchedule> findTrainByStationCode(
            @RequestParam String sourceCode,
            @RequestParam String destinationCode) {
        return trainSearchService.findTrainByStationCode(sourceCode, destinationCode);
    }

    @GetMapping("/by-name")
    public List<TrainSchedule> findTrainByStationName(
            @RequestParam String sourceName,
            @RequestParam String destinationName) {
        return trainSearchService.findTrainByStationName(sourceName, destinationName);

    }
}
