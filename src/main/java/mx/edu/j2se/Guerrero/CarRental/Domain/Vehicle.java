package mx.edu.j2se.Guerrero.CarRental.Domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "vehicle")
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehicle;
    private String model;
    private String brand;
    private String transmission;
    private String typevehicle;
    private int price;
}
