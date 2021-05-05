package mx.edu.j2se.Guerrero.CarRental.Service;

import mx.edu.j2se.Guerrero.CarRental.Domain.Reservation;

import java.util.List;

public interface ReservationService {

    public List<Reservation> listarReservations();

    public void guardar(Reservation reservation);

    public void eliminar(Reservation reservation);

    public Reservation encontrarReservation(Reservation reservation);
}
