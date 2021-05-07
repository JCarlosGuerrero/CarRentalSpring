package mx.edu.j2se.Guerrero.CarRental.DAO;

import mx.edu.j2se.Guerrero.CarRental.Domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolDAO extends JpaRepository<Rol,Long> {
    //metodos personalizaod como Queries concretos
}
