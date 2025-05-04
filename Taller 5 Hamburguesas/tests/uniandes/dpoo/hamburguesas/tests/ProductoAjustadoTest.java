package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.excepciones.IngredienteRepetidoException;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoAjustadoTest {


	ProductoMenu chikinNugget = new ProductoMenu("5 Nuggets de pollo", 15000);
	ProductoAjustado producto = new ProductoAjustado(chikinNugget);
	Ingrediente salsaDeTomate = new Ingrediente( "Salsa de tomate", 500);
	Ingrediente mayonesa = new Ingrediente( "Mayonesa", 500);
	
	
	
	
	
	
	@Test
	void testGenerarTextoFactura( ) throws IngredienteRepetidoException {
		
		producto.addAgregados(mayonesa);
		producto.addEliminados(salsaDeTomate);
		
        StringBuffer sb = new StringBuffer( );
        sb.append(chikinNugget.getNombre()  + "\n" );
        {
            sb.append( "    +" + mayonesa.getNombre( )  + "\n" );
            sb.append( "                " + mayonesa.getCostoAdicional( )  + "\n" );
        }
        {
            sb.append( "    -" + salsaDeTomate.getNombre( ) + "\n"  );
        }

        sb.append( "            " + producto.getPrecio( ) + "\n" );

        assertEquals( sb.toString(), producto.generarTextoFactura(), "La factura no es la esperada." );
	
	}
	
	
}
