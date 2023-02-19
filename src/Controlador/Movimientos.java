
package Controlador;

import javax.swing.JTabbedPane;

public class Movimientos {
    
    public static void Tabla(JTabbedPane tablamadre, int seleccion){/*
        if(seleccion==0){
        tablamadre.setEnabledAt(0, true);
        tablamadre.setEnabledAt(1, false);
        tablamadre.setEnabledAt(2, false);
        tablamadre.setEnabledAt(3, false);
        tablamadre.setEnabledAt(4, false);
        tablamadre.setEnabledAt(5, false);
        tablamadre.setEnabledAt(6, false);
        tablamadre.setEnabledAt(7, false);
        tablamadre.setEnabledAt(8, false);
        tablamadre.setEnabledAt(9, false);
        tablamadre.setEnabledAt(10, false);
        tablamadre.setEnabledAt(11, false);
        tablamadre.setEnabledAt(12, false);
        tablamadre.setEnabledAt(13, false);
        tablamadre.setEnabledAt(14, false);
        tablamadre.setEnabledAt(15, false);
        
        tablamadre.setTitleAt(seleccion, "<html>HOME</html>");
        tablamadre.setTitleAt(1, "");
        tablamadre.setTitleAt(2, "");
        tablamadre.setTitleAt(3, "");
        tablamadre.setTitleAt(4, "");
        tablamadre.setTitleAt(5, "");
        tablamadre.setTitleAt(6, "");
        tablamadre.setTitleAt(7, "");
        tablamadre.setTitleAt(8, "");
        tablamadre.setTitleAt(9, "");
        tablamadre.setTitleAt(10, "");
        tablamadre.setTitleAt(11, "");
        tablamadre.setTitleAt(12, "");
        tablamadre.setTitleAt(13, "");
        tablamadre.setTitleAt(14, "");
        tablamadre.setTitleAt(15, "");
        
        }else if(seleccion==1){
        tablamadre.setEnabledAt(0, false);
        tablamadre.setEnabledAt(1, true);
        tablamadre.setEnabledAt(2, false);
        tablamadre.setEnabledAt(3, false);
        tablamadre.setEnabledAt(4, false);
        tablamadre.setEnabledAt(5, false);
        tablamadre.setEnabledAt(6, false);
        tablamadre.setEnabledAt(7, false);
        tablamadre.setEnabledAt(8, false);
        tablamadre.setEnabledAt(9, false);
        tablamadre.setEnabledAt(10, false);
        tablamadre.setEnabledAt(11, false);
        tablamadre.setEnabledAt(12, false);
        tablamadre.setEnabledAt(13, false);
        tablamadre.setEnabledAt(14, false);
        tablamadre.setEnabledAt(15, false);
        
        tablamadre.setTitleAt(0, "");
        tablamadre.setTitleAt(seleccion, "<html>POS</html>");
        tablamadre.setTitleAt(2, "");
        tablamadre.setTitleAt(3, "");
        tablamadre.setTitleAt(4, "");
        tablamadre.setTitleAt(5, "");
        tablamadre.setTitleAt(6, "");
        tablamadre.setTitleAt(7, "");
        tablamadre.setTitleAt(8, "");
        tablamadre.setTitleAt(9, "");
        tablamadre.setTitleAt(10, "");
        tablamadre.setTitleAt(11, "");
        tablamadre.setTitleAt(12, "");
        tablamadre.setTitleAt(13, "");
        tablamadre.setTitleAt(14, "");
        tablamadre.setTitleAt(15, "");
        
        }else if(seleccion==2){
        tablamadre.setEnabledAt(0, false);
        tablamadre.setEnabledAt(1, false);
        tablamadre.setEnabledAt(2, true);
        tablamadre.setEnabledAt(3, false);
        tablamadre.setEnabledAt(4, false);
        tablamadre.setEnabledAt(5, false);
        tablamadre.setEnabledAt(6, false);
        tablamadre.setEnabledAt(7, false);
        tablamadre.setEnabledAt(8, false);
        tablamadre.setEnabledAt(9, false);
        tablamadre.setEnabledAt(10, false);
        tablamadre.setEnabledAt(11, false);
        tablamadre.setEnabledAt(12, false);
        tablamadre.setEnabledAt(13, false);
        tablamadre.setEnabledAt(14, false);
        tablamadre.setEnabledAt(15, false);
        
        tablamadre.setTitleAt(0, "");
        tablamadre.setTitleAt(1, "");
        tablamadre.setTitleAt(seleccion, "<html>INVENTARIO</html>");
        tablamadre.setTitleAt(3, "");
        tablamadre.setTitleAt(4, "");
        tablamadre.setTitleAt(5, "");
        tablamadre.setTitleAt(6, "");
        tablamadre.setTitleAt(7, "");
        tablamadre.setTitleAt(8, "");
        tablamadre.setTitleAt(9, "");
        tablamadre.setTitleAt(10, "");
        tablamadre.setTitleAt(11, "");
        tablamadre.setTitleAt(12, "");
        tablamadre.setTitleAt(13, "");
        tablamadre.setTitleAt(14, "");
        tablamadre.setTitleAt(15, "");
        
        }else if(seleccion==3){
            tablamadre.setEnabledAt(0, false);
        tablamadre.setEnabledAt(1, false);
        tablamadre.setEnabledAt(2, false);
        tablamadre.setEnabledAt(4, false);
        tablamadre.setEnabledAt(5, false);
        tablamadre.setEnabledAt(6, false);
        tablamadre.setEnabledAt(7, false);
        tablamadre.setEnabledAt(8, false);
        tablamadre.setEnabledAt(9, false);
        tablamadre.setEnabledAt(10, false);
        tablamadre.setEnabledAt(11, false);
        tablamadre.setEnabledAt(12, false);
        tablamadre.setEnabledAt(13, false);
        tablamadre.setEnabledAt(14, false);
        tablamadre.setEnabledAt(15, false);
       
        tablamadre.setTitleAt(0, "");
        tablamadre.setTitleAt(1, "");
        tablamadre.setTitleAt(2, "");
        tablamadre.setTitleAt(seleccion, "<html>PROVEEDORES</html>");
        tablamadre.setTitleAt(4, "");
        tablamadre.setTitleAt(5, "");
        tablamadre.setTitleAt(6, "");
        tablamadre.setTitleAt(7, "");
        tablamadre.setTitleAt(8, "");
        tablamadre.setTitleAt(9, "");
        tablamadre.setTitleAt(10, "");
        tablamadre.setTitleAt(11, "");
        tablamadre.setTitleAt(12, "");
        tablamadre.setTitleAt(13, "");
        tablamadre.setTitleAt(14, "");
        tablamadre.setTitleAt(15, "");
        
        }else if(seleccion==4){
            
            tablamadre.setEnabledAt(0, false);
        tablamadre.setEnabledAt(1, false);
        tablamadre.setEnabledAt(2, false);
        tablamadre.setEnabledAt(3, false);
        tablamadre.setEnabledAt(4, true);
        tablamadre.setEnabledAt(5, false);
        tablamadre.setEnabledAt(6, false);
        tablamadre.setEnabledAt(7, false);
        tablamadre.setEnabledAt(8, false);
        tablamadre.setEnabledAt(9, false);
        tablamadre.setEnabledAt(10, false);
        tablamadre.setEnabledAt(11, false);
        tablamadre.setEnabledAt(12, false);
        tablamadre.setEnabledAt(13, false);
        tablamadre.setEnabledAt(14, false);
        tablamadre.setEnabledAt(15, false);
        
        tablamadre.setTitleAt(0, "");
        tablamadre.setTitleAt(1, "");
        tablamadre.setTitleAt(2, "");
        tablamadre.setTitleAt(3, "");
        tablamadre.setTitleAt(seleccion, "<html>SESIÓN</html>");
        tablamadre.setTitleAt(5, "");
        tablamadre.setTitleAt(6, "");
        tablamadre.setTitleAt(7, "");
        tablamadre.setTitleAt(8, "");
        tablamadre.setTitleAt(9, "");
        tablamadre.setTitleAt(10, "");
        tablamadre.setTitleAt(11, "");
        tablamadre.setTitleAt(12, "");
        tablamadre.setTitleAt(13, "");
        tablamadre.setTitleAt(14, "");
        tablamadre.setTitleAt(15, "");
        
        }else if(seleccion==5){
            
            tablamadre.setEnabledAt(0, false);
        tablamadre.setEnabledAt(1, false);
        tablamadre.setEnabledAt(2, false);
        tablamadre.setEnabledAt(3, false);
        tablamadre.setEnabledAt(4, false);
        tablamadre.setEnabledAt(5, true);
        tablamadre.setEnabledAt(6, false);
        tablamadre.setEnabledAt(7, false);
        tablamadre.setEnabledAt(8, false);
        tablamadre.setEnabledAt(9, false);
        tablamadre.setEnabledAt(10, false);
        tablamadre.setEnabledAt(11, false);
        tablamadre.setEnabledAt(12, false);
        tablamadre.setEnabledAt(13, false);
        tablamadre.setEnabledAt(14, false);
        tablamadre.setEnabledAt(15, false);
        
        tablamadre.setTitleAt(0, "");
        tablamadre.setTitleAt(1, "");
        tablamadre.setTitleAt(2, "");
        tablamadre.setTitleAt(3, "");
        tablamadre.setTitleAt(4, "");
        tablamadre.setTitleAt(6, "");
        tablamadre.setTitleAt(7, "");
        tablamadre.setTitleAt(8, "");
        tablamadre.setTitleAt(9, "");
        tablamadre.setTitleAt(10, "");
        tablamadre.setTitleAt(11, "");
        tablamadre.setTitleAt(12, "");
        tablamadre.setTitleAt(13, "");
        tablamadre.setTitleAt(14, "");
        tablamadre.setTitleAt(15, "");
        
        }else if(seleccion==6){
            
            tablamadre.setEnabledAt(0, false);
        tablamadre.setEnabledAt(1, false);
        tablamadre.setEnabledAt(2, false);
        tablamadre.setEnabledAt(3, false);
        tablamadre.setEnabledAt(4, false);
        tablamadre.setEnabledAt(5, false);
        tablamadre.setEnabledAt(6, true);
        tablamadre.setEnabledAt(7, false);
        tablamadre.setEnabledAt(8, false);
        tablamadre.setEnabledAt(9, false);
        tablamadre.setEnabledAt(10, false);
        tablamadre.setEnabledAt(11, false);
        tablamadre.setEnabledAt(12, false);
        tablamadre.setEnabledAt(13, false);
        tablamadre.setEnabledAt(14, false);
        tablamadre.setEnabledAt(15, false);
        
        tablamadre.setTitleAt(0, "");
        tablamadre.setTitleAt(1, "");
        tablamadre.setTitleAt(2, "");
        tablamadre.setTitleAt(3, "");
        tablamadre.setTitleAt(4, "");
        tablamadre.setTitleAt(5, "");
        tablamadre.setTitleAt(seleccion, "<html>VALES</html>");
        tablamadre.setTitleAt(7, "");
        tablamadre.setTitleAt(8, "");
        tablamadre.setTitleAt(9, "");
        tablamadre.setTitleAt(10, "");
        tablamadre.setTitleAt(11, "");
        tablamadre.setTitleAt(12, "");
        tablamadre.setTitleAt(13, "");
        tablamadre.setTitleAt(14, "");
        tablamadre.setTitleAt(15, "");
        
        }else if(seleccion==7){
            
            tablamadre.setEnabledAt(0, false);
        tablamadre.setEnabledAt(1, false);
        tablamadre.setEnabledAt(2, false);
        tablamadre.setEnabledAt(3, false);
        tablamadre.setEnabledAt(4, false);
        tablamadre.setEnabledAt(5, false);
        tablamadre.setEnabledAt(6, false);
        tablamadre.setEnabledAt(7, true);
        tablamadre.setEnabledAt(8, false);
        tablamadre.setEnabledAt(9, false);
        tablamadre.setEnabledAt(10, false);
        tablamadre.setEnabledAt(11, false);
        tablamadre.setEnabledAt(12, false);
        tablamadre.setEnabledAt(13, false);
        tablamadre.setEnabledAt(14, false);
        tablamadre.setEnabledAt(15, false);
        
        tablamadre.setTitleAt(0, "");
        tablamadre.setTitleAt(1, "");
        tablamadre.setTitleAt(2, "");
        tablamadre.setTitleAt(3, "");
        tablamadre.setTitleAt(4, "");
        tablamadre.setTitleAt(5, "");
        tablamadre.setTitleAt(6, "");
        tablamadre.setTitleAt(seleccion, "<html>VENTAS DEL DÍA</html>");
        tablamadre.setTitleAt(8, "");
        tablamadre.setTitleAt(9, "");
        tablamadre.setTitleAt(10, "");
        tablamadre.setTitleAt(11, "");
        tablamadre.setTitleAt(12, "");
        tablamadre.setTitleAt(13, "");
        tablamadre.setTitleAt(14, "");
        tablamadre.setTitleAt(15, "");
        
        }else if(seleccion==8){
            
            tablamadre.setEnabledAt(0, false);
        tablamadre.setEnabledAt(1, false);
        tablamadre.setEnabledAt(2, false);
        tablamadre.setEnabledAt(3, false);
        tablamadre.setEnabledAt(4, false);
        tablamadre.setEnabledAt(5, false);
        tablamadre.setEnabledAt(6, false);
        tablamadre.setEnabledAt(7, false);
        tablamadre.setEnabledAt(8, true);
        tablamadre.setEnabledAt(9, false);
        tablamadre.setEnabledAt(10, false);
        tablamadre.setEnabledAt(11, false);
        tablamadre.setEnabledAt(12, false);
        tablamadre.setEnabledAt(13, false);
        tablamadre.setEnabledAt(14, false);
        tablamadre.setEnabledAt(15, false);
        
        tablamadre.setTitleAt(0, "");
        tablamadre.setTitleAt(1, "");
        tablamadre.setTitleAt(2, "");
        tablamadre.setTitleAt(3, "");
        tablamadre.setTitleAt(4, "");
        tablamadre.setTitleAt(5, "");
        tablamadre.setTitleAt(6, "");
        tablamadre.setTitleAt(7, "");
        tablamadre.setTitleAt(seleccion, "<html>ADMINISTRACIÓN DE VALES</html>");
        tablamadre.setTitleAt(9, "");
        tablamadre.setTitleAt(10, "");
        tablamadre.setTitleAt(11, "");
        tablamadre.setTitleAt(12, "");
        tablamadre.setTitleAt(13, "");
        tablamadre.setTitleAt(14, "");
        tablamadre.setTitleAt(15, "");
        
        }else if(seleccion==9){
            
            tablamadre.setEnabledAt(0, false);
        tablamadre.setEnabledAt(1, false);
        tablamadre.setEnabledAt(2, false);
        tablamadre.setEnabledAt(3, false);
        tablamadre.setEnabledAt(4, false);
        tablamadre.setEnabledAt(5, false);
        tablamadre.setEnabledAt(6, false);
        tablamadre.setEnabledAt(7, false);
        tablamadre.setEnabledAt(8, false);
        tablamadre.setEnabledAt(9, true);
        tablamadre.setEnabledAt(10, false);
        tablamadre.setEnabledAt(11, false);
        tablamadre.setEnabledAt(12, false);
        tablamadre.setEnabledAt(13, false);
        tablamadre.setEnabledAt(14, false);
        tablamadre.setEnabledAt(15, false);
        
        tablamadre.setTitleAt(0, "");
        tablamadre.setTitleAt(1, "");
        tablamadre.setTitleAt(2, "");
        tablamadre.setTitleAt(3, "");
        tablamadre.setTitleAt(4, "");
        tablamadre.setTitleAt(5, "");
        tablamadre.setTitleAt(6, "");
        tablamadre.setTitleAt(7, "");
        tablamadre.setTitleAt(8, "");
        tablamadre.setTitleAt(seleccion, "<html>ADMINISTRACIÓN DE CLIENTES</html>");
        tablamadre.setTitleAt(10, "");
        tablamadre.setTitleAt(11, "");
        tablamadre.setTitleAt(12, "");
        tablamadre.setTitleAt(13, "");
        tablamadre.setTitleAt(14, "");
        tablamadre.setTitleAt(15, "");
        
        }else if(seleccion==10){
            
            tablamadre.setEnabledAt(0, false);
        tablamadre.setEnabledAt(1, false);
        tablamadre.setEnabledAt(2, false);
        tablamadre.setEnabledAt(3, false);
        tablamadre.setEnabledAt(4, false);
        tablamadre.setEnabledAt(5, false);
        tablamadre.setEnabledAt(6, false);
        tablamadre.setEnabledAt(7, false);
        tablamadre.setEnabledAt(8, false);
        tablamadre.setEnabledAt(9, false);
        tablamadre.setEnabledAt(10, true);
        tablamadre.setEnabledAt(11, false);
        tablamadre.setEnabledAt(12, false);
        tablamadre.setEnabledAt(13, false);
        tablamadre.setEnabledAt(14, false);
        tablamadre.setEnabledAt(15, false);
        
        tablamadre.setTitleAt(0, "");
        tablamadre.setTitleAt(1, "");
        tablamadre.setTitleAt(2, "");
        tablamadre.setTitleAt(3, "");
        tablamadre.setTitleAt(4, "");
        tablamadre.setTitleAt(5, "");
        tablamadre.setTitleAt(6, "");
        tablamadre.setTitleAt(7, "");
        tablamadre.setTitleAt(8, "");
        tablamadre.setTitleAt(9, "");
        tablamadre.setTitleAt(seleccion, "<html>SELECCIÓN DEL VALE</html>");
        tablamadre.setTitleAt(11, "");
        tablamadre.setTitleAt(12, "");
        tablamadre.setTitleAt(13, "");
        tablamadre.setTitleAt(14, "");
        tablamadre.setTitleAt(15, "");
        
        }else if(seleccion==11){
            
            tablamadre.setEnabledAt(0, false);
        tablamadre.setEnabledAt(1, false);
        tablamadre.setEnabledAt(2, false);
        tablamadre.setEnabledAt(3, false);
        tablamadre.setEnabledAt(4, false);
        tablamadre.setEnabledAt(5, false);
        tablamadre.setEnabledAt(6, false);
        tablamadre.setEnabledAt(7, false);
        tablamadre.setEnabledAt(8, false);
        tablamadre.setEnabledAt(9, false);
        tablamadre.setEnabledAt(10, false);
        tablamadre.setEnabledAt(11, true);
        tablamadre.setEnabledAt(12, false);
        tablamadre.setEnabledAt(13, false);
        tablamadre.setEnabledAt(14, false);
        tablamadre.setEnabledAt(15, false);
        
        tablamadre.setTitleAt(0, "");
        tablamadre.setTitleAt(1, "");
        tablamadre.setTitleAt(2, "");
        tablamadre.setTitleAt(3, "");
        tablamadre.setTitleAt(4, "");
        tablamadre.setTitleAt(5, "");
        tablamadre.setTitleAt(6, "");
        tablamadre.setTitleAt(7, "");
        tablamadre.setTitleAt(8, "");
        tablamadre.setTitleAt(9, "");
        tablamadre.setTitleAt(10, "");
        tablamadre.setTitleAt(seleccion, "<html>ADMINISTRACIÓN DE CLIENTES</html>");
        tablamadre.setTitleAt(12, "");
        tablamadre.setTitleAt(13, "");
        tablamadre.setTitleAt(14, "");
        tablamadre.setTitleAt(15, "");
        
        }else if(seleccion==12){
            
            tablamadre.setEnabledAt(0, false);
        tablamadre.setEnabledAt(1, false);
        tablamadre.setEnabledAt(2, false);
        tablamadre.setEnabledAt(3, false);
        tablamadre.setEnabledAt(4, false);
        tablamadre.setEnabledAt(5, false);
        tablamadre.setEnabledAt(6, false);
        tablamadre.setEnabledAt(7, false);
        tablamadre.setEnabledAt(8, false);
        tablamadre.setEnabledAt(9, false);
        tablamadre.setEnabledAt(10, false);
        tablamadre.setEnabledAt(11, false);
        tablamadre.setEnabledAt(12, true);
        tablamadre.setEnabledAt(13, false);
        tablamadre.setEnabledAt(14, false);
        tablamadre.setEnabledAt(15, false);
        
        tablamadre.setTitleAt(0, "");
        tablamadre.setTitleAt(1, "");
        tablamadre.setTitleAt(2, "");
        tablamadre.setTitleAt(3, "");
        tablamadre.setTitleAt(4, "");
        tablamadre.setTitleAt(5, "");
        tablamadre.setTitleAt(6, "");
        tablamadre.setTitleAt(7, "");
        tablamadre.setTitleAt(8, "");
        tablamadre.setTitleAt(9, "");
        tablamadre.setTitleAt(10, "");
        tablamadre.setTitleAt(11, "");
        tablamadre.setTitleAt(seleccion, "<html>FACTURACIÓN Y FORMA DE PAGO</html>");
        tablamadre.setTitleAt(13, "");
        tablamadre.setTitleAt(14, "");
        tablamadre.setTitleAt(15, "");
        
        }else if(seleccion==15){
            
            tablamadre.setEnabledAt(0, false);
        tablamadre.setEnabledAt(1, false);
        tablamadre.setEnabledAt(2, false);
        tablamadre.setEnabledAt(3, false);
        tablamadre.setEnabledAt(4, false);
        tablamadre.setEnabledAt(5, false);
        tablamadre.setEnabledAt(6, false);
        tablamadre.setEnabledAt(7, false);
        tablamadre.setEnabledAt(8, false);
        tablamadre.setEnabledAt(9, false);
        tablamadre.setEnabledAt(10, false);
        tablamadre.setEnabledAt(11, false);
        tablamadre.setEnabledAt(12, false);
        tablamadre.setEnabledAt(13, false);
        tablamadre.setEnabledAt(14, false);
        tablamadre.setEnabledAt(15, true);
        
        tablamadre.setTitleAt(0, "");
        tablamadre.setTitleAt(1, "");
        tablamadre.setTitleAt(2, "");
        tablamadre.setTitleAt(3, "");
        tablamadre.setTitleAt(4, "");
        tablamadre.setTitleAt(5, "");
        tablamadre.setTitleAt(6, "");
        tablamadre.setTitleAt(7, "");
        tablamadre.setTitleAt(8, "");
        tablamadre.setTitleAt(9, "");
        tablamadre.setTitleAt(10, "");
        tablamadre.setTitleAt(11, "");
        tablamadre.setTitleAt(12, "");
        tablamadre.setTitleAt(13, "");
        tablamadre.setTitleAt(14, "");
        tablamadre.setTitleAt(seleccion, "<html>DATOS DE EMPRESA</html>");
        }*/
    }
}
