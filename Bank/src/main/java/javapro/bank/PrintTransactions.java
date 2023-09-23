package javapro.bank;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

import static javax.persistence.Persistence.createEntityManagerFactory;

@RestController
public class PrintTransactions {

    @GetMapping("transactions")
    public static String printTransactions() {
        String transactions = "<h4>Transactions are as follows: </h4>";
        EntityManagerFactory emf = createEntityManagerFactory("Bank");
        EntityManager em = emf.createEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Transaction> cq = cb.createQuery(Transaction.class);
        Root<Transaction> root = cq.from(Transaction.class);
        cq.select(root);
        TypedQuery<Transaction> tq = em.createQuery(cq);
        List<Transaction> list = tq.getResultList();

        if (list.isEmpty()) {
            return "<h4>There are no transactions yet</h4>";
        }
        for (Transaction transaction : list) {
            transactions += transaction.toString() + "<br>";
        }
        return transactions + "<h4>Total number of transactions: " + list.size() + "</h4>";
    }
}
