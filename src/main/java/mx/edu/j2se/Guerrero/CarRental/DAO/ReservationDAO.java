package mx.edu.j2se.Guerrero.CarRental.DAO;

import mx.edu.j2se.Guerrero.CarRental.Domain.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationDAO extends CrudRepository<Reservation, Long> {
    //metodos personalizados como Queries concretos
}
