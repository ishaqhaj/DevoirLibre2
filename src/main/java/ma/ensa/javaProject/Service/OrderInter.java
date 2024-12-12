package ma.ensa.javaProject.Service;

import ma.ensa.javaProject.Module.Costmer;
import ma.ensa.javaProject.Module.Order;

import java.util.List;

public interface OrderInter {
    public List<Order> selectAll();
    public List<Order> findById(int idCostmer);
    public Order selectById(int id);
    public void save(Order order);
    public void update(Order order);
    public void delete(int id);
}
