package javapro.bank;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BalancePrinter {

    @GetMapping("balance")
    public static String balancePrinter() {
        String balance = "<h4> Current balances of Clients are as follows:</h4>";

        List<Client> clients = GetAllClients.printClients(EntityManagerProvider.getEm());

        for (Client client : clients) {
            if (Balance.printBalance(EntityManagerProvider.getEm(), client) != null) {
                balance += Balance.printBalance(EntityManagerProvider.getEm(), client) + "<br>";
            } else {
                balance += "Client " + client.getName().toUpperCase() + " doesn't have any accounts. <br>";
            }
        }

        return balance;
    }
}
