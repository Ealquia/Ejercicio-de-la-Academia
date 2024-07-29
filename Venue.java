import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** La clase Venue es una clase controladora cuyos atributos son las características del lugar y del
 * concierto, y un contador de ventas diarias, y cuyos métodos son los requisitos funcionales del programa  */
public class Venue {
    //Atributos
    private String Nombre;
    private Localidad Balcon;
    private Localidad Platea;
    private Localidad VIP;
    private LocalDate fechaConcierto;
    private int VentasHoy; 
    private LocalDate fechaHoy;

    //Constructor
    public Venue(String Nombre, int Balcon, int Platea, int VIP, LocalDate fechaConcierto){
        this.Nombre = Nombre;
        this.Balcon = new Localidad("Balcón 2", Balcon, 300.0);
        this.Platea = new Localidad("Platea", Platea, 600.0);
        this.VIP = new Localidad("Balcón VIP", VIP, 1800.0);
        this.VentasHoy = 0; //Al crear un venue, las ventas de ese día están en 0
        this.fechaConcierto = fechaConcierto;
        this.fechaHoy = LocalDate.now(); //AL crear el venue, fechaHoy se iguala a la de ese día
    }

    //MÉTODOS

    /** El método actualizarFecha compara el atributo fechaHoy con la fecha del día en que se usa
     * Las fechas serán distintas si es la primera vez que se usa el sistema en un día determinado,
     * en cuyo caso se cambiará fechaHoy a la fecha del día, y se resetearan las ventas del día a 0
     */
    public void actualizarFecha(){
        if (this.fechaHoy != LocalDate.now()){
            this.fechaHoy = LocalDate.now();
            this.VentasHoy = 0;
        }
    }

    /** El método validarEspacio toma como parámetro un comprador. Si la cantidad de boletos que pidió
     * el comprador es menor o igual a los asientos disponibles en la localidad que especificó el 
     * comprador, devuelve verdadero. De lo contrario, devuelve falso.
     * @param Comprador
     * @return Boolean
     */
    public boolean validarEspacio(Comprador Comprador){
        return Comprador.getcantidad() <= Comprador.getLocalidad().disponibles();
    }

    /** El método validarPresupuesto toma como parámetro un comprador. Si el presupuesto del comprador es 
     * menor al precio de los boletos pedidos, devuelve falso. Si el presupuesto es mayor o igual, devuelve
     * verdadero.
     * @param Comprador
     * @return Boolean
     */
    public boolean validarPresupuesto(Comprador Comprador){
        return Comprador.getPresupuesto() >= Comprador.getLocalidad().getPrecio()*Comprador.getcantidad(); 
    }

    /** El método VerificarVenta toma como parámetro un comprador. Devuelve verdadero si los métodos
     * validarEspacio y validarPresupuesto los dos dan verdadero para el comprador ingresado. 
     * @param comprador
     * @return boolean
     */
    public boolean VerificarVenta(Comprador Comprador){
        return this.validarEspacio(Comprador) && this.validarPresupuesto(Comprador);
    }

    /**
     * El método Venta toma como parámetro un comprador. Crea y devuelve una lista con la cantidad de boletos 
     * solicitada por el comprador. Actualiza las ventas del día y las ventas de la localidad correspondiente.
     * @param comprador
     * @return Lista de boletos
     */
    public List<Boleto> Venta(Comprador comprador){
        List<Boleto> boletos = new ArrayList<>(); // Inicializar la lista
        for (int i = 0; i < comprador.getcantidad(); i++) { //Por cada boleto pedido
            this.VentasHoy = this.VentasHoy + 1; //Añadir una venta del día
            boletos.add(new Boleto(comprador.getLocalidad(), comprador, this)); //Crear un boleto y añadirlo a la lista
            comprador.getLocalidad().setVendidos(comprador.getLocalidad().getVendidos() + 1); //Añadir una venta a la localidad correspondiente
        }
        comprador.getBoletos().addAll(boletos); //Añadir la lista de boletos al atributo boletos del comprador
        return boletos;
    }

    /**
     * El método Confirmación toma como parámetro una lista de boletos. Crea y devuelve un string concatenando 
     * los toStrin de todos los boletos en la lista, separados por una línea de "=" para simular la forma de 
     * un boleto.
     * @param boletos
     * @return String
     */
    public String Confirmacion(List<Boleto> boletos){
        String confirmacion = "=================================================\n";
        for (Boleto boleto : boletos) {
            confirmacion = confirmacion + boleto.toString()
            + "\n=================================================\n";
        }
        return confirmacion;
    }

    /**
     * El método consultar_disponibilidad sin parámetros devuelve los toString concatenados de las 3 localidades,
     * que a su vez describen los boletos disponibles para cada una.
     * @return String
     */
    public String Consultar_disponibilidad(){
        return this.Balcon.toString() + "\n" + this.Platea.toString() + "\n" + this.VIP.toString();
    }

    /**
     * El método consultar_disponibilidad con parámetro Localidad, devuelve el toString de la localidad 
     * indicada.
     * @param localidad
     * @return String
     */
    public String Consultar_disponibilidad(Localidad localidad){
        return localidad.toString();
    }

    /**
     * El método reporteCaja toma los boletos vendidos en cada localidad, los multiplica por su precio, 
     * y luego suma las 3 localidades, devolviendo el ingreso total generado.
     * @return double
     */
    public double reporteCaja(){
        return this.Balcon.getPrecio()*this.Balcon.getVendidos() 
        + this.Platea.getPrecio()*this.Platea.getVendidos()
        + this.VIP.getPrecio()*this.VIP.getVendidos();
    }

    //Setters and getters
    public String getNombre() {
        return this.Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public Localidad getBalcon() {
        return this.Balcon;
    }

    public void setBalcon(Localidad Balcon) {
        this.Balcon = Balcon;
    }

    public Localidad getPlatea() {
        return this.Platea;
    }

    public void setPlatea(Localidad Platea) {
        this.Platea = Platea;
    }

    public Localidad getVIP() {
        return this.VIP;
    }

    public void setVIP(Localidad VIP) {
        this.VIP = VIP;
    }

	public int getVentasHoy() {
    return this.VentasHoy; }

	public void setVentasHoy(int VentasHoy) {
    this.VentasHoy = VentasHoy; }

    public LocalDate getFechaHoy() {
        return this.fechaHoy; }

    public void setFechaHoy(LocalDate fechaHoy) {
        this.fechaHoy = fechaHoy;}


    public LocalDate getFechaConcierto() {
        return this.fechaConcierto;
    }

    public void setFechaConcierto(LocalDate fechaConcierto) {
        this.fechaConcierto = fechaConcierto;
    }

} 
