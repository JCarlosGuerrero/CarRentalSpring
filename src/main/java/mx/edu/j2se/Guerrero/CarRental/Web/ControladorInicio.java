package mx.edu.j2se.Guerrero.CarRental.Web;

import lombok.extern.slf4j.Slf4j;
import mx.edu.j2se.Guerrero.CarRental.Domain.*;
import mx.edu.j2se.Guerrero.CarRental.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDate;


@Controller
@Slf4j
public class ControladorInicio {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private RolService rolService;


    @GetMapping
    public String inicio(Model model, @AuthenticationPrincipal User user) {
        Iterable<Vehicle> vehicles = vehicleService.listarVehicles();
        log.info("Ejecutando el controlador Spring MVC");
        log.info("Usuario que se registro: " + user);
        model.addAttribute("vehicles",vehicles);
        return "index";
    }

    @GetMapping("/agregarCliente")
    public String agregarC(Client client) {
        return "modificarCliente";
    }

    @GetMapping("/agregarReservacion")
    public String agregarR(Reservation reservation) {
        return "modificarReservacion";
    }

    @GetMapping("/mostrarClientes")
    public String mostrarC(Model model) {
        Iterable<Client> clients = clientService.listClients();
        model.addAttribute("clients",clients);
        return "mostrarClientes";
    }

    @GetMapping("/mostrarReservaciones")
    public String mostrarR(Model model) {
        Iterable<Reservation> reservations = reservationService.listarReservations();
        model.addAttribute("reservations",reservations);
        return "mostrarReservaciones";
    }

    @GetMapping("/editarCliente/{idClient}")
    public String editarC(Client client, Model model) {
        client = clientService.encontrarClient(client);
        model.addAttribute("client",client);
        return "modificarCliente";
    }

    @PostMapping("/guardarCliente")
    public String guardarC(@Valid Client client, Errors errors) {
        if(errors.hasErrors()){
            return "modificarCliente";
        }
        clientService.guardar(client);
        return "redirect:/";
    }

    @GetMapping("/editarReservacion/{idReservation}")
    public String editarR(Reservation reservation,Model model) {
        reservation = reservationService.encontrarReservation(reservation);
        model.addAttribute("reservation",reservation);
        return "modificarReservacion";
    }

    @PostMapping("/guardarReservacion")
    public String guardarR(@Valid Reservation reservation, Errors errors) {
        if (errors.hasErrors()){
            return "modificarReservacion";
        }


        //reservation.setTotalPrice();
        reservationService.guardar(reservation);
        return "redirect:/";
    }

    @GetMapping("/eliminarCliente/{idClient}")
    public String eliminarC(Client client) {
        clientService.eliminar(client);
        return "redirect:/";
    }

    @GetMapping("/eliminarReservacion/{idReservation}")
    public String eliminarR(Reservation reservation) {
        reservationService.eliminar(reservation);
        return "redirect:/";
    }

    @GetMapping("/newUser")
    public String addU(Users users) {
        return "addUser";
    }

    @PostMapping("/saveuser")
    public String saveU(Users users){
        users.setPassword(encryptPassword(users.getPassword()));
        usersService.guardarU(users);
        Rol rol = new Rol();
        rol.setName("ROLE_USER");
        //rol.setIdUser(users.getIdUser());
        rolService.guardarR(rol);
        return "redirect:/";

    }

    public static String encryptPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.encode(password);
    }
}

