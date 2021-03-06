package mx.edu.j2se.Guerrero.CarRental.Domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Entity
@Table(name = "client")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    @NotEmpty
    private String nameClient;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String phone;
}
