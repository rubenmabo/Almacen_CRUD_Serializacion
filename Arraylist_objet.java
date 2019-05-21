package Ejercicio_Almacen_Serializacion;

import java.util.*;
import java.io.*;

public class Arraylist_objet extends ModeloArrayList{
	static final String nom_fich = "productos.csv";

    public Arraylist_objet(){
        super();
        cargarDesdeFichero();  
    }

    public void guardarenfichero(){
        File fproductos = new File (nom_fich);
        
        try{
            FileOutputStream fos= new FileOutputStream(fproductos);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Producto pro:lista){
                oos.writeObject(pro);
            }
            oos.close();
            fos.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    private void cargarDesdeFichero() {
        File fproductos =new File (nom_fich);
        if(!fproductos.exists()){
            return;
        }
        try{
            FileInputStream fin = new FileInputStream(fproductos);
            ObjectInputStream oin = new ObjectInputStream(fin);
            try {
              Producto pro = (Producto) oin.readObject();
              while ( true ){
                lista.add(pro); 
                pro = (Producto) oin.readObject();  
                }
            }
            catch (IOException ex){} 
            oin.close();
            fin.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }
    
    public boolean insertarProducto (Producto p){
        boolean result = super.insertarProducto(p);
        if (result){
            guardarenfichero();
        }
        return result;
    }
    
    public boolean borrarProducto (int codigo){
        boolean result = super.borrarProducto(codigo);
        if (result){
            guardarenfichero();
        }
        return result;
    }
    
    public boolean modificarProducto (Producto nuevo){
        boolean result = super.modificarProducto(nuevo);
        if (result){
            guardarenfichero();
        }
        return result;
    }


}
