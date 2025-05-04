package uniandes.dpoo.hamburguesas.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.excepciones.IngredienteRepetidoException;
import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PedidoTest {

    private Pedido pedido;

    @BeforeEach
    public void setUp() {
        pedido = new Pedido("Juan Perez", "Calle Falsa 123");
    }
    
    @Test
    public void testCalcularPrecioTotalPedido() throws IngredienteRepetidoException {
    	
    	ArrayList<ProductoMenu> itemsCombo = new ArrayList<>();
        itemsCombo.add(new ProductoMenu("Papas", 5000));
        itemsCombo.add(new ProductoMenu("Gaseosa", 3000));
        Combo combo = new Combo("Combo 1", 0.1, itemsCombo);
        
    	
        ProductoMenu productoMenu = new ProductoMenu("Hamburguesa", 15000);
        ProductoAjustado productoAjustado = new ProductoAjustado(productoMenu);
        Ingrediente agregado = new Ingrediente("Queso extra", 2000);
        Ingrediente eliminado = new Ingrediente("Tomate", 2000);
        productoAjustado.addAgregados(agregado);
        productoAjustado.addEliminados(eliminado);

        pedido.agregarProducto(productoMenu);
        pedido.agregarProducto(combo);
        pedido.agregarProducto(productoAjustado);
        

        assertEquals(pedido.getPrecioTotalPedido(),46648);   
    }

    @Test
    public void testAgregarProductoAjustadoConIngredientes() throws IngredienteRepetidoException {
        ProductoMenu productoBase = new ProductoMenu("Hamburguesa", 15000);
        ProductoAjustado productoAjustado = new ProductoAjustado(productoBase);

        Ingrediente agregado = new Ingrediente("Queso extra", 2000);
        Ingrediente eliminado = new Ingrediente("Tomate", 2000);

        productoAjustado.addAgregados(agregado);
        productoAjustado.addEliminados(eliminado);

        pedido.agregarProducto(productoAjustado);

        String factura = pedido.generarTextoFactura();
        assertTrue(factura.contains("Hamburguesa"));
        assertTrue(factura.contains("+Queso extra"));
        assertTrue(factura.contains("-Tomate"));
        assertTrue(factura.contains("17000")); 
    }
    
    @Test
    public void testAgregarProductoMenu() {
        ProductoMenu productoBase = new ProductoMenu("Hamburguesa", 15000);

        pedido.agregarProducto(productoBase);

        String factura = pedido.generarTextoFactura();
        assertTrue(factura.contains("Hamburguesa"));
        assertTrue(factura.contains("15000")); 
    }
    @Test
    public void testAgregarCombo() {

        ArrayList<ProductoMenu> itemsCombo = new ArrayList<>();
        itemsCombo.add(new ProductoMenu("Papas", 5000));
        itemsCombo.add(new ProductoMenu("Gaseosa", 3000));
        Combo combo = new Combo("Combo 1", 0.1, itemsCombo);
        pedido.agregarProducto(combo);
        

        String factura = pedido.generarTextoFactura();
        assertTrue(factura.contains("Combo 1"));
        assertTrue(factura.contains("7200"));
        assertTrue(factura.contains("0.1"));
    }
    
    

    @Test
    public void testFacturaCompleta() throws IngredienteRepetidoException {
    	
    	ArrayList<ProductoMenu> itemsCombo = new ArrayList<>();
        itemsCombo.add(new ProductoMenu("Papas", 5000));
        itemsCombo.add(new ProductoMenu("Gaseosa", 3000));
        Combo combo = new Combo("Combo 1", 0.1, itemsCombo);
        
    	
        ProductoMenu productoMenu = new ProductoMenu("Hamburguesa", 15000);
        ProductoAjustado productoAjustado = new ProductoAjustado(productoMenu);
        Ingrediente agregado = new Ingrediente("Queso extra", 2000);
        Ingrediente eliminado = new Ingrediente("Tomate", 2000);
        productoAjustado.addAgregados(agregado);
        productoAjustado.addEliminados(eliminado);

        pedido.agregarProducto(productoMenu);
        pedido.agregarProducto(combo);
        pedido.agregarProducto(productoAjustado);

        String factura = pedido.generarTextoFactura();

        assertTrue(factura.contains("Hamburguesa"));
        assertTrue(factura.contains("15000")); 
        
        assertTrue(factura.contains("Hamburguesa"));
        assertTrue(factura.contains("+Queso extra"));
        assertTrue(factura.contains("-Tomate"));
        assertTrue(factura.contains("17000")); 
        
        assertTrue(factura.contains("Combo 1"));
        assertTrue(factura.contains("7200"));
        assertTrue(factura.contains("0.1"));
        
        assertTrue(factura.contains("39200"));
        
        assertTrue(factura.contains("7448"));
        assertTrue(factura.contains("46648"));
        
     
    }
}