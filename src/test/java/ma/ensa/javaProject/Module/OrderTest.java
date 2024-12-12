package ma.ensa.javaProject.Module;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    public void testToJson() throws JsonProcessingException {
        // Créer une instance de Costmer
        Costmer costmer = new Costmer();
        costmer.setId(1);
        costmer.setNom("Jean Dupont");
        costmer.setEmail("jean.dupont@example.com");
        costmer.setPhone("0123456789");

        // Créer une instance de Order
        Order order = new Order();
        order.setId(1);
        order.setDate(new java.util.Date());
        order.setAmount(99.99);
        order.setCostmerId(costmer.getId());
        order.setCostmer(costmer);

        // Convertir l'objet en JSON
        String json = order.toJson();
        System.out.println("JSON généré : " + json);

        // Vérifier que le JSON contient les valeurs correctes
        assertTrue(json.contains("\"id\":1"));
        assertTrue(json.contains("\"amount\":99.99"));
        assertTrue(json.contains("\"costmerId\":1"));
    }

    @Test
    public void testToOrder() throws JsonProcessingException {
        // Créer un JSON de test
        String json = "{\"id\":1,\"date\":1677634845000,\"amount\":99.99,\"costmerId\":1,\"costmer\":{\"id\":1,\"nom\":\"Jean Dupont\",\"email\":\"jean.dupont@example.com\",\"phone\":\"0123456789\"}}";

        // Convertir le JSON en objet Order
        Order order = Order.toOrder(json);

        // Vérifier les valeurs de l'objet
        assertEquals(1, order.getId());
        assertEquals(99.99, order.getAmount());
        assertEquals(1, order.getCostmerId());
        assertNotNull(order.getCostmer());
        assertEquals("Jean Dupont", order.getCostmer().getNom());
        assertEquals("jean.dupont@example.com", order.getCostmer().getEmail());
    }

    @Test
    public void testToJsonAndBack() throws JsonProcessingException {
        // Créer une instance de Costmer
        Costmer costmer = new Costmer();
        costmer.setId(1);
        costmer.setNom("Jean Dupont");
        costmer.setEmail("jean.dupont@example.com");
        costmer.setPhone("0123456789");

        // Créer une instance de Order
        Order originalOrder = new Order();
        originalOrder.setId(1);
        originalOrder.setDate(new java.util.Date());
        originalOrder.setAmount(99.99);
        originalOrder.setCostmerId(costmer.getId());
        originalOrder.setCostmer(costmer);

        // Convertir en JSON
        String json = originalOrder.toJson();

        // Recréer un objet Order à partir du JSON
        Order recreatedOrder = Order.toOrder(json);

        // Vérifier que les valeurs correspondent
        assertEquals(originalOrder.getId(), recreatedOrder.getId());
        assertEquals(originalOrder.getAmount(), recreatedOrder.getAmount());
        assertEquals(originalOrder.getCostmerId(), recreatedOrder.getCostmerId());
        assertEquals(originalOrder.getCostmer().getNom(), recreatedOrder.getCostmer().getNom());
    }
}