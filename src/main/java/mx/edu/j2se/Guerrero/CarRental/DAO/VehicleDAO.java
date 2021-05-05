package mx.edu.j2se.Guerrero.CarRental.DAO;

import mx.edu.j2se.Guerrero.CarRental.Domain.Vehicle;
import org.springframework.data.repository.CrudRepository;

public interface VehicleDAO extends CrudRepository<Vehicle,Long> {
    //metodos personalizados como Queries concretos
}
