package ma.ensa.javaProject.Module;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Costmer {
    private int id;
    private String nom;
    private String email;
    private String phone;

    public Costmer(){}

    public Costmer(@JsonProperty("id")int id,@JsonProperty("nom") String nom,@JsonProperty("email") String email,@JsonProperty("phone") String phone) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.phone = phone;
    }

    public Costmer(String nom,String email,String phone) {
        this.nom = nom;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
    public String toJson() throws JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();

            String json = objectMapper.writeValueAsString(this);

            return json;
    }


    public static Costmer toCostmer(String json) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        Costmer costmer = objectMapper.readValue(json, Costmer.class);

        return costmer;
    }

}
