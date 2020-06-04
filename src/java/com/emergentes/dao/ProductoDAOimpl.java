
package com.emergentes.dao;

import com.emergentes.modelo.Producto;
import com.emergentes.utiles.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOimpl extends ConexionBD implements ProductoDAO {

    @Override
    public void insert(Producto producto) throws Exception {
         try {
        this.conectar();
        PreparedStatement ps = this.conn.prepareStatement("INSERT into productos (descripcion,stock) values (?,?)");
        ps.setString(1, producto.getDescripcion());
        ps.setInt(2, producto.getStock());
        
        ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public void update(Producto producto) throws Exception {
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE productos set descripcion = ?, stock=? WHERE id = ?");
            ps.setString(1, producto.getDescripcion());
            ps.setInt(2,producto.getStock());
            ps.setInt(4, producto.getId());
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM productos where id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public Producto getById(int id) throws Exception {
        Producto pro=new Producto();
        
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM productos where id = ? limit 1");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                pro.setId(id);
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setStock(rs.getInt("stock"));
                
            }
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
        return pro;
    }

    @Override
    public List<Producto> getAll() throws Exception {
     List<Producto> lista = null;
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM productos");
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Producto>();
            while(rs.next()){
                Producto pro= new Producto();
                pro.setId(rs.getInt("id"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setStock(rs.getInt("stock"));
                lista.add(pro);
            }
            rs.close();
            ps.close();
        }catch(Exception e){
            throw e;
        } finally{
            this.desconectar();
        }
        return lista;
    }
    
}
