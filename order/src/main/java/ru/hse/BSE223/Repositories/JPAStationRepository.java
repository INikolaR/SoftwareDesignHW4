package ru.hse.BSE223.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hse.BSE223.Repositories.Data.Station;

public interface JPAStationRepository extends JpaRepository<Station, Integer> {
}
