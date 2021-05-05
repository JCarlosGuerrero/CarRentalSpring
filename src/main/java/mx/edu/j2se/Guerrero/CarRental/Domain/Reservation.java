package mx.edu.j2se.Guerrero.CarRental.Domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;
    private int idVehicle;
    private int idClient;
    private String typevehicle;
    private LocalDateTime deliverDate;
    private LocalDateTime returnDate;
    private int totalPrice;
}
