package mx.edu.j2se.Guerrero.CarRental.Service;

import mx.edu.j2se.Guerrero.CarRental.DAO.VehicleDAO;
import mx.edu.j2se.Guerrero.CarRental.Domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehicleServiceImp implements VehicleService {

    @Autowired
    private VehicleDAO vehicleDAO;

    @Override
    @Transactional(readOnly = true)
    public List<Vehicle> listarVehicles() {
        return (List<Vehicle>) vehicleDAO.findAll();
    }

    @Override
    @Transactional
    public void guardar(Vehicle vehicle) {
        vehicleDAO.save(vehicle);
    }

    @Override
    @Transactional
    public void eliminar(Vehicle vehicle) {
        vehicleDAO.delete(vehicle);
    }

    @Override
    @Transactional(readOnly = true)
    public Vehicle encontrarVehicle(Vehicle vehicle) {
        return vehicleDAO.findById(vehicle.getIdVehicle()).orElse(null);
    }
}
