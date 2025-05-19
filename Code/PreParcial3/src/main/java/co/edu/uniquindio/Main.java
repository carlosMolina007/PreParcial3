package co.edu.uniquindio;

import co.edu.uniquindio.Enums.TipoCarro;
import co.edu.uniquindio.Enums.TipoServicio;
import co.edu.uniquindio.Gestores.*;
import co.edu.uniquindio.Model.*;

import javax.swing.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Empresa empresa = new Empresa("INVIAS UQ", "123.456.789");
        GestorPeaje gPeaje = new GestorPeaje();
        GestorConductor gConductor = new GestorConductor();
        GestorRecaudador gRecaudador = new GestorRecaudador();
        GestorMoto gMoto = new GestorMoto();
        GestorCamion gCamion = new GestorCamion();
        GestorCarro gCarro = new GestorCarro();

        String[] options = {
                "Gestionar peajes",
                "Gestionar conductores",
                "Gestionar recaudadores",
                "Gestionar motos",
                "Gestionar carros",
                "Gestionar camiones",
                "Asignar un vehiculo a un conductor",
                "Listado de vehiculos que han pasado por algún peaje",
                "Mostrar información de un vehiculo",
                "Consultar dinero pagado en peajes por un conductor",
                "Consultar vehiculos de un tipo pertenecientes a un conductor",
                "Calcular, actualizar y registrar un vehiculo en un peaje",
                "Buscar recaudador",
                "Consultar conductores con camiones que soporten más de 10 Toneladas",
                "Salir"
        };
        String mensajeOpciones = "Seleccione alguna de las siguientes opciones\n1. Crear\n2. Actualizar\n3. Buscar\n4. Eliminar\n5. Mostrar Lista";
        while (true) {
            String option = (String) JOptionPane.showInputDialog(
                    null,
                    "Seleccione una opción",
                    "SISTEMA DE PEAJES",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]);
            switch (option) {
                case "Gestionar peajes":
                    int optionPeaje = Integer.parseInt(JOptionPane.showInputDialog(null, mensajeOpciones));
                    switch (optionPeaje) {
                        case 1:
                            String nombrePeaje = JOptionPane.showInputDialog(null, "Ingrese el nombre del peaje");
                            String departamento = JOptionPane.showInputDialog(null, "Ingrese el departamento en el que está ubicado");
                            Peaje newPeaje = new Peaje(nombrePeaje, departamento);
                            boolean verificar = gPeaje.crear(newPeaje);
                            if (verificar) {
                                JOptionPane.showMessageDialog(null, "El peaje se ha creado con exito");
                            } else {
                                JOptionPane.showMessageDialog(null, "Peaje existente, intente con otro nombre");
                            }
                            break;
                        case 2:
                            String nombrePActualizar = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre del peaje");
                            String departamentoActualizar = JOptionPane.showInputDialog(null, "Ingrese el nuevo departamento en el que estará el peaje");
                            Peaje peajeActualizar = new Peaje(nombrePActualizar, departamentoActualizar);
                            boolean verificarActualizar = gPeaje.actualizar(peajeActualizar);
                            if (verificarActualizar) {
                                JOptionPane.showMessageDialog(null, "Se ha actualizado con éxito");
                            } else {
                                JOptionPane.showMessageDialog(null, "Peaje inexistente");
                            }
                            break;
                        case 3:
                            String peajeBuscar = JOptionPane.showInputDialog(null, "Ingrese el nombre del peaje que desea buscar");
                            Peaje peajeBuscado = gPeaje.buscar(peajeBuscar);
                            if (peajeBuscado != null) {
                                JOptionPane.showMessageDialog(null, "Se encontró el peaje:\n" + peajeBuscado);
                            } else {
                                JOptionPane.showMessageDialog(null, "Peaje inexistente");
                            }
                            break;
                        case 4:
                            String peajeEliminar = JOptionPane.showInputDialog(null, "Ingrese el nombre del peaje que desea eliminar");
                            boolean peajeEliminado = gPeaje.eliminar(peajeEliminar);
                            if (peajeEliminado) {
                                JOptionPane.showMessageDialog(null, "Se ha eliminado con exito");
                            } else {
                                JOptionPane.showMessageDialog(null, "Peaje inexistente");
                            }
                            break;
                        case 5:
                            if (gPeaje.listar().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Lista vacía, por favor registre al menos 1 peaje");
                            } else {
                                JOptionPane.showMessageDialog(null, gPeaje.listar());
                            }
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opcion no valida");
                            break;
                    }
                    break;
                case "Gestionar conductores":
                    int optionConductor = Integer.parseInt(JOptionPane.showInputDialog(null, mensajeOpciones));
                    switch (optionConductor) {
                        case 1:
                            String nombreConductor = JOptionPane.showInputDialog(null, "Ingrese el nombre del conductor");
                            String apellidoConductor = JOptionPane.showInputDialog(null, "Ingrese el apellido del conductor");
                            String idConductor = JOptionPane.showInputDialog(null, "Ingrese su número de documento");
                            int anioNacimiento = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese su año de nacimiento"));
                            int mesNacimiento = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese su mes de nacimiento (del 1 al 12)"));
                            int diaNacimiento = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese su dia de nacimiento"));
                            Conductor newConductor = new Conductor(nombreConductor, apellidoConductor, idConductor, LocalDate.of(anioNacimiento, mesNacimiento, diaNacimiento));
                            boolean verificarConductor = gConductor.crear(newConductor);
                            if (verificarConductor) {
                                JOptionPane.showMessageDialog(null, "Se ha creado con éxito");
                            } else {
                                JOptionPane.showMessageDialog(null, "Conductor existente");
                            }
                            break;
                        case 2:
                            String idConductorActualizar = JOptionPane.showInputDialog(null, "Ingrese el número del documento del conductor que desea actualizar");
                            String nombreCActualizar = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre del conductor");
                            String apellidoConductorActualizar = JOptionPane.showInputDialog(null, "Ingrese el apellido del conductor");
                            int anioNacimientoAct = 2000;
                            int mesNacimientoAct = 1;
                            int diaNacimientoAct = 1;
                            Conductor conductorActualizar = new Conductor(nombreCActualizar, apellidoConductorActualizar, idConductorActualizar, LocalDate.of(anioNacimientoAct, mesNacimientoAct, diaNacimientoAct));
                            boolean conductorVActualizar = gConductor.actualizar(conductorActualizar);
                            if (conductorVActualizar) {
                                JOptionPane.showMessageDialog(null, "Se ha actualizado con éxito" + conductorActualizar);
                            } else {
                                JOptionPane.showMessageDialog(null, "Conductor inexistente");
                            }
                            break;
                        case 3:
                            String idConductorBuscar = JOptionPane.showInputDialog(null, "Ingrese el número de documento del conductor que desea buscar");
                            Conductor conductorBuscar = gConductor.buscar(idConductorBuscar);
                            if (conductorBuscar != null) {
                                JOptionPane.showMessageDialog(null, "Se encontró el siguiente conductor:\n" + conductorBuscar);
                            } else {
                                JOptionPane.showMessageDialog(null, "Conductor inexistente");
                            }
                            break;
                        case 4:
                            String idConductorEliminar = JOptionPane.showInputDialog(null, "Ingrese el número de documento del conductor que desea eliminar");
                            boolean conductorEliminar = gConductor.eliminar(idConductorEliminar);
                            if (conductorEliminar) {
                                JOptionPane.showMessageDialog(null, "Se ha eliminado con exito");
                            } else {
                                JOptionPane.showMessageDialog(null, "Conductor inexistente");
                            }
                            break;
                        case 5:
                            if (gConductor.listar().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Lista vacía, por favor registre al menos 1 conductor");
                            } else {
                                JOptionPane.showMessageDialog(null, gConductor.listar());
                            }
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opcion no valida");
                            break;
                    }
                    break;
                case "Gestionar recaudadores":
                    int optionRecaudador = Integer.parseInt(JOptionPane.showInputDialog(null, mensajeOpciones));

                    switch (optionRecaudador) {
                        case 1:
                            String idRecaudador = JOptionPane.showInputDialog(null, "Ingrese el número de identificación del recaudador");
                            String nombreRecaudador = JOptionPane.showInputDialog(null, "Ingrese el nombre del recaudador");
                            String apellidoRecaudador = JOptionPane.showInputDialog(null, "Ingrese el apellido del recaudador");
                            double dineroRecaudado = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el dinero del recaudado"));
                            Recaudador newRecaudador = new Recaudador(nombreRecaudador, apellidoRecaudador, idRecaudador, dineroRecaudado);
                            boolean validarNewRecaudador = gRecaudador.crear(newRecaudador);
                            if (validarNewRecaudador) {
                                JOptionPane.showMessageDialog(null, "Se ha agregado con exito");
                            } else {
                                JOptionPane.showMessageDialog(null, "Recaudador existente");
                            }
                            break;

                        case 2:
                            String idActRecaudador = JOptionPane.showInputDialog(null, "Ingrese el número de identificación del recaudador que desea actualizar");
                            String nombreActRecaudador = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre del recaudador");
                            String apellidoActRecaudador = JOptionPane.showInputDialog(null, "Ingrese el nuevo apellido del recaudador");
                            double dineroActualizar = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el dinero actual recaudado"));
                            Recaudador recaudadorActualizar = new Recaudador(nombreActRecaudador, apellidoActRecaudador, idActRecaudador, dineroActualizar);
                            boolean verificarActRecaudador = gRecaudador.actualizar(recaudadorActualizar);
                            if (verificarActRecaudador) {
                                JOptionPane.showMessageDialog(null, "Se ha actualizado con exito");
                            } else {
                                JOptionPane.showMessageDialog(null, "Recaudador inexistente");
                            }
                            break;

                        case 3:
                            String idBuscarRecaudador = JOptionPane.showInputDialog(null, "Ingrese el número de identificación del reccaudador que desea buscar");
                            Recaudador recaudadorBuscado = gRecaudador.buscar(idBuscarRecaudador);
                            if (recaudadorBuscado != null) {
                                JOptionPane.showMessageDialog(null, "Se ha encontrado el siguiente recaudador:\n" + recaudadorBuscado);
                            } else {
                                JOptionPane.showMessageDialog(null, "Recaudador inexistente");
                            }
                            break;

                        case 4:
                            String idRecaudadorEliminar = JOptionPane.showInputDialog(null, "Ingrese el número de identificación del recaudador que desea eliminar");
                            boolean recaudadorEliminar = gRecaudador.eliminar(idRecaudadorEliminar);
                            if (recaudadorEliminar) {
                                JOptionPane.showMessageDialog(null, "Se ha eliminado con exito");
                            } else {
                                JOptionPane.showMessageDialog(null, "Recaudador inexistente");
                            }
                            break;

                        case 5:
                            if (gRecaudador.listar().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Lista vacía, por favor registre al menos 1 recaudador");
                            } else {
                                JOptionPane.showMessageDialog(null, gRecaudador.listar());
                            }
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "Opción no válida");
                            break;
                    }
                    break;
                case "Gestionar motos":

                    int optionMoto = Integer.parseInt(JOptionPane.showInputDialog(null, mensajeOpciones));
                    switch (optionMoto) {
                        case 1:
                            String placaMoto = JOptionPane.showInputDialog(null, "Ingrese la placa de la moto");
                            int cantidadPeajesPagosMoto = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de peajes pagados hasta el momento"));
                            int cilindrada = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cilindrada de su motocicleta"));
                            Moto newMoto = new Moto(placaMoto, cantidadPeajesPagosMoto, cilindrada);
                            boolean validarMoto = gMoto.crear(newMoto);
                            if (validarMoto) {
                                JOptionPane.showMessageDialog(null, "Se ha agregado con exito");
                            } else {
                                JOptionPane.showMessageDialog(null, "Moto existente");
                            }
                            break;

                        case 2:
                            String placaMotoExistente = JOptionPane.showInputDialog(null, "Ingrese la placa de la moto que desea actualizar");
                            int cantPeajesPagosAct = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad actual de peajes pagos"));
                            int cilindrajeActual = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cilindraje de su moto actualmente"));
                            Moto motoActualizar = new Moto(placaMotoExistente, cantPeajesPagosAct, cilindrajeActual);
                            boolean validarMotoActualizar = gMoto.actualizar(motoActualizar);
                            if (validarMotoActualizar) {
                                JOptionPane.showMessageDialog(null, "Se ha actualizado con exito");
                            }else {
                                JOptionPane.showMessageDialog(null, "Moto inexistente");
                            }
                            break;

                        case 3:
                            String placaBuscarMoto = JOptionPane.showInputDialog(null, "Ingrese la placa de la moto que desea consultar");
                            Moto motoBuscada = gMoto.buscar(placaBuscarMoto);
                            if (motoBuscada != null) {
                                JOptionPane.showMessageDialog(null, "Se ha encontrado el siguiente resultado:\n" + motoBuscada);
                            }else {
                                JOptionPane.showMessageDialog(null, "Moto inexistente");
                            }
                            break;

                        case 4:
                            String placaEliminarMoto = JOptionPane.showInputDialog(null, "Ingrese la placa de la moto que desea eliminar");
                            boolean verificarEliminarMoto = gMoto.eliminar(placaEliminarMoto);
                            if (verificarEliminarMoto) {
                                JOptionPane.showMessageDialog(null, "Se ha eliminado con exito");
                            }else {
                                JOptionPane.showMessageDialog(null, "Moto inexistente");
                            }
                            break;

                        case 5:
                            if (gMoto.listar().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Lista vacía, por favor registre al menos 1 moto");
                            }else {
                                JOptionPane.showMessageDialog(null, gMoto.listar());
                            }
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "Opción no válida");
                            break;
                    }
                    break;
                case "Gestionar carros":
                    int optionCarro = Integer.parseInt(JOptionPane.showInputDialog(null, mensajeOpciones));
                    switch (optionCarro) {
                        case 1:
                            String placaCarro = JOptionPane.showInputDialog(null, "Ingrese la placa del carro");
                            int cantPeajesPagosCarro = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de peajes pagos"));
                            int opcionTipoCarro = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese alguna de las siguientes opciones: \n1.Carro eléctrico\n2.Carro a combustión"));
                            TipoCarro tipoCarro = empresa.tipoCombustibleCarro(opcionTipoCarro);
                            int opcionTipoServicio = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese alguna de las siguientes opciones: \n1.Servicio particular\n2.Servicio Público" ));
                            TipoServicio tipoServicio = empresa.tipoServicioCarro(opcionTipoServicio);
                            Carro newCarro = new Carro(placaCarro, cantPeajesPagosCarro, tipoCarro, tipoServicio);
                            boolean validarCarro = gCarro.crear(newCarro);
                            if (validarCarro) {
                                JOptionPane.showMessageDialog(null, "Se ha agregado con exito");
                            }else{
                                JOptionPane.showMessageDialog(null, "Carro existente");
                            }
                            break;

                        case 2:
                            String placaCarroActualizar = JOptionPane.showInputDialog(null, "Ingrese la placa del carro que desea actualizar");
                            int cantPeajesCarroActualizar = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantPeajes pagos actuales"));
                            int opcionTipoCarroAct = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese alguna de las siguientes opciones para actualizarla: \n1.Carro eléctrico\n2.Carro a combustión"));
                            TipoCarro tipoCarroAct = empresa.tipoCombustibleCarro(opcionTipoCarroAct);
                            int opcionServicioCarroAct = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese alguna de las siguientes opciones para actualizarla: \n1.Servicio particular\n2.Servicio Público" ));
                            TipoServicio tipoServicioAct = empresa.tipoServicioCarro(opcionServicioCarroAct);
                            Carro carroActualizar = new Carro(placaCarroActualizar, cantPeajesCarroActualizar, tipoCarroAct, tipoServicioAct);
                            boolean validarCarroActualizar = gCarro.actualizar(carroActualizar);
                            if (validarCarroActualizar) {
                                JOptionPane.showMessageDialog(null, "Se ha actualizado con exito");
                            }else{
                                JOptionPane.showMessageDialog(null, "Carro inexistente");
                            }
                            break;

                        case 3:
                            String placaCarroBuscar = JOptionPane.showInputDialog(null, "Ingrese la placa del carro que desea buscar");
                            Carro carroBuscado = gCarro.buscar(placaCarroBuscar);
                            if (carroBuscado != null) {
                                JOptionPane.showMessageDialog(null, "Se ha encontrado el siguiente carro: \n"+carroBuscado);
                            }else{
                                JOptionPane.showMessageDialog(null, "Carro inexistente");
                            }
                            break;

                        case 4:
                            String placaCarroBorrar = JOptionPane.showInputDialog(null, "Ingrese la placa del carro que desea eliminar");
                            boolean verificarCarroBorrar = gCarro.eliminar(placaCarroBorrar);
                            if (verificarCarroBorrar) {
                                JOptionPane.showMessageDialog(null, "Se ha eliminado con exito");
                            }else {
                                JOptionPane.showMessageDialog(null, "Carro inexistente");
                            }
                            break;

                        case 5:
                            if (gCarro.listar().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Lista vacía, agregue al menos 1 carro");
                            }else {
                                JOptionPane.showMessageDialog(null, gCarro.listar());
                            }
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "Opción no válida");
                            break;
                    }
                    break;
                case "Gestionar camiones":
                    int optionCamion = Integer.parseInt(JOptionPane.showInputDialog(null, mensajeOpciones));
                    switch (optionCamion) {
                        case 1:
                            String placaCamion = JOptionPane.showInputDialog(null, "Ingrese la placa del camión");
                            int cantPeajesPagosCamion = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de peajes pagos"));
                            int cantEjes = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de ejes del camión"));
                            int capacidadPeso = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la capacidad peso (en toneladas)"));
                            Camion newCamion = new Camion(placaCamion, cantPeajesPagosCamion, cantEjes, capacidadPeso);
                            boolean validarCamion = gCamion.crear(newCamion);
                            if(validarCamion){
                                JOptionPane.showMessageDialog(null, "Se ha agregado con exito");
                            }else{
                                JOptionPane.showMessageDialog(null, "Camión existente");
                            }
                            break;

                        case 2:
                            String placaCamionAct = JOptionPane.showInputDialog(null, "Ingrese la placa del camión que desea actualizar");
                            int cantPeajesPagosAct = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de peajes pagos actualmente"));
                            int cantEjesAct = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de ejes actualmente"));
                            int capacidadPesoAct = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la capacidad de peso que soporta el camión actualmente (en toneladas)"));
                            Camion camionAct = new Camion(placaCamionAct, cantPeajesPagosAct, cantEjesAct, capacidadPesoAct);
                            boolean validarCamionAct = gCamion.actualizar(camionAct);
                            if(validarCamionAct){
                                JOptionPane.showMessageDialog(null, "Se ha actualizado con exito");
                            }else {
                                JOptionPane.showMessageDialog(null, "Camión inexistente");
                            }
                            break;

                        case 3:
                            // Buscar conductor
                            // 1. Pedir ID
                            // 2. Usar gConductor.buscar() y mostrar resultado
                            break;

                        case 4:
                            // Eliminar conductor
                            // 1. Pedir ID
                            // 2. Usar gConductor.eliminar() y mostrar mensaje
                            break;

                        case 5:
                            // Listar conductores
                            // Mostrar lista de conductores usando gConductor.listar()
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "Opción no válida");
                            break;
                    }
                    break;

            }
        }
    }
}