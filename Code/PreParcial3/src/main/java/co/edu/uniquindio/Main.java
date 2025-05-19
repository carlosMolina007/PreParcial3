package co.edu.uniquindio;

import co.edu.uniquindio.Enums.TipoCarro;
import co.edu.uniquindio.Enums.TipoServicio;
import co.edu.uniquindio.Gestores.*;
import co.edu.uniquindio.Model.*;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Empresa empresa = new Empresa("INVIAS UQ", "123.456.789");



        String[] options = {
                "Gestionar peajes",
                "Gestionar conductores",
                "Gestionar recaudadores",
                "Gestionar motos",
                "Gestionar carros",
                "Gestionar camiones",
                "Asignar un vehiculo a un conductor",
                "Registrar vehiculo a un peaje",
                "Listado de vehiculos que han pasado por algún peaje",
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
                            boolean verificar = empresa.getgPeaje().crear(newPeaje);
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
                            boolean verificarActualizar = empresa.getgPeaje().actualizar(peajeActualizar);
                            if (verificarActualizar) {
                                JOptionPane.showMessageDialog(null, "Se ha actualizado con éxito");
                            } else {
                                JOptionPane.showMessageDialog(null, "Peaje inexistente");
                            }
                            break;
                        case 3:
                            String peajeBuscar = JOptionPane.showInputDialog(null, "Ingrese el nombre del peaje que desea buscar");
                            Peaje peajeBuscado = empresa.getgPeaje().buscar(peajeBuscar);
                            if (peajeBuscado != null) {
                                JOptionPane.showMessageDialog(null, "Se encontró el peaje:\n" + peajeBuscado);
                            } else {
                                JOptionPane.showMessageDialog(null, "Peaje inexistente");
                            }
                            break;
                        case 4:
                            String peajeEliminar = JOptionPane.showInputDialog(null, "Ingrese el nombre del peaje que desea eliminar");
                            boolean peajeEliminado = empresa.getgPeaje().eliminar(peajeEliminar);
                            if (peajeEliminado) {
                                JOptionPane.showMessageDialog(null, "Se ha eliminado con exito");
                            } else {
                                JOptionPane.showMessageDialog(null, "Peaje inexistente");
                            }
                            break;
                        case 5:
                            if (empresa.getgPeaje().listar().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Lista vacía, por favor registre al menos 1 peaje");
                            } else {
                                JOptionPane.showMessageDialog(null, empresa.getgPeaje().listar());
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
                            boolean verificarConductor = empresa.getgConductor().crear(newConductor);
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
                            boolean conductorVActualizar = empresa.getgConductor().actualizar(conductorActualizar);
                            if (conductorVActualizar) {
                                JOptionPane.showMessageDialog(null, "Se ha actualizado con éxito" + conductorActualizar);
                            } else {
                                JOptionPane.showMessageDialog(null, "Conductor inexistente");
                            }
                            break;
                        case 3:
                            String idConductorBuscar = JOptionPane.showInputDialog(null, "Ingrese el número de documento del conductor que desea buscar");
                            Conductor conductorBuscar = empresa.getgConductor().buscar(idConductorBuscar);
                            if (conductorBuscar != null) {
                                JOptionPane.showMessageDialog(null, "Se encontró el siguiente conductor:\n" + conductorBuscar);
                            } else {
                                JOptionPane.showMessageDialog(null, "Conductor inexistente");
                            }
                            break;
                        case 4:
                            String idConductorEliminar = JOptionPane.showInputDialog(null, "Ingrese el número de documento del conductor que desea eliminar");
                            boolean conductorEliminar = empresa.getgConductor().eliminar(idConductorEliminar);
                            if (conductorEliminar) {
                                JOptionPane.showMessageDialog(null, "Se ha eliminado con exito");
                            } else {
                                JOptionPane.showMessageDialog(null, "Conductor inexistente");
                            }
                            break;
                        case 5:
                            if (empresa.getgConductor().listar().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Lista vacía, por favor registre al menos 1 conductor");
                            } else {
                                JOptionPane.showMessageDialog(null, empresa.getgConductor().listar());
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
                            boolean validarNewRecaudador = empresa.getgRecaudador().crear(newRecaudador);
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
                            boolean verificarActRecaudador = empresa.getgRecaudador().actualizar(recaudadorActualizar);
                            if (verificarActRecaudador) {
                                JOptionPane.showMessageDialog(null, "Se ha actualizado con exito");
                            } else {
                                JOptionPane.showMessageDialog(null, "Recaudador inexistente");
                            }
                            break;

                        case 3:
                            String idBuscarRecaudador = JOptionPane.showInputDialog(null, "Ingrese el número de identificación del reccaudador que desea buscar");
                            Recaudador recaudadorBuscado = empresa.getgRecaudador().buscar(idBuscarRecaudador);
                            if (recaudadorBuscado != null) {
                                JOptionPane.showMessageDialog(null, "Se ha encontrado el siguiente recaudador:\n" + recaudadorBuscado);
                            } else {
                                JOptionPane.showMessageDialog(null, "Recaudador inexistente");
                            }
                            break;

                        case 4:
                            String idRecaudadorEliminar = JOptionPane.showInputDialog(null, "Ingrese el número de identificación del recaudador que desea eliminar");
                            boolean recaudadorEliminar = empresa.getgRecaudador().eliminar(idRecaudadorEliminar);
                            if (recaudadorEliminar) {
                                JOptionPane.showMessageDialog(null, "Se ha eliminado con exito");
                            } else {
                                JOptionPane.showMessageDialog(null, "Recaudador inexistente");
                            }
                            break;

                        case 5:
                            if (empresa.getgRecaudador().listar().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Lista vacía, por favor registre al menos 1 recaudador");
                            } else {
                                JOptionPane.showMessageDialog(null, empresa.getgRecaudador().listar());
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
                            boolean validarMoto = empresa.getgMoto().crear(newMoto);
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
                            boolean validarMotoActualizar = empresa.getgMoto().actualizar(motoActualizar);
                            if (validarMotoActualizar) {
                                JOptionPane.showMessageDialog(null, "Se ha actualizado con exito");
                            }else {
                                JOptionPane.showMessageDialog(null, "Moto inexistente");
                            }
                            break;

                        case 3:
                            String placaBuscarMoto = JOptionPane.showInputDialog(null, "Ingrese la placa de la moto que desea consultar");
                            Moto motoBuscada = empresa.getgMoto().buscar(placaBuscarMoto);
                            if (motoBuscada != null) {
                                JOptionPane.showMessageDialog(null, "Se ha encontrado el siguiente resultado:\n" + motoBuscada.getDescripcion());
                            }else {
                                JOptionPane.showMessageDialog(null, "Moto inexistente");
                            }
                            break;

                        case 4:
                            String placaEliminarMoto = JOptionPane.showInputDialog(null, "Ingrese la placa de la moto que desea eliminar");
                            boolean verificarEliminarMoto = empresa.getgMoto().eliminar(placaEliminarMoto);
                            if (verificarEliminarMoto) {
                                JOptionPane.showMessageDialog(null, "Se ha eliminado con exito");
                            }else {
                                JOptionPane.showMessageDialog(null, "Moto inexistente");
                            }
                            break;

                        case 5:
                            if (empresa.getgMoto().listar().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Lista vacía, por favor registre al menos 1 moto");
                            }else {
                                JOptionPane.showMessageDialog(null, empresa.getgMoto().listar());
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
                            boolean validarCarro = empresa.getgCarro().crear(newCarro);
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
                            boolean validarCarroActualizar = empresa.getgCarro().actualizar(carroActualizar);
                            if (validarCarroActualizar) {
                                JOptionPane.showMessageDialog(null, "Se ha actualizado con exito");
                            }else{
                                JOptionPane.showMessageDialog(null, "Carro inexistente");
                            }
                            break;

                        case 3:
                            String placaCarroBuscar = JOptionPane.showInputDialog(null, "Ingrese la placa del carro que desea buscar");
                            Carro carroBuscado = empresa.getgCarro().buscar(placaCarroBuscar);
                            if (carroBuscado != null) {
                                JOptionPane.showMessageDialog(null, "Se ha encontrado el siguiente carro: \n"+carroBuscado.getDescripcion());
                            }else{
                                JOptionPane.showMessageDialog(null, "Carro inexistente");
                            }
                            break;

                        case 4:
                            String placaCarroBorrar = JOptionPane.showInputDialog(null, "Ingrese la placa del carro que desea eliminar");
                            boolean verificarCarroBorrar = empresa.getgCarro().eliminar(placaCarroBorrar);
                            if (verificarCarroBorrar) {
                                JOptionPane.showMessageDialog(null, "Se ha eliminado con exito");
                            }else {
                                JOptionPane.showMessageDialog(null, "Carro inexistente");
                            }
                            break;

                        case 5:
                            if (empresa.getgCarro().listar().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Lista vacía, agregue al menos 1 carro");
                            }else {
                                JOptionPane.showMessageDialog(null, empresa.getgCarro().listar());
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
                            boolean validarCamion = empresa.getgCamion().crear(newCamion);
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
                            boolean validarCamionAct = empresa.getgCamion().actualizar(camionAct);
                            if(validarCamionAct){
                                JOptionPane.showMessageDialog(null, "Se ha actualizado con exito");
                            }else {
                                JOptionPane.showMessageDialog(null, "Camión inexistente");
                            }
                            break;

                        case 3:
                            String placaCamionBuscar = JOptionPane.showInputDialog(null, "Ingrese la placa del camión que desea buscar");
                            Camion camionBuscado = empresa.getgCamion().buscar(placaCamionBuscar);
                            if (camionBuscado != null) {
                                JOptionPane.showMessageDialog(null, "Se ha encontrado el siguiente camión: \n"+camionBuscado.getDescripcion());
                            }else {
                                JOptionPane.showMessageDialog(null, "Camión inexistente");
                            }
                            break;

                        case 4:
                            String placaCamionEliminar = JOptionPane.showInputDialog(null, "Ingrese la placa del camión que desea eliminar");
                            boolean verificarCamionEliminar = empresa.getgCamion().eliminar(placaCamionEliminar);
                            if (verificarCamionEliminar) {
                                JOptionPane.showMessageDialog(null, "Se ha eliminado con exito");
                            }else {
                                JOptionPane.showMessageDialog(null, "Camión inexistente");
                            }
                            break;

                        case 5:
                            if (empresa.getgCamion().listar().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Lista vacía, ingrese al menos 1 camión");
                            }else{
                                JOptionPane.showMessageDialog(null, empresa.getgCamion().listar());
                            }
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "Opción no válida");
                            break;
                    }
                    break;
                case "Asignar un vehiculo a un conductor":
                    String idConductor = JOptionPane.showInputDialog(null, "Ingrese el número de documento del conductor al que desea agregar un vehiculo");
                    String placaVehiculoAsignar = JOptionPane.showInputDialog(null, "Ingrese la placa del vehiculo el cual quieres asignar");
                    boolean verificarAsignacion = empresa.asignarVehiculosConductor(idConductor, placaVehiculoAsignar);
                    if(verificarAsignacion){
                        JOptionPane.showMessageDialog(null, "Se ha asignado con exito");
                    }else {
                        JOptionPane.showMessageDialog(null, "Vehiculo o conductor inexistente");
                    }
                    break;

                case "Registrar vehiculo a un peaje":
                    String nombrePeaje = JOptionPane.showInputDialog(null, "Ingrese el nombre del peaje en el que está");
                    String placaRegistrar = JOptionPane.showInputDialog(null, "Ingrese la placa del vehiculo que registrará en este peaje");
                    boolean verificarRegistro = empresa.registrarVehiculoPorPeaje(placaRegistrar, nombrePeaje);
                    if(verificarRegistro){
                        JOptionPane.showMessageDialog(null, "Se ha registrado con exito");
                    }else {
                        JOptionPane.showMessageDialog(null, "Vehiculo o peaje inexistente");
                    }
                    break;

                case "Listado de vehiculos que han pasado por algún peaje":
                    String nombrePeajeB = JOptionPane.showInputDialog(null, "Ingrese el nombre del peaje del cual desea consultar los vehiculos que pasaron por el");
                    List<Vehiculo> listVehiculosPasajes = empresa.mostrarVehiculosPorPeaje(nombrePeajeB);
                    if(listVehiculosPasajes.isEmpty()){
                        JOptionPane.showMessageDialog(null, "No hay ningún vehiculo registrado en este peaje");
                    }else {
                        JOptionPane.showMessageDialog(null, listVehiculosPasajes.toString());
                    }
                    break;
                case "Consultar dinero pagado en peajes por un conductor":
                    String idConductorPP = JOptionPane.showInputDialog(null, "Ingrese el número de identificación del conductor");
                    JOptionPane.showMessageDialog(null, empresa.consultarDineroPagadoPeajes(idConductorPP));
                    break;
                case "Consultar vehiculos de un tipo pertenecientes a un conductor":
                    String idConductorVP = JOptionPane.showInputDialog(null, "Ingrese el número de identificación del conductor que desea consultar");
                    String tipoVehiculo = JOptionPane.showInputDialog(null, "Ingrese que tipo de vehiculo desea buscar: (Moto, Carro o Camión)");
                    List<Vehiculo> listTipoVConductor = empresa.retornarVehiculosTipo(idConductorVP, tipoVehiculo);
                    if(listTipoVConductor.isEmpty()){
                        JOptionPane.showMessageDialog(null, "El conductor no tiene vehiculos de tipo: "+tipoVehiculo);
                    }else {
                        JOptionPane.showMessageDialog(null, listTipoVConductor.toString());
                    }
                    break;
                case "Calcular, actualizar y registrar un vehiculo en un peaje":
                    String placaVehiculoPE = JOptionPane.showInputDialog(null, "Ingrese la placa del vehiculo que está pasando por el peaje");
                    String nombrePeajePE = JOptionPane.showInputDialog(null, "Ingrese el nombre del peaje en el que está");
                    boolean verificarARPeaje = empresa.calcularYActualizarDineroTotalPeaje(placaVehiculoPE, nombrePeajePE);
                    if(verificarARPeaje){
                        JOptionPane.showMessageDialog(null, "Se calculó el valor del peaje del vehiculo con éxito\nSe registró el vehiculo con éxito\nSe actualizó el valor total recaudado en el peaje");
                    }else {
                        JOptionPane.showInputDialog(null, "Vehiculo o peaje inexistente/incorrectos");
                    }
                    break;

                case "Buscar recaudador":
                    String nombreRecaudador = JOptionPane.showInputDialog(null, "Ingrese el nombre del recaudador que desea buscar").trim();
                    String apellidoRecaudador = JOptionPane.showInputDialog(null, "Ingrese el apellido del recaudador que desea buscar").trim();
                    Recaudador recaudador = empresa.buscarRecaudadorPorNombreYApellido(nombreRecaudador, apellidoRecaudador);
                    if(recaudador != null){
                        JOptionPane.showMessageDialog(null, recaudador.toString());
                    }else {
                        JOptionPane.showMessageDialog(null, "El recaudador no existe");
                    }
                    break;

                case "Consultar conductores con camiones que soporten más de 10 Toneladas":
                    JOptionPane.showMessageDialog(null, empresa.conductoresConCamionesCarga10T());
                    break;
            }
            if(option == "Salir"){
                JOptionPane.showMessageDialog(null, "Saliendo del programa...");
                System.exit(0);
            }
        }
    }
}