package ru.hse.BSE223.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.BSE223.Data.Station;
import ru.hse.BSE223.Data.Repositories.JPAStationRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class StationServiceImpl implements StationService {
    JPAStationRepository stationRepository;
    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }
}
