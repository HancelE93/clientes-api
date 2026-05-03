package com.krakedev.clientes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.krakedev.clientes.entidades.Cliente;
import com.krakedev.clientes.servicios.ServicioCliente;

public class ServicioClienteTest {

	 private ServicioCliente servicio;

	    @BeforeEach
	    void setUp() {
	        servicio = new ServicioCliente();
	    }

	    @Test
	    void testCrearClienteExitoso() {
	        Cliente cliente = new Cliente("0101", "Juan", "Perez", "j@gmail.com");

	        Cliente resultado = servicio.crear(cliente);

	        assertNotNull(resultado);
	        assertEquals("0101", resultado.getCedula());
	    }

	    @Test
	    void testCrearClienteDuplicado() {
	        Cliente cliente1 = new Cliente("0101", "Juan", "Perez", "j1@gmail.com");
	        Cliente cliente2 = new Cliente("0101", "Pedro", "Lopez", "jp@gmail.com");

	        servicio.crear(cliente1);
	        Cliente resultado = servicio.crear(cliente2);

	        assertNull(resultado);
	    }

	    @Test
	    void testBuscarPorCedulaExistente() {
	        Cliente cliente = new Cliente("0102", "Ana", "Diaz", "ana@gmail.com");
	        servicio.crear(cliente);

	        Cliente resultado = servicio.buscarPorCedula("0102");

	        assertNotNull(resultado);
	        assertEquals("Ana", resultado.getNombre());
	    }

	    @Test
	    void testBuscarPorCedulaNoExiste() {
	        Cliente resultado = servicio.buscarPorCedula("9999");

	        assertNull(resultado);
	    }

	    @Test
	    void testActualizarCliente() {
	        Cliente cliente = new Cliente("0103", "Luis", "Ramirez", "l@gmail.com");
	        servicio.crear(cliente);

	        Cliente actualizado = new Cliente("0103", "Luisito", "Ramirez", "l2@gmail.com");

	        Cliente resultado = servicio.actualizar("0103", actualizado);

	        assertNotNull(resultado);
	        assertEquals("Luisito", resultado.getNombre());
	        assertEquals("l2@gmail.com", resultado.getEmail()); // 👈 agregado
	    }

	    @Test
	    void testEliminarCliente() {
	        Cliente cliente = new Cliente("0104", "Maria", "Lopez", "maria@gmail.com");
	        servicio.crear(cliente);

	        boolean eliminado = servicio.eliminar("0104");

	        assertTrue(eliminado);
	        assertNull(servicio.buscarPorCedula("0104"));
	    }

	    @Test
	    void testListarClientes() {
	        servicio.crear(new Cliente("1", "A", "B", "a@gmail.com"));
	        servicio.crear(new Cliente("2", "C", "D", "b@gmail.com"));

	        List<Cliente> lista = servicio.listar();

	        assertNotNull(lista);
	        assertEquals(2, lista.size());
	    }
	}