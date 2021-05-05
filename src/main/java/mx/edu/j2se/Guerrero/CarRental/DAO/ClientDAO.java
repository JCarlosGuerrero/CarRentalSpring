package mx.edu.j2se.Guerrero.CarRental.DAO;

import mx.edu.j2se.Guerrero.CarRental.Domain.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientDAO extends CrudRepository<Client,Long> {
    //metodos personalizados como Queries concretos
}
