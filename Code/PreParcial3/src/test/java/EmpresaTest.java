import static org.junit.jupiter.api.Assertions.*;

import co.edu.uniquindio.Enums.TipoCarro;
import co.edu.uniquindio.Enums.TipoServicio;
import co.edu.uniquindio.Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmpresaTest {

    private Empresa empresa;

    @BeforeEach
    public void setUp() {
        empresa = new Empresa("Peajes UQ", "123.456.789");

        // Simulación de datos necesarios
        Conductor conductor = new Conductor("Carlos", "Molina", "123", LocalDate.of(2000, 1, 1));
        empresa.getgConductor().crear(conductor);

        Carro carro1 = new Carro("ABC123", 5, TipoCarro.ELECTRICO, TipoServicio.PARTICULAR);
        empresa.getgCarro().crear(carro1);

        Peaje peaje = new Peaje("Peaje UQ", "Quindio");
        empresa.getgPeaje().crear(peaje);
    }

    @Test
    public void testAsignarVehiculosConductor() {
        boolean asignado = empresa.asignarVehiculosConductor("123", "ABC123");
        assertTrue(asignado);
    }

    @Test
    public void testRegistrarVehiculoPorPeaje() {
        boolean registrado = empresa.registrarVehiculoPorPeaje("ABC123", "Peaje UQ");
        assertTrue(registrado);
    }

    @Test
    public void testMostrarVehiculosPorPeaje() {
        empresa.registrarVehiculoPorPeaje("ABC123", "Peaje UQ");
        List<Vehiculo> lista = empresa.mostrarVehiculosPorPeaje("Peaje UQ");
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
    }

    @Test
    public void testConsultarDineroPagadoPeajes() {
        // Asignar vehículo al conductor
        boolean asignado = empresa.asignarVehiculosConductor("123", "ABC123");
        assertTrue(asignado);
        // Registrar vehículo por el peaje
        boolean registrado = empresa.registrarVehiculoPorPeaje("ABC123", "Peaje UQ");
        assertTrue(registrado);
        // Calcular y actualizar el peaje
        Vehiculo vehiculo = empresa.getgCarro().buscar("ABC123");
        Peaje peaje = empresa.getgPeaje().buscar("Peaje1");
        empresa.calcularYActualizarDineroTotalPeaje("ABC123", "Peaje UQ");
        // Ejecutar el metodo que devuelve el string
        String resultado = empresa.consultarDineroPagadoPeajes("123");
        // Extraer el número del valor total
        Pattern pattern = Pattern.compile("VALOR TOTAL\\s*:\\s*(\\d+(\\.\\d+)?)");
        Matcher matcher = pattern.matcher(resultado);
        assertTrue(matcher.find(), "No se encontró el total en el string retornado");
        double valorTotal = Double.parseDouble(matcher.group(1));
        assertTrue(valorTotal > 0, "El valor total pagado debe ser mayor que 0");
    }


    @Test
    public void testRetornarVehiculosTipo() {
        empresa.asignarVehiculosConductor("123", "ABC123");
        List<Vehiculo> carros = empresa.retornarVehiculosTipo("123", "Carro");
        assertEquals(1, carros.size());
    }

    @Test
    public void testCalcularYActualizarDineroTotalPeaje() {
        Carro carro2 = new Carro("ABC123", 0, TipoCarro.ELECTRICO, TipoServicio.PARTICULAR);
        Peaje peaje = empresa.getgPeaje().buscar("Peaje UQ");
        empresa.calcularYActualizarDineroTotalPeaje(carro2.getPlaca(), peaje.getNombre());
        assertTrue(peaje.getValorTotalPeajes() > 0);
    }

    @Test
    public void testBuscarRecaudadorPorNombreYApellido() {
        Recaudador recaudador = new Recaudador("Ana", "Pérez", "999", 0);
        empresa.getgRecaudador().crear(recaudador);
        Persona resultado = empresa.buscarRecaudadorPorNombreYApellido("Ana", "Pérez");
        assertNotNull(resultado);
    }

    @Test
    public void testConductoresConCamionesCarga10T() {
        Conductor conductor = new Conductor("Pedro", "Lopez", "456", LocalDate.of(1995, 5, 5));
        Camion camion = new Camion("XYZ789", 5, 10, 11);
        empresa.getgConductor().crear(conductor);
        empresa.getgCamion().crear(camion);
        empresa.asignarVehiculosConductor("456", "XYZ789");
        List<Conductor> resultado = empresa.conductoresConCamionesCarga10T();
        assertFalse(resultado.isEmpty());
    }
}

