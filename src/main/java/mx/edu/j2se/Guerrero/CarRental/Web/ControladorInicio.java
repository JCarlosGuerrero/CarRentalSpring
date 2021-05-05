package mx.edu.j2se.Guerrero.CarRental.Web;

import lombok.extern.slf4j.Slf4j;
import mx.edu.j2se.Guerrero.CarRental.Domain.Client;
import mx.edu.j2se.Guerrero.CarRental.Domain.Reservation;
import mx.edu.j2se.Guerrero.CarRental.Domain.Vehicle;
import mx.edu.j2se.Guerrero.CarRental.Service.ClientService;
import mx.edu.j2se.Guerrero.CarRental.Service.ReservationService;
import mx.edu.j2se.Guerrero.CarRental.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
@Slf4j
public class ControladorInicio {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ReservationService reservationService;

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
    public String guardarC(@Valid Client client) {
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
    public String guardarR(Reservation reservation) {
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


}
