package mx.edu.j2se.Guerrero.CarRental.Service;

import mx.edu.j2se.Guerrero.CarRental.DAO.ReservationDAO;
import mx.edu.j2se.Guerrero.CarRental.Domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservationServiceImp implements ReservationService{

    @Autowired
    private ReservationDAO reservationDAO;

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> listarReservations() {
        return (List<Reservation>) reservationDAO.findAll();
    }

    @Override
    @Transactional
    public void guardar(Reservation reservation) {
        reservationDAO.save(reservation);
    }

    @Override
    @Transactional
    public void eliminar(Reservation reservation) {
        reservationDAO.delete(reservation);
    }

    @Override
    @Transactional(readOnly = true)
    public Reservation encontrarReservation(Reservation reservation) {
        return reservationDAO.findById(reservation.getIdReservation()).orElse(null);
    }
}
