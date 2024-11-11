package gm.zona_fit;
import gm.zona_fit.models.Client;
import gm.zona_fit.DAO.IClientDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ZonaFitApplication implements CommandLineRunner {

	@Autowired
	private IClientDAO clientService;

	private static final Logger logger = LoggerFactory.getLogger(ZonaFitApplication.class);
	String nl = System.lineSeparator();

	public static void main(String[] args) {
		logger.info("===== Start Aplication =====");
		//Levantar la fabrica de spring
		SpringApplication.run(ZonaFitApplication.class, args);
		logger.info("==== Application completed  ======");
	}

	@Override
	public void run(String... args) throws Exception {
		zonaFitApp();
	}

	private void zonaFitApp() {
		var exit = false;
		var console = new Scanner(System.in);
		while (!exit) {
			var opcion = mostrarMenu(console);
			exit = runOptions(console, opcion);
			logger.info(nl);
		}
	}

	private int mostrarMenu(Scanner consola) {
		logger.info(nl +"""
				            ==== Zona Fit (GYM) ====
				            1. List Customer
				            2. Search Client
				            3. Add Client
				            4. Modify Client
				            5. Delete Client
				            6. Exit
				            choose an option:\s
				""" + nl);
		var option = Integer.parseInt(consola.nextLine());
		return option;
	}
	private boolean runOptions(Scanner console, int option) {
		var exit = false;
		switch (option) {
			case 1 -> {
				logger.info(nl + "==== List Customer ====" + nl);
				List<Client> clients = clientService.listCustomer();
				clients.forEach(client -> logger.info(clients.toString() + nl));
			}
			case 2 ->{//Buscar Client
				logger.info(nl + "===== Search Client ======" + nl);
				logger.info(nl + "Enter the id of the Customer to search for" + nl);
				var idClient = Integer.parseInt(console.nextLine());
				Client client = clientService.searchClientById(idClient);
				if(client != null){
					logger.info(nl + "Client found!!!" + client + nl);
				}else{
					logger.info(nl + "Client not found!!!" + client + nl);
				}
			}
			case 3 ->{//Agregar Client
				logger.info(nl + "===== Add Customer ======" + nl);
				logger.info(nl + "Enter the client's name" + nl);
				var name = console.nextLine();
				logger.info(nl + "Enter the client's last name" + nl);
				var lastName = console.nextLine();
				logger.info(nl + "Enter the client's membership" + nl);
				var membership = Integer.parseInt(console.nextLine());
				var client = new Client();
				client.setName(name);
				client.setLastName(lastName);
				client.setMembership(membership);
				clientService.saveClient(client);
				if(client != null){
					logger.info(nl + "Customer added!!!" + client + nl);
				}else{
					logger.info(nl + "Customer not added!!!" + client + nl);
				}
			}
			case 4 ->{//Eliminar Client
				logger.info(nl + "===== Modify Client ======" + nl);
				logger.info(nl + "Enter the client's the id" + nl);
				var idClient = Integer.parseInt(console.nextLine());
				Client client = clientService.searchClientById(idClient);
				if(client != null){
				logger.info(nl + "Enter the client's name" + nl);
				var name = console.nextLine();
				logger.info(nl + "Enter the client's last name" + nl);
				var lastName = console.nextLine();
				logger.info(nl + "Enter the client's membership" + nl);
				var membership = Integer.parseInt(console.nextLine());
				client.setId(idClient);
				client.setName(name);
				client.setLastName(lastName);
				client.setMembership(membership);
				clientService.saveClient(client);
				logger.info(nl + "Client Modificado!!!" + client + nl);
				}else{
					logger.info(nl + "Client No Modificado!!!" + client + nl);
				}
			}
			case 5 ->{//Eliminar client
				logger.info("--- Delete Client ---" + nl);
				logger.info("Enter the client's id: ");
				var idClient = Integer.parseInt(console.nextLine());
				var client = clientService.searchClientById(idClient);
				if(client != null){
					clientService.deleteClient(client);
					logger.info("Customer delete!!!: " + client + nl);
				}
				else
					logger.info("Customer Not Delete: " + client + nl);

			}
			case 6 ->{
				logger.info("Good bye!!!");
				exit = true;
			}
			default -> logger.info("Option not Found" + option);
		}
		return exit;
	}
}
