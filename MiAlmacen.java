package Ejercicio_Almacen_Serializacion;

import java.util.*;

import Ejercicio_Almacen.ModeloAbs;

// Crear la clase Producto y completar los métodos

public class MiAlmacen{
    static private ModeloAbs almacen;
    static Scanner sc;
    
    public static void main(String[] args){
        almacen = new ModeloArrayList ();
        sc = new Scanner(System.in);
        int opcion = 0;
        do{
		mostrarMenu();
                opcion = leerOpcion(1,9);
                switch(opcion){
                    case 1: crear();break;
                    case 2: consultar();break;
                    case 3: borrar();break;
                    case 4: modificarPrecio();break;
                    case 5: comprar();break;
                    case 6: vender();break;
                    case 7: listar();break;
                    case 8: listarPocoStock();break;
                }
                System.out.println("\n---------------------------- ");
                System.out.print("Pulse enter para continuar");
                sc.nextLine();
        }while(opcion!=9);
        sc.close();
        
    }
    
    
    private static void mostrarMenu(){
        System.out.println("\n\n    MENU");
        System.out.println("1. Nuevo producto ");
        System.out.println("2. Consulta producto ");
        System.out.println("3. Borrar producto ");
        System.out.println("4. Modificar precio ");
        System.out.println("5. Compra de productos ");
        System.out.println("6. Venta de productos ");
        System.out.println("7. Listado completo de productos ");
        System.out.println("8. Listado de productos con stock inferior al mínimo");
        System.out.println("9. Terminar ");
        System.out.print("Elige una opción (1-9)");        
    }
    
    // Lee un entero del System.in que este comprendido entre primero y ultimo
    private static int leerOpcion(int primero, int ultimo){
        int valor = leerEntero();
        while ( valor < primero || valor > ultimo){
            valor = leerEntero();
        }
        return valor;
    }
      
    
    // Metodos Auxiliares leerFloat y LeerEntero, 
    // Lee de la System.in con el scanner sc y controlan la excepcion de NumberFormatException
    static private float leerFloat(){
        
        boolean error = false;
        float valor =0;
        String cadena;
        do {
        error = false;  
          try {
             // Intento leer directamente un entero  
             cadena = sc.nextLine();
             valor = Float.parseFloat(cadena);
             
            } catch(NumberFormatException e){
              System.out.println("Error en formato.");
              error = true;
            }
        }
       while (error);
       return valor;
    }
    
    static private int leerEntero(){
       String cadena;
       int valor = 0;
       boolean error = false;
       do {
    	   error = false;
    	   try {
    		   cadena = sc.nextLine();
    		   valor = Integer.parseInt(cadena);
    	   }catch (NumberFormatException e) {
    		   System.out.println("ERROR DE FORMATO");
    		   error = true;
    	   }
       }while (error);
    	return valor;
    }

    // Muestra los datos de un producto a partir de su codigo
    
    private static void consultar(){        
       System.out.println("<CONSULTA>");
       System.out.print("Introduzca codigo:");
       int codigo = leerEntero();
       Producto p = almacen.buscarProducto(codigo);
       if (p == null){
           System.out.println("El producto no se encuentra en almacen");
        }
       else {
           System.out.println("PRODUCTO " + p);
        }
       
    }
    
   
    // Borrar un producto a partir de su codigo
    
    private static void borrar(){       
      System.out.println("<ELIMINAR>");
      System.out.println("Introducir codigo de producto que quieres borrar");
      int codigo = leerEntero();
      Producto p = almacen.buscarProducto(codigo);
      if(p == null) {
    	  System.out.println("El producto no existe");
      }
      else {
    	  System.out.println("PRODUCTO " + p);
    	  System.out.println("Desea borrarlo? (S/N)");
    	  char op = sc.nextLine().charAt(0);
    	  if (op == 's' || op == 'S') {
    		  almacen.borrarProducto(codigo);
    		  System.out.println("ELIMINADO");
    	  }else {
    		  System.out.println("NO se ha eliminado");
    	  }
      }
      System.out.println("Aún no disponible");
     
    }
    
    // Cambia el precio de un producto a partir de su codigo
    private static void modificarPrecio (){
       System.out.println("<MODIFICAR PRECIO>");
       System.out.println("Introducir codigo a modificar");
       int codigo = leerEntero();
       Producto p = almacen.buscarProducto(codigo);
       System.out.println("Producto" + p);
       System.out.println("Introduce el precio nuevo");
       float valor = leerFloat();
       if (valor>0) {
    	   p.setPrecio(valor);
       }else {
    	   System.out.println("El precio no puede ser 0 o menor que 0");
       }
    }
    
    
    
    // Incrementa el stock
    private static void comprar(){     
       System.out.println("<COMPRAR>");
       System.out.println("introducir codigo del producto que quieres comprar");
       int codigo = leerEntero();
       Producto p = almacen.buscarProducto(codigo);
       System.out.println("Unidades que quieres comprar?");
       int unidades = leerEntero();
       p.setStock(p.getStock() + unidades);
       System.out.println("Compra realizada"); 
    }
    
    // Decrementa el stock
    private static void vender(){
        System.out.println("<VENDER>");
        System.out.println("Introduce el codigo del producto que quieres vender: ");
        int codigo = leerEntero();
        Producto p = almacen.buscarProducto(codigo);
        System.out.println("Unidades que quieres vender");
        int unidades = leerEntero();
        if(p.getStock() >= unidades){
        	p.setStock(p.getStock() - unidades);
        }else{
        	System.out.println("No hay tantas unidades, tienes: " + p.getStock());
        }
       
    }
    
    // Listado de todos los productos
    private static void listar(){        
         System.out.println("<LISTAR>");
         almacen.listarProductos();
    }
    
    // Listado de todos los productos con stock inferior a stock minimo
    private static void listarPocoStock(){
        System.out.println("<LISTAR STOCK BAJO MINIMOS>");
        almacen.listarPocoStock();
    }
    
    // Solicita datos al usuario para dar de alta un nuevo producto 
    // El codigo no se puede repetir
    private static void crear(){
       System.out.println("<NUEVO PRODUCTO>");
       Producto nuevo = null;
       System.out.println("Nombre del nuevo producto");
       String nom = sc.nextLine();
       System.out.println("Codigo del nuevo producto ");
       int codigo = sc.nextInt();
     	  if(almacen.buscarProducto(codigo) != null) {
     		  System.out.println("ERROR, CODIGO REPETIDO");
     	  }else {
     		    nuevo = new Producto(codigo, nom);
     		   
     	  }
     	     
       System.out.println("Stock del producto creado ");
       int stock=sc.nextInt();
       if(stock>0) {
     	  nuevo.setStock(stock);
       }else {
     	  System.out.println("El stock tiene que ser mayor a 0");
       }
       System.out.println("Stock minimo del producto creado ");
       int min=sc.nextInt();
       if(min>0) {
     	  nuevo.setStock_min(min);
       }else {
     	  System.out.println("El numero debe ser mayor que 0");
       }
       almacen.insertarProducto(nuevo);
    }    
  
}

