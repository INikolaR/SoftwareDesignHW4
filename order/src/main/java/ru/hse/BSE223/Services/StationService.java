package ru.hse.BSE223.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.BSE223.Repositories.Data.Station;
import ru.hse.BSE223.Repositories.JPAStationRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class StationService {
    JPAStationRepository stationRepository;
    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }
}
