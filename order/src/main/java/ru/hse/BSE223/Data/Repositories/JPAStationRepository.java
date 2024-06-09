package ru.hse.BSE223.Data.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hse.BSE223.Data.Station;

public interface JPAStationRepository extends JpaRepository<Station, Integer> {
}
