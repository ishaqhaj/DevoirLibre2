package ma.ensa.javaProject.Service;

import ma.ensa.javaProject.Module.Costmer;

import java.util.List;

public interface CostmerInter {
    public List<Costmer> selectAll();
    //public Costmer findById(int id);
    public Costmer selectById(int id);
    public void save(Costmer costmer);
    public void update(Costmer costmer);
    public void delete(int id);
}
