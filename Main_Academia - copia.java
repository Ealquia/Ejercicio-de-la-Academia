import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/** 
 * @author Mar Isabel Orozco Moscoso 24967
 * Progrmación Orientada a Objetos, Ejercicio 1, lunes 29 de julio 
 * Este programa permite configurar la información de un concierto, vender boletos para dicho concierto,
 * verificar la disponibilidad de asientos en las distintas localidades del venue, y ver cuánto dinero se 
 * generó para el concierto.
*/

public class Main_Academia{
private static final Scanner input = new Scanner(System.in);
public static void main(String[] args) {
    String enter; 
    //Configuración inicial
    System.out.println("Bienvenido al sistema de La Academia. ¡Configuremos la información del próximo concierto!");
    System.out.println("Escribe el nombre del Venue");
    String Nombre_Venue = input.nextLine();
    System.out.println("¿Cuá es la capacidad del Balcón 2 de este venue? Ingresa un número entero");
    int Balcon = input.nextInt();
    System.out.println("¿Cuál es la capacidad de la Platea de este venue? Ingresa un número entero");
    int Platea = input.nextInt();
    System.out.println("¿Cuál es la capacidad del Balcón VIP de este venue? Ingresa un número entero");
    int VIP = input.nextInt();
    System.out.println("¿Cuándo será el concierto?");
    System.out.println("Día:");
    int dia = input.nextInt();
    System.out.println("Mes (en número):");
    int mes = input.nextInt();
    System.out.println("Año:");
    int anio = input.nextInt();
    LocalDate fechaConcierto = LocalDate.of(anio, mes, dia); //Creación de un objeto LocalDate
    Venue venue = new Venue(Nombre_Venue, Balcon, Platea, VIP, fechaConcierto); //Creación de un objeto venue 
    System.out.println("¡Gracias! El concierto ha sido creado."); 

    //Uso diario
    System.out.println("Bienvenido al sistema de la Academia para el concierto en " + venue.getNombre() 
    + " el " + venue.getFechaConcierto()); //Saludo
       
        //Setear las ventas de hoy a 0 si es la primera vez que se usa el sistema en el día
    venue.actualizarFecha();

        //Generación de ventas aleatorias para probar el prototipo
    Random random = new Random();
    venue.getBalcon().setVendidos(random.nextInt(venue.getBalcon().getCapacidad() + 1));
    venue.getPlatea().setVendidos(random.nextInt(venue.getPlatea().getCapacidad() + 1));
    venue.getVIP().setVendidos(random.nextInt(venue.getVIP().getCapacidad() + 1));
    int capacidad = venue.getBalcon().getCapacidad() + venue.getPlatea().getCapacidad() + venue.getVIP().getCapacidad();
    venue.setVentasHoy(random.nextInt(capacidad));

        //Verificar si ya pasó la fecha del concierto
    if (venue.getFechaConcierto().isBefore(LocalDate.now())) {
        System.out.println("""
        El concierto ya pasó. ¿Desea ver el reporte de caja? 
        (Ingrese el número de la opción correspondiente) 
        1. Sí
        2. No """);
        int z = input.nextInt();
        if (z==1) System.out.println("El ingreso generado fue de: Q." + venue.reporteCaja()); }
        //Si el concierto ya pasó, solo da la opción de ver el ingreso generado
    else {


        //Menú principal
    int opcion = 0; while (opcion != 7) {
    System.out.println("""
    ¿Qué desea hacer a continuación? Elija el número de la opción correspondiente.
    1. Vender boletos
    2. Consultar disponibilidad total y precios
    3. Consultar disponibilidad en Balcón 1
    4. Consultar disponibilidad en Platea
    5. Consultar disponibilidad en Balcón VIP
    6. Ver reporte de caja
    7. Salir """);
    opcion = input.nextInt(); 
    switch (opcion){
        //Vender boletos
        case 1: {
            //Pedir la información del comprador y crear un nuevo objeto comprador
        System.out.println("A continuación solicitaremos una compra de boletos. Por favor ingrese la información del comprador");
        System.out.println("Nombre del comprador:");
        enter = input.nextLine();
        String Nombre_Comprador = input.nextLine();
        System.out.println("Email del comprador:");
        String Email = input.nextLine();
        int cantidad = 7; while (cantidad > 6) {
            System.out.println("¿Cuántos boletos desea comprar? Puede comprar un máximo de 6");
            cantidad = input.nextInt(); }
        System.out.println("¿Cuál es el presupuesto máximo del comprador?");
        double presupuesto = input.nextDouble();
        System.out.println("""
        ¿En qué localidad desea comprar sus boletos? Ingrese el número de la opción correspondiente:
        1. Balcón 2: la más alejada del escenario, con un precio de Q300.
        2. Platea: en medio y con una mejor vista al escenario, con un precio de Q600.
        3. Balcón 1 o VIP: la mejor vista del escenario, con un precio de Q1800. """);
        int x = input.nextInt(); Localidad localidad = new Localidad("",0,0);
        switch (x){
            case 1: localidad = venue.getBalcon(); break;
            case 2: localidad = venue.getPlatea(); break;
            case 3: localidad = venue.getVIP(); break;
            //Dependiendo del número elegido, "localidad" se convierte en un alias para el objeto Localidad correspondiente 
            //en los atributos del venue
        }
        Comprador comprador = new Comprador(Nombre_Comprador, Email,cantidad, presupuesto, localidad); //Crear objeto comprador
        System.out.println("""
        ¡Gracias! Se ha guaradado la información del comprador.
        Ahora verificaremos si la compra es posible.""");

        // Verificar si la venta puede realizarse y crear los boletos
        if (venue.VerificarVenta(comprador)){
            List<Boleto> boletos = venue.Venta(comprador);
            System.out.println("¡Todo en orden! Aquí están sus boletos:");
            System.out.println(venue.Confirmacion(boletos));
        }
        else {
            // Si no hay suficiente espacio, verificar si el comprador desea los boletos disponibles y crearlos
            if (!venue.validarEspacio(comprador)){
                if (comprador.getLocalidad().disponibles()>0){
                    System.out.println("¡Lo sentimos! Solo hay " + comprador.getLocalidad().disponibles()
                    + " boletos disponibles para la localidad solicitada."
                    + """
                    ¿Desea comprar estos boletos? 
                    (Escriba el número correspondiente)
                    1. Sí
                    2. No
                    """);
                    int y = input.nextInt();
                    if (y==1){
                        y = comprador.getLocalidad().disponibles();
                        comprador.setcantidad(y);
                        List<Boleto> boletos = venue.Venta(comprador);
                        System.out.println("¡Gracias! Aquí están sus boletos:");
                        System.out.println(venue.Confirmacion(boletos)); }
                }
                else{ System.out.println("¡Lo sentimos! Ya no hay boletos disponibles en la localidad solicitada.");}
            }
            //Si el presupuesto es insuficiente comunicarlo al usuario
            if (!venue.validarPresupuesto(comprador))
                System.out.println("Lo sentimos, su presupuesto no es suficiente para comprar estos boletos.");
        }
        break; }

        // Consultar disponibilidad  y precios
        case 2: System.out.println(venue.Consultar_disponibilidad()); break;
        case 3: System.out.println(venue.Consultar_disponibilidad(venue.getBalcon())); break;
        case 4: System.out.println(venue.Consultar_disponibilidad(venue.getPlatea())); break;
        case 5: System.out.println(venue.Consultar_disponibilidad(venue.getVIP())); break;
        case 6: System.out.println("El ingreso generado hasta ahora es de: Q." + venue.reporteCaja()); break;
    } //Fin del switch
} //Fin del while
} //Fin del else
System.out.println("Gracias por usar el sistema. ¡Nos vemos la próxima!"); //Despedida
} //Fin del main
} //FIn de la clase