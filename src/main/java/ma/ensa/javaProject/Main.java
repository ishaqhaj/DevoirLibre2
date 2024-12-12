package ma.ensa.javaProject;

import com.fasterxml.jackson.core.JsonProcessingException;
import ma.ensa.javaProject.Module.GestionnaireData;

public class Main {
    public static void main(String[] args) throws JsonProcessingException, InterruptedException {

        GestionnaireData gd = new GestionnaireData();
        gd.gestiondonnee();

    }
}