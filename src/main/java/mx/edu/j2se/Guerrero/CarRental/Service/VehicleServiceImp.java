package mx.edu.j2se.Guerrero.CarRental.Service;

import mx.edu.j2se.Guerrero.CarRental.DAO.VehicleDAO;
import mx.edu.j2se.Guerrero.CarRental.Domain.Reservation;
import mx.edu.j2se.Guerrero.CarRental.Domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    public void eliminar(Vehicle vehicle) { vehicleDAO.delete(vehicle); }

    @Override
    @Transactional(readOnly = true)
    public Vehicle encontrarVehicle(Vehicle vehicle) {
        return vehicleDAO.findById(vehicle.getIdVehicle()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicle> findByPriceOrderbyAsc() { return vehicleDAO.findAll(Sort.by(Sort.Direction.ASC,"price")); }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicle> findByPriceOrderbyDesc() { return vehicleDAO.findAll(Sort.by(Sort.Direction.DESC,"price")); }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicle> findByClassOrderbyAsc() { return vehicleDAO.findAll(Sort.by(Sort.Direction.ASC,"typevehicle")); }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicle> findByClassOrderbyDesc() { return vehicleDAO.findAll(Sort.by(Sort.Direction.DESC,"typevehicle")); }
}
