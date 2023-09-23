package javapro.bank;

import javax.persistence.EntityManager;
import java.util.*;

public class Balance {
    public static String printBalance(EntityManager em, Client client) {
        Set<Account> set = client.getAccounts();
        StringBuilder resultAccounts = new StringBuilder("Client " + client.getName().toUpperCase() + " has the following accounts: ");

        if (!set.isEmpty()) {
            for (Account account : set) {
                resultAccounts.append(account.getCurrency()).append(" (amount: ").append(account.getAmount());
                if (!account.getCurrency().equals("UAH")) {
                    resultAccounts.append("/").append("UAH equivalent: ").append(account.getAmount() * em.find(ExRate.class, account.getCurrency() + "UAH").getExRateValue()).append("), ");
                } else {
                    resultAccounts.append("), ");
                }
            }
        } else {
            System.out.println("Client doesn't have any accounts.");
            return null;
        }

        resultAccounts.delete(resultAccounts.length() - 3, resultAccounts.length()).append(")");
        System.out.println(resultAccounts);

        return resultAccounts.toString();
    }
}
