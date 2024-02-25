import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

public class DatabaseManager {
    private static final DatabaseManager instance = new DatabaseManager();
    private final EntityManagerFactory entityManagerFactory;

    private DatabaseManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory("CandyShopPU");
    }

    public static DatabaseManager getInstance() {
        return instance;
    }

    public void addProduct(Product product) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(product);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }


    public Product getProductByName(String productName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p WHERE p.name = :name", Product.class);
            query.setParameter("name", productName);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public List<Product> getProducts() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Product> query = cb.createQuery(Product.class);
            Root<Product> root = query.from(Product.class);
            query.select(root);
            return entityManager.createQuery(query).getResultList();
        } finally {
            entityManager.close();
        }
    }
}

