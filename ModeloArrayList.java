package Ejercicio_Almacen_Serializacion;
import java.util.*;

/**
 * Implementa la parte de Modelo de Datow
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;

import Ejercicio_Almacen.ModeloAbs;
public class ModeloArrayList extends ModeloAbs{
    private ArrayList <Producto> lista;
    Scanner sc = new Scanner(System.in);
    
    public ModeloArrayList() {
       lista=new ArrayList <Producto>();
    }

    
    // Implementar los metodos abstractos de ModeloAbs
    public boolean insertarProducto ( Producto p){
    	boolean result= false;
    	
    	if(p!=null) {
    	lista.add(p);
    	result = true;
    	}
    	
    	return result;  
    }
 
    public boolean borrarProducto ( int codigo ){
    	boolean result = false;
        if(buscarProducto(codigo)!=null) {
      	  lista.remove(buscarProducto(codigo));
      	  result = true;
        }
        return result;
    }
    
    public Producto buscarProducto ( int codigo) {
    	
    	Producto Pro= null;
        
        for(int i=0 ;i<lista.size() ;i++){
        	if(lista.get(i).getCodigo() == codigo){
        		Pro = lista.get(i);
        		break;
        	}
        }
    	return Pro;
    }
    
    public void listarProductos (){
    	for(int i=0; i<lista.size(); i++){
        	System.out.println(lista.get(i));
        }
    }
    
    public void listarPocoStock() {
    	for (int i=0; i<lista.size(); i++){
    		if(lista.get(i).getStock_min() >= lista.get(i).getStock()){
    			System.out.println(lista.get(i));
    		}
    	}
    }
    
    public boolean modificarProducto (Producto nuevo){
    	boolean result = false;
       return result;
    }
}    