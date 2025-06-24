package com.example.metrix.model;


import com.example.metrix.repository.CompraRepository;
import com.example.metrix.repository.DatosHistoricosRepository;
import com.example.metrix.repository.FuncionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class FuncionScheduler {

    @Autowired
    private FuncionRepository funcionRepository;

    @Autowired
    private DatosHistoricosRepository datosHistoricosRepository;

    @Autowired
    private CompraRepository compraRepository;

    // Actualizar el estado de las funciones
    @Scheduled(cron = "0 0 10,13,16,19 * * *") // Se ejecuta a las 10:00, 13:00, 16:00 y 19:00
    public void actualizarEstadoFunciones() {
        LocalDate hoy = LocalDate.now();
        List<Funcion> funcionesProgramadas = funcionRepository.findByEstado("programada");

        for (Funcion funcion : funcionesProgramadas) {
            if (funcion.getFecha().isBefore(hoy)) {
                funcion.setEstado("finalizada");
                funcionRepository.save(funcion);
            }
        }
    }

    // Eliminar funciones que han pasado hace más de dos semanas
    @Scheduled(cron = "0 0 1 * * *") // Se ejecuta todos los días a la 1 AM
    public void eliminarFuncionesAntiguas() {
        LocalDate fechaLimite = LocalDate.now().minusWeeks(2);

        // Obtener funciones finalizadas que son más antiguas que dos semanas
        List<Funcion> funcionesAntiguas = funcionRepository.findByFechaBeforeAndEstado(fechaLimite, "finalizada");

        if (funcionesAntiguas.isEmpty()) {
            return; // No hay funciones antiguas para procesar
        }

        // Variables para acumular datos
        double totalDeVentas = 0.0;
        int totalBoletosOfertados = 0;
        int totalAsientosOcupados = 0;
        int numeroDeFuncionesImpartidas = funcionesAntiguas.size();

        // Supongamos que cada función tiene un número fijo de asientos ofertados
        int asientosPorFuncion = 100; // Ajusta este valor según tu configuración o agrega un campo en la entidad

        for (Funcion funcion : funcionesAntiguas) {
            totalDeVentas += funcion.getDineroRecaudado();
            totalBoletosOfertados += asientosPorFuncion;
            totalAsientosOcupados += funcion.getBoletosVendidos().size();

            // Eliminar compras asociadas a la función
            List<Compra> comprasAsociadas = compraRepository.findByFuncion(funcion);
            compraRepository.deleteAll(comprasAsociadas);
        }

        // Obtener el registro existente de DatosHistoricos con id 1
        Optional<DatosHistoricos> datosHistoricosOptional = datosHistoricosRepository.findById(1);

        DatosHistoricos datosHistoricos;

        if (datosHistoricosOptional.isPresent()) {
            // Si el registro existe, lo actualizamos
            datosHistoricos = datosHistoricosOptional.get();
            // Mantenemos la fechaInicio existente
        } else {
            // Si no existe, creamos uno nuevo y establecemos la fechaInicio
            datosHistoricos = new DatosHistoricos();
            datosHistoricos.setFechaInicio(LocalDate.now()); // O establece la fecha que corresponda
        }

        // Actualizamos los campos sumando los nuevos totales
        datosHistoricos.setTotalDeVentas(datosHistoricos.getTotalDeVentas() + totalDeVentas);
        datosHistoricos.setTotalBoletosOfertados(datosHistoricos.getTotalBoletosOfertados() + totalBoletosOfertados);
        datosHistoricos.setTotalAsientosOcupados(datosHistoricos.getTotalAsientosOcupados() + totalAsientosOcupados);
        datosHistoricos.setNumeroDeFuncionesImpartidas(datosHistoricos.getNumeroDeFuncionesImpartidas() + numeroDeFuncionesImpartidas);

        // Guardar los datos históricos actualizados
        datosHistoricosRepository.save(datosHistoricos);

        // Eliminar las funciones antiguas
        funcionRepository.deleteAll(funcionesAntiguas);
    }
}
