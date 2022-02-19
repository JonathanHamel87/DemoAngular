package fr.jha.demoangualr.backend.repositories;

import fr.jha.demoangualr.backend.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarDao extends JpaRepository<Car, Long> {
}
