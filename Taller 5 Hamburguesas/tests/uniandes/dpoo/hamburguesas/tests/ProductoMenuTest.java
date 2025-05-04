package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest {
	
	//SetUp
	
	ProductoMenu chweezeBurger = new ProductoMenu("Hamburgesa de queso", 15000);
	ProductoMenu fwenchFries = new ProductoMenu("Papitas a la francesa", 5000);
	ProductoMenu chikinNugget = new ProductoMenu("5 Nuggets de pollo", 15000);
	ProductoMenu iceCream = new ProductoMenu("Helado", 3000);
	
	
    
    
	@Test
    void testGetNombre( )
    {
		 assertEquals( "Hamburgesa de queso", chweezeBurger.getNombre( ), "El nombre del producto no es el esperado." );
		 assertEquals( "Papitas a la francesa", fwenchFries.getNombre( ), "El nombre del producto no es el esperado." );
		 assertEquals( "5 Nuggets de pollo", chikinNugget.getNombre( ), "El nombre del producto no es el esperado." );
		 assertEquals( "Helado", iceCream.getNombre( ), "El nombre del producto no es el esperado." );
    }
	
	 @Test
    void testGetPrecio( )
    {
		 assertEquals( 15000, chweezeBurger.getPrecio( ), "El precio del ingrediente no es el esperado." );
		 assertEquals( 5000, fwenchFries.getPrecio( ), "El precio del ingrediente no es el esperado." );
		 assertEquals( 15000, chikinNugget.getPrecio( ), "El precio del ingrediente no es el esperado." );
		 assertEquals( 3000, iceCream.getPrecio( ), "El precio del ingrediente no es el esperado." );
    }
	 @Test
	 void testGenerarTextoFactura( )
	    {
		 	StringBuffer sb = new StringBuffer( );
			sb.append( "Hamburgesa de queso" + "\n" );
		    sb.append( "            " + 15000 + "\n" );
			String factura = sb.toString();
			
			
			 assertEquals( factura, chweezeBurger.generarTextoFactura( ), "La factura no es la esperada." );
			 
			sb = new StringBuffer( );
			sb.append( "Papitas a la francesa" + "\n" );
		    sb.append( "            " + 5000 + "\n" );
			factura = sb.toString();
			 
			 assertEquals( factura, fwenchFries.generarTextoFactura( ), "La factura no es la esperada." );
			 
			 sb = new StringBuffer( );
				sb.append( "5 Nuggets de pollo" + "\n" );
			    sb.append( "            " + 15000 + "\n" );
				factura = sb.toString();
			 
			 assertEquals( factura, chikinNugget.generarTextoFactura( ), "La factura no es la esperada." );
			 
			 sb = new StringBuffer( );
				sb.append( "Helado" + "\n" );
			    sb.append( "            " + 3000 + "\n" );
				factura = sb.toString();
				 
			 assertEquals( factura, iceCream.generarTextoFactura( ), "La factura no es la esperada." );
			 
			 
	    }
		 
	
	

}
