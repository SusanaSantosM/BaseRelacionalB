import javax.swing.*;
import java.sql.*;

public class GestionDatos {

    /**
     * Metodo para listar los productos de la base de datos
     */
    public void listarProductos(){
        // Consulta para listar los productos.
        String consulta = "SELECT * FROM produtos";

        try{
            // Conectamos a la BD
            Connection conectar = ConectarBD.conexion();
            Statement st = conectar.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(consulta);

            while(rs.next()){
                System.out.println("Código: "+rs.getString("codigo"));
                System.out.println("Descricion: "+rs.getString("descricion"));
                System.out.println("Prezo: "+rs.getInt("prezo"));
                System.out.println("Data: "+rs.getString("datac"));
            }

        } catch(SQLException e){
            JOptionPane.showMessageDialog(null,"No se pudo leer los productos de la base de datos.");
            e.printStackTrace();
        }
    }

    /**
     * Metodo que actualiza el precio de un producto por su código
     * @param codigo String
     * @param novoPrezo de tipo int
     */
    public void actualizarProductos(String codigo, int novoPrezo){
        // Consulta para actualiar produtos
        String consulta = "select * from produtos where codigo = '" + codigo + "'";

        try {
            // Conectamos a la BD
            Connection conectar = ConectarBD.conexion();
            Statement st = conectar.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(consulta);

            // Bucle para encontrar la fila y actualizar el producto
            if(rs.next()){
                rs.updateInt("prezo", novoPrezo);
                rs.updateRow();
                JOptionPane.showMessageDialog(null,"Producto actualizado.");
            } else{
                JOptionPane.showMessageDialog(null,"No existe el código del producto");
            }

        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"No se pudo realizar la consulta");
            e.printStackTrace();
        }
    }

    /**
     * Metodo para insetar porductos a la base de datos
     * @param codigo de tipo String
     * @param descricion de tipo String
     * @param prezo de tipo int
     * @param datac de tipo String
     */
    public void insertarProducto(String codigo, String descricion, int prezo, String datac){
        // Consulta para insertar productos
        String consulta = "SELECT * FROM produtos";

        try {
            // Conectamos a la BD
            Connection conectar = ConectarBD.conexion();
            Statement st = conectar.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(consulta);

            // Nos movemos a la fila que vamos a insertar el producto
            rs.moveToInsertRow();

            // Asignamos los valores
            rs.updateString("codigo",codigo);
            rs.updateString("descricion",descricion);
            rs.updateInt("prezo",prezo);
            rs.updateDate("datac",Date.valueOf(datac)); // Convertimos la data en tipp Date para la base de datos

            // Insertamos los valores a la fila
            rs.insertRow();

            JOptionPane.showMessageDialog(null,"Producto insertado correctamente");

        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"No se pudo realizar la consulta");
            e.printStackTrace();
        }

    }

    /**
     * Metodo para eliminar un producto buscado por su código
     * @param codigo de tipo String
     */
    public void borrarProducto(String codigo){
        // Consulta para borrar el producto
        String consulta = "select * from produtos where codigo = '" + codigo + "'";

        try{
            // Conectamos a la BD
            Connection conectar = ConectarBD.conexion();
            Statement st = conectar.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(consulta);

            //Ejecutamos la orden
            if(rs.next()){
                rs.deleteRow();
                JOptionPane.showMessageDialog(null,"Producto eliminado");
            }

        }catch(SQLException e){
            System.out.println("No se pudo eliminar produto. ");
            e.printStackTrace();
        }
    }
}
