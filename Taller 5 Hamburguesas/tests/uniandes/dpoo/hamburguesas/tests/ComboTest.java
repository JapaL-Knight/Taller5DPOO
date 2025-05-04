package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest {

	ArrayList<ProductoMenu> items = new ArrayList<ProductoMenu>();
	
	ProductoMenu chweezeBurger = new ProductoMenu("Hamburgesa de queso", 15000);
	ProductoMenu fwenchFries = new ProductoMenu("Papitas a la francesa", 5000);
	ProductoMenu chikinNugget = new ProductoMenu("5 Nuggets de pollo", 15000);
	ProductoMenu iceCream = new ProductoMenu("Helado", 3000);
	Combo combo = new Combo( "Crew combo", 0.15, items );
	
	private void setUp() {
		items.add(chikinNugget);
		items.add(fwenchFries);
		items.add(chikinNugget);
		items.add(iceCream);
		combo = new Combo( "Crew combo", 0.15, items );
		
	}
	
	@Test
	void testGetPrecio( ) {
		setUp();
		assertEquals( (int) (15000+5000+15000+3000)*(1-0.15), combo.getPrecio(), "El precio no es el esperado." );
	}
	
	@Test
	void testGenerarTextoFactura( ) {
		setUp();
		StringBuffer sb = new StringBuffer( );
        sb.append( "Combo " + "Crew combo" + "\n" );
        sb.append( " Descuento: " +  0.15+ "\n" );
        sb.append( "            " + ((int)((15000+5000+15000+3000)*(1-0.15))) + "\n" );
        
        assertEquals( sb.toString(), combo.generarTextoFactura(), "La factura no es la esperada." );
        
	}
	
	
	
	
}
