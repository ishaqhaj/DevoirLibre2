package ma.ensa.javaProject.Module;

import ma.ensa.javaProject.Service.CostmerImpl;
import ma.ensa.javaProject.Service.OrderImpl;
import ma.ensa.javaProject.Utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestionnaireData {

    public void gestiondonnee() throws InterruptedException {

        Thread serviceOrders = new Thread(()->{
        OrderImpl orderImpl = new OrderImpl();
        CostmerImpl costmer = new CostmerImpl();
        ObjectMapper objectMapper = new ObjectMapper(); // Crée un ObjectMapper pour désérialiser les JSON
        try (
                BufferedReader br = new BufferedReader(new FileReader("data/input.json"));
                BufferedWriter bw1 = new BufferedWriter(new FileWriter("data/Output.json"));
                BufferedWriter bw2 = new BufferedWriter(new FileWriter("data/Error.json"))
        ) {
            String dataJson;
            List<Order> validOrders = new ArrayList<>();
            List<String> errorLines = new ArrayList<>();

//            br.readLine();
            while ((dataJson = br.readLine()) != null) {
                try {
                    // Désérialiser une ligne JSON en objet Order
                    Order order = objectMapper.readValue(dataJson, Order.class);

                    // Valider l'objet Order
                    if (Utils.isValidOrder(order)) {
                        validOrders.add(order);
                        if (costmer.selectById(order.getCostmerId()) != null){
                            bw1.write(order.toString());
                            bw1.newLine();
                            orderImpl.save(order);
                        }
                    } else {
                        errorLines.add(dataJson);
                        bw2.write("Invalid Order: " + dataJson);
                        bw2.newLine();
                    }
                    System.out.println("l\'order courant est servi !");
                    System.out.println("Attend un 1h pour passer à la prochaine...");
                    Thread.sleep(1000); // pour une Heure remplacer par 60*60*1000
                } catch (Exception e) {
                    // Gérer les lignes qui provoquent une erreur
                    errorLines.add(dataJson);
                    bw2.write(dataJson);
                    bw2.newLine();
                }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter("data/input.json"));
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture/écriture des fichiers...");
            e.printStackTrace();
        }
        });
        serviceOrders.start();
        //serviceOrders.join();
    }
}
