package uniandes.dpoo.hamburguesas.tests;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.excepciones.HamburguesaException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;

public class RestauranteTest {
	
	Restaurante restaurante;
	
	@BeforeEach
    public void setUp() {
        restaurante = new Restaurante();
    }
	
	@Test
    void testIniciarPedido( ) throws YaHayUnPedidoEnCursoException
    {
		restaurante.iniciarPedido("Juan Perez", "Calle Falsa 123");
		assertThrows(YaHayUnPedidoEnCursoException.class, () -> {restaurante.iniciarPedido("Cliente2", "Direccion2");});
	
    }
	
	@Test
    void testCerrarYGuardarPedido( ) throws NoHayPedidoEnCursoException, IOException, YaHayUnPedidoEnCursoException
    {
		
		restaurante.iniciarPedido("Juan Perez", "Calle Falsa 123");
		restaurante.cerrarYGuardarPedido();
		assertEquals(restaurante.getPedidoEnCurso(),null);
		assertThrows(NoHayPedidoEnCursoException.class, () -> {restaurante.cerrarYGuardarPedido();});
	
    } 
	
	@Test
    void testCargarInformacionRestaurante( ) throws IOException, NumberFormatException, HamburguesaException
    {
		restaurante.cargarInformacionRestaurante(new File("./data/ingredientesTest.txt"),new File("./data/menuTest.txt"),new File("./data/combosTest.txt"));
		
		ArrayList<Ingrediente> ingredientes = restaurante.getIngredientes();
		ArrayList<ProductoMenu> menuBase = restaurante.getMenuBase();
		ArrayList<Combo> menuCombos = restaurante.getMenuCombos();
		
		assertTrue(compararIngrediente(ingredientes.get(0),"lechuga",1000));
		assertTrue(compararIngrediente(ingredientes.get(1),"tomate",1000));
		assertTrue(compararIngrediente(ingredientes.get(2),"cebolla",1000));
		assertTrue(compararIngrediente(ingredientes.get(3),"queso mozzarella",2500));
		assertTrue(compararIngrediente(ingredientes.get(4),"huevo",2500));
		assertTrue(compararIngrediente(ingredientes.get(5),"queso americano",2500));
		assertTrue(compararIngrediente(ingredientes.get(6),"tocineta express",2500));
		assertTrue(compararIngrediente(ingredientes.get(7),"papa callejera",2000));
		assertTrue(compararIngrediente(ingredientes.get(8),"pepinillos",2500));
		assertTrue(compararIngrediente(ingredientes.get(9),"cebolla grille",2500));
		assertTrue(compararIngrediente(ingredientes.get(10),"suero costeño",3000));
		assertTrue(compararIngrediente(ingredientes.get(11),"frijol refrito",4500));
		assertTrue(compararIngrediente(ingredientes.get(12),"queso fundido",4500));
		assertTrue(compararIngrediente(ingredientes.get(13),"tocineta picada",6000));
		assertTrue(compararIngrediente(ingredientes.get(14),"piña",2500));
		

		assertTrue(compararProductoMenu(menuBase.get(0), "corral", 14000));
		assertTrue(compararProductoMenu(menuBase.get(1), "corral queso", 16000));
		assertTrue(compararProductoMenu(menuBase.get(2), "corral pollo", 15000));
		assertTrue(compararProductoMenu(menuBase.get(3), "corralita", 13000));
		assertTrue(compararProductoMenu(menuBase.get(4), "todoterreno", 25000));
		assertTrue(compararProductoMenu(menuBase.get(5), "1/2 libra", 25000));
		assertTrue(compararProductoMenu(menuBase.get(6), "especial", 24000));
		assertTrue(compararProductoMenu(menuBase.get(7), "casera", 23000));
		assertTrue(compararProductoMenu(menuBase.get(8), "mexicana", 22000));
		assertTrue(compararProductoMenu(menuBase.get(9), "criolla", 22000));
		assertTrue(compararProductoMenu(menuBase.get(10), "costeña", 20000));
		assertTrue(compararProductoMenu(menuBase.get(11), "hawaiana", 20000));
		assertTrue(compararProductoMenu(menuBase.get(12), "wrap de pollo", 15000));
		assertTrue(compararProductoMenu(menuBase.get(13), "wrap de lomo", 22000));
		assertTrue(compararProductoMenu(menuBase.get(14), "ensalada mexicana", 20900));
		assertTrue(compararProductoMenu(menuBase.get(15), "papas medianas", 5500));
		assertTrue(compararProductoMenu(menuBase.get(16), "papas grandes", 6900));
		assertTrue(compararProductoMenu(menuBase.get(17), "papas en casco medianas", 5500));
		assertTrue(compararProductoMenu(menuBase.get(18), "papas en casco grandes", 6900));
		assertTrue(compararProductoMenu(menuBase.get(19), "agua cristal sin gas", 5000));
		assertTrue(compararProductoMenu(menuBase.get(20), "agua cristal con gas", 5000));
		assertTrue(compararProductoMenu(menuBase.get(21), "gaseosa", 5000));
		
		ArrayList<ProductoMenu> productos = new ArrayList<ProductoMenu>();
		productos.add(menuBase.get(0));
		productos.add(menuBase.get(15));
		productos.add(menuBase.get(21));
		
		
		assertTrue(compararCombo(menuCombos.get(0),"combo corral",0.1, productos));
		productos = new ArrayList<ProductoMenu>();
		productos.add(menuBase.get(1));
		productos.add(menuBase.get(15));
		productos.add(menuBase.get(21));
		assertTrue(compararCombo(menuCombos.get(1),"combo corral queso",0.1, productos));
		productos = new ArrayList<ProductoMenu>();
		productos.add(menuBase.get(4));
		productos.add(menuBase.get(16));
		productos.add(menuBase.get(21));
		assertTrue(compararCombo(menuCombos.get(2),"combo todoterreno",0.07, productos));
		productos = new ArrayList<ProductoMenu>();
		productos.add(menuBase.get(6));
		productos.add(menuBase.get(15));
		productos.add(menuBase.get(21));
		assertTrue(compararCombo(menuCombos.get(3),"combo especial",0.095, productos));
		
		
		
		
    } 
	
	
	
	
	private boolean compararProductoMenu(ProductoMenu productoMenu, String nombre, int precio) {
		
		if(productoMenu.getNombre().equals(nombre) && productoMenu.getPrecio() == precio) {
			return true;
		}
		
		return false;
	}

	private boolean compararIngrediente(Ingrediente ingrediente, String nombre, int precio) {
		
		if(ingrediente.getNombre().equals(nombre) && ingrediente.getCostoAdicional() == precio) {
			return true;
		}
		return false;
	}
	
	private boolean compararCombo(Combo combo, String nombre, double d, ArrayList<ProductoMenu> productos) {
	
		for(int i = 0; i < productos.size(); i++) {
			if(!compararProductoMenu(combo.getItemsCombo().get(i),productos.get(i).getNombre(),productos.get(i).getPrecio())) {
				return false;
			}
		}
		if (combo.getDescuento() != d) {
			return false;
		}
		if (!combo.getNombre().equals(nombre)) {
			return false;
		}
		
		return true;
	}
	
}
