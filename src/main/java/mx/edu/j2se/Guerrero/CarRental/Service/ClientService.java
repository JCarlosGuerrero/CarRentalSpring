package mx.edu.j2se.Guerrero.CarRental.Service;

import mx.edu.j2se.Guerrero.CarRental.Domain.Client;

import java.util.List;

public interface ClientService {

    public List<Client> listClients();

    public void guardar(Client client);

    public void eliminar(Client client);

    public Client encontrarClient(Client client);
}
