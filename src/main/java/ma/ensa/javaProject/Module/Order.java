package ma.ensa.javaProject.Module;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

public class Order {
    private int id;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date date;
    private double amount;
    @JsonProperty("costmerId")
    private int costmerId;
    private Costmer costmer;

//    public Order(int id, Date date, double amount, int costmerId, Costmer costmer) {
//        {
//            this.id = id;
//            this.date = date;
//            this.amount = amount;
//            this.costmerId = costmerId;
//            this.costmer = costmer;
//        }
//    }
    public Order(){}


    @JsonCreator
    public Order(@JsonProperty("id")int id,@JsonProperty("date") Date date,@JsonProperty("amount") double amount,@JsonProperty("costmerId") int costmerId) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.costmerId = costmerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Costmer getCostmer() {
        return costmer;
    }

    public void setCostmer(Costmer costmer) {
        this.costmer = costmer;
    }

    public int getCostmerId() {
        return costmerId;
    }

    public void setCostmerId(int costmerId) {
        this.costmerId = costmerId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", costmerId=" + costmerId +
                '}';
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(this);
        return json;
    }

    public static Order toOrder(String json) throws JsonProcessingException{

        ObjectMapper objectMapper = new ObjectMapper();
        Order order = objectMapper.readValue(json, Order.class);

        return order;
    }
}
