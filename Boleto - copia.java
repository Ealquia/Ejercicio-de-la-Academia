import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Boleto {
    //Atributos 
	private Localidad localidad;
    private int numeroAsiento;
    private Comprador comprador;
    private final Venue venue;
    private final LocalDate fechaCompra;
    private final String Ticket;

    //Constructor
    public Boleto(Localidad localidad, Comprador comprador, Venue venue){
        this.localidad =localidad;
        this.numeroAsiento = localidad.getVendidos() + 1;
        this.venue = venue;
        this.comprador = comprador;
        this.fechaCompra = LocalDate.now();
        this.Ticket = this.generarTicket();
    }

    //Generar número de ticket
    public String generarTicket(){
        LocalDate fecha = LocalDate.now();
        String ticket = String.valueOf(fecha.getDayOfMonth()) + String.valueOf(fecha.getMonthValue())
        + String.valueOf(fecha.getYear())+ String.valueOf(this.venue.getVentasHoy());
        return ticket; 
    } 

    //toSTring
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return "Número de boleto: "+ this.Ticket
        + "\nNúmero de asiento: " + this.numeroAsiento + " en " + this.localidad.getNombre()
        + "\nPrecio: " + this.localidad.getPrecio()
        + "\nFecha de compra: " + this.fechaCompra.format(formatter)
        + "\nFecha del evento: "  + this.venue.getFechaConcierto().format(formatter)
        + "\nComprador: " + this.comprador.getNombre();
    }

    //Getters and setters
    public Localidad getLocalidad() {
        return this.localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public int getNumeroAsiento() {
        return this.numeroAsiento;
    }

    public void setNumeroAsiento(int numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }

    public Comprador getComprador() {
        return this.comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

}