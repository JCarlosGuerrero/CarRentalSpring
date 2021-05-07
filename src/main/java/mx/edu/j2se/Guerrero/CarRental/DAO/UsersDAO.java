package mx.edu.j2se.Guerrero.CarRental.DAO;

import mx.edu.j2se.Guerrero.CarRental.Domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersDAO extends JpaRepository<Users, Long> {
    //metodos personalizados como Queries concretos
    Users findByUsername(String username);
}
