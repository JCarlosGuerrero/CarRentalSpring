package mx.edu.j2se.Guerrero.CarRental.Service;

import mx.edu.j2se.Guerrero.CarRental.Domain.Reservation;
import mx.edu.j2se.Guerrero.CarRental.Domain.Vehicle;

import java.util.List;

public interface VehicleService {

    public List<Vehicle> listarVehicles();

    public void guardar(Vehicle vehicle);

    public void eliminar(Vehicle vehicle);

    public Vehicle encontrarVehicle(Vehicle vehicle);

    public List<Vehicle> findByPriceOrderbyAsc();

    public List<Vehicle> findByPriceOrderbyDesc();

    public List<Vehicle> findByClassOrderbyAsc();

    public List<Vehicle> findByClassOrderbyDesc();
}
