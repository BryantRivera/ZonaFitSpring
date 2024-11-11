package gm.zona_fit.DAO;

import gm.zona_fit.models.Client;
import gm.zona_fit.repositorio.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ClientDAO implements IClientDAO {

    @Autowired//Auto inyectar los datos de la iterface ClientRepository
    private ClientRepository clientRepository;

    @Override
    public List<Client> listCustomer() {
        List<Client> clients = clientRepository.findAll();
        return clients;
    }

    @Override
    public Client searchClientById(Integer idClient) {
        Client client = clientRepository.findById(idClient).orElse(null);
        return client;
    }

    @Override
    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public Client deleteClient(Client client) {
        clientRepository.delete(client);
        return client;
    }
}
