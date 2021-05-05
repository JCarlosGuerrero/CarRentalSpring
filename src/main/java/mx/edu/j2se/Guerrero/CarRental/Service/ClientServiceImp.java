package mx.edu.j2se.Guerrero.CarRental.Service;

import mx.edu.j2se.Guerrero.CarRental.DAO.ClientDAO;
import mx.edu.j2se.Guerrero.CarRental.Domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientServiceImp implements ClientService{

    @Autowired
    private ClientDAO clientDAO;

    @Override
    @Transactional(readOnly = true)
    public List<Client> listClients() {
        return (List<Client>) clientDAO.findAll();
    }

    @Override
    @Transactional
    public void guardar(Client client) {
        clientDAO.save(client);
    }

    @Override
    @Transactional
    public void eliminar(Client client) {
        clientDAO.delete(client);
    }

    @Override
    @Transactional(readOnly = true)
    public Client encontrarClient(Client client) {
        return clientDAO.findById(client.getIdClient()).orElse(null);
    }
}
