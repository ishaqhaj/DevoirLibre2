package ma.ensa.javaProject.Module;

import com.fasterxml.jackson.core.JsonProcessingException;
import ma.ensa.javaProject.Utils.Utils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CostmerTest {

    @Test
    void toJson() throws JsonProcessingException {
        Costmer costmer = new Costmer();
        costmer.setId(1);
        costmer.setNom("JeanDupont");
        costmer.setEmail("jean.dupont@example.com");
        costmer.setPhone("+212123456789");

        String json = costmer.toJson();

        assertTrue(Utils.isPhoneNumber(costmer.getPhone()));
        assertTrue(Utils.isValideEmail(costmer.getEmail()));
        assertTrue(Utils.isAlpha(costmer.getNom()));

        assertTrue(json.contains("\"id\":1"));
        assertTrue(json.contains("\"nom\":\"JeanDupont\""));
        assertTrue(json.contains("\"email\":\"jean.dupont@example.com\""));
        assertTrue(json.contains("\"phone\":\"+212123456789\""));
    }

    @Test
    void toCostmer() throws JsonProcessingException{
        // Étape 1 : Créer un JSON de test
        String json = "{\"id\":1,\"nom\":\"Jean Dupont\",\"email\":\"jean.dupont@example.com\",\"phone\":\"0123456789\"}";

        // Étape 2 : Convertir le JSON en objet Costmer
        Costmer costmer = Costmer.toCostmer(json);

        // Étape 3 : Vérifier les valeurs de l'objet
        assertEquals(1, costmer.getId());
        assertEquals("Jean Dupont", costmer.getNom());
        assertEquals("jean.dupont@example.com", costmer.getEmail());
        assertEquals("0123456789", costmer.getPhone());
    }


    @Test
    public void testToJsonAndBack() throws JsonProcessingException {
        // Étape 1 : Créer une instance de Costmer
        Costmer original = new Costmer();
        original.setId(1);
        original.setNom("Jean Dupont");
        original.setEmail("jean.dupont@example.com");
        original.setPhone("0123456789");

        // Étape 2 : Convertir en JSON
        String json = original.toJson();

        // Étape 3 : Recréer un objet Costmer à partir du JSON
        Costmer recreated = Costmer.toCostmer(json);

        // Étape 4 : Vérifier que les valeurs correspondent
        assertEquals(original.getId(), recreated.getId());
        assertEquals(original.getNom(), recreated.getNom());
        assertEquals(original.getEmail(), recreated.getEmail());
        assertEquals(original.getPhone(), recreated.getPhone());
    }

}