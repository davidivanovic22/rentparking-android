package rs.ac.metropolitan.rentparking_mapbox.entity.service;

import java.util.Collection;
import java.util.List;

import rs.ac.metropolitan.rentparking_mapbox.entity.*;

public interface ParkingService {

	List<Parking> findAll();

	List<Parking> findAllByCity(String city);

	Parking save(Parking parking);

	Parking update(Parking parking);

	Parking findById(Integer parkingId);

	void deleteById(Integer parkingId);

}