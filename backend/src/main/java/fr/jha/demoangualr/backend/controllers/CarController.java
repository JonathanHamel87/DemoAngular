package fr.jha.demoangualr.backend.controllers;

import fr.jha.demoangualr.backend.exceptions.CarNotFoundException;
import fr.jha.demoangualr.backend.models.Car;
import fr.jha.demoangualr.backend.repositories.CarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin(value = {"http://localhost:4200"})
@RestController
@RequestMapping(value = "/api/cars")
public class CarController {
    @Autowired
    CarDao carDao;

    @GetMapping
    public List<Car> getAll(){
        return carDao.findAll();
    }

    @GetMapping(value = "/{id}")
    public Car getOneById(@PathVariable Long id){
        return carDao.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
    }

    @PostMapping
    public ResponseEntity newCar(@RequestBody Car car) throws URISyntaxException {
        Car savedCar = carDao.save(car);
        return ResponseEntity.created(new URI("/cars/" + savedCar.getId())).body(savedCar);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateCar(@RequestBody Car car, @PathVariable Long id){
        Car currentCar = carDao.findById(id).orElseThrow(() -> new CarNotFoundException(id));
        currentCar.setColor(car.getColor());
        currentCar.setBrand(car.getBrand());
        currentCar = carDao.save(car);

        return ResponseEntity.ok(currentCar);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteCar(@PathVariable Long id){
        carDao.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
