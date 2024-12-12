package ma.ensa.javaProject.Service;

import ma.ensa.javaProject.DAO.DBConnection;
import ma.ensa.javaProject.Module.Costmer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CostmerImpl implements CostmerInter{
    @Override
    public List<Costmer> selectAll() {
        Connection conn = DBConnection.getConnection();
        if (conn == null) {
            return null;
        }
        String query = "SELECT * FROM costmer;";
        List<Costmer> costmers = new ArrayList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Costmer costmer = new Costmer(rs.getInt("id"),rs.getString("nom")
                        ,rs.getString("email"),rs.getString("phone"));
                costmers.add(costmer);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBConnection.close();
        }
        return costmers;
    }

    @Override
    public Costmer selectById(int id) {
        Connection conn = DBConnection.getConnection();
        if (conn == null) {
            return null;
        }
        String query = "SELECT * FROM costmer WHERE id=?;";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)){
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                return new Costmer(rs.getInt("id"),rs.getString("nom")
                        ,rs.getString("email"),rs.getString("phone"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBConnection.close();
        }
        return null;
    }

    @Override
    public void save(Costmer costmer) {
        Connection conn = DBConnection.getConnection();
        if (conn == null) {
            return;
        }
        String query = "INSERT INTO costmer(id,nom,email,phone) VALUES (?,?,?,?);";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)){
            preparedStatement.setInt(1, costmer.getId());
            preparedStatement.setString(2, costmer.getNom());
            preparedStatement.setString(3, costmer.getEmail());
            preparedStatement.setString(4, costmer.getPhone());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBConnection.close();
        }
    }

    @Override
    public void update(Costmer costmer) {
        Connection conn = DBConnection.getConnection();
        if (conn == null) {
            return;
        }
        String query = "UPDATE SET nom=?,email=?,phone=? WHERE id=?;";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)){
            preparedStatement.setString(1, costmer.getNom());
            preparedStatement.setString(2, costmer.getEmail());
            preparedStatement.setString(3, costmer.getPhone());
            preparedStatement.setInt(4, costmer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBConnection.close();
        }
    }

    @Override
    public void delete(int id) {
        Connection conn = DBConnection.getConnection();
        if (conn == null) {
            return;
        }
        String query = "DELETE FROM costmer WHERE id=?;";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)){
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBConnection.close();
        }
    }
}
