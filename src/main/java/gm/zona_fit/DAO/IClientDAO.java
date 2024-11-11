package gm.zona_fit.DAO;

import gm.zona_fit.models.Client;

import java.util.List;

public interface IClientDAO {
    public List<Client> listCustomer();

    public Client searchClientById(Integer idClient);

    public void saveClient(Client client);

    public Client deleteClient(Client idClient);

}
