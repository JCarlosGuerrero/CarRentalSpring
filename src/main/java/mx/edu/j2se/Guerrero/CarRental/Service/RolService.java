package mx.edu.j2se.Guerrero.CarRental.Service;

import mx.edu.j2se.Guerrero.CarRental.DAO.RolDAO;
import mx.edu.j2se.Guerrero.CarRental.Domain.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolService {

    @Autowired
    private RolDAO rolDAO;

    @Transactional
    public void guardarR(Rol rol) {rolDAO.save(rol); }
}
