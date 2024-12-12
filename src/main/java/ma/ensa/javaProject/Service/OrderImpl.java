package ma.ensa.javaProject.Service;

import ma.ensa.javaProject.DAO.DBConnection;
import ma.ensa.javaProject.Module.Costmer;
import ma.ensa.javaProject.Module.Order;
import ma.ensa.javaProject.Utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderImpl implements OrderInter{
    @Override
    public List<Order> selectAll() {
        Connection conn = DBConnection.getConnection();
        if (conn == null) {
            return null;
        }
        String query = "SELECT * FROM orders;";
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Order order = new Order(rs.getInt("id"),rs.getDate("date"),
                        rs.getDouble("amount"),rs.getInt("costmerId"));
                orders.add(order);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBConnection.close();
        }
            return orders;
    }

    //les orders d'un costmer
    @Override
    public List<Order> findById(int idCostmer){
        Connection conn = DBConnection.getConnection();
        if (conn == null) {
            return null;
        }
        String query = "SELECT * FROM orders WHERE costmerId=?;";
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)){
            preparedStatement.setInt(1,idCostmer);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Order order = new Order(rs.getInt("id"),rs.getDate("date"),
                        rs.getDouble("amount"),rs.getInt("costmerId"));
                orders.add(order);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBConnection.close();
        }
        return orders;
    }

    @Override
    public Order selectById(int id) {
        Connection conn = DBConnection.getConnection();
        if (conn == null) {
            return null;
        }
        String query = "SELECT * FROM orders WHERE id=?;";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)){
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                return new Order(rs.getInt("id"),rs.getDate("date"),
                        rs.getDouble("amount"),rs.getInt("costmerId"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBConnection.close();
        }
        return null;
    }


    @Override
    public void save(Order order) {
        Connection conn = DBConnection.getConnection();
        if (conn == null) {
            return;
        }
        String query = "INSERT INTO orders (id, date, amount, costmerId) VALUES (?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, order.getId());
            preparedStatement.setDate(2, Utils.convertDate(order.getDate()));
            preparedStatement.setDouble(3, order.getAmount());
            preparedStatement.setInt(4, order.getCostmerId());
            preparedStatement.executeUpdate(); // Execute the statement
        } catch (SQLException e) {
            System.err.println("Error while saving order: " + e.getMessage());
        } finally {
            DBConnection.close();
        }
    }

    @Override
    public void update(Order order) {
        Connection conn = DBConnection.getConnection();
        if (conn == null) {
            return;
        }
        String query = "UPDATE orders SET date = ?, amount = ?, costmerId = ? WHERE id = ?;";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setDate(1, Utils.convertDate(order.getDate()));
            preparedStatement.setDouble(2, order.getAmount());
            preparedStatement.setInt(3, order.getCostmerId());
            preparedStatement.setInt(4, order.getId());
            preparedStatement.executeUpdate(); // Execute the statement
        } catch (SQLException e) {
            System.err.println("Error while updating order: " + e.getMessage());
        } finally {
            DBConnection.close();
        }
    }

    @Override
    public void delete(int id) {
        Connection conn = DBConnection.getConnection();
        if (conn == null) {
            return;
        }
        String query = "DELETE FROM orders WHERE id=?;";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)){
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}