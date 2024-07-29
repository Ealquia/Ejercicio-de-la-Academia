import java.util.ArrayList;
import java.util.List;

public class Comprador {
    //Atrivutos
    private String Nombre;
    private String Email;
    private int cantidad;
    private double presupuesto;
    private Localidad localidad;
	private List<Boleto> boletos;

    //Constructor
	public Comprador(String Nombre, String Email, int cantidad, double presupuesto, Localidad localidad){
		this.Nombre = Nombre;
		this.Email = Email;
		this.cantidad = cantidad;
		this.presupuesto = presupuesto;
		this.localidad = localidad;
        this.boletos = new ArrayList<>();
	}
    
	//Setters and getters
	 public String getNombre() {
        return this.Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public int getcantidad() {
        return this.cantidad;
    }

    public void setcantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPresupuesto() {
        return this.presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Localidad getLocalidad() {
        return this.localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public List<Boleto> getBoletos() {
        return this.boletos;
    }

    public void setBoletos(List<Boleto> boletos) {
        this.boletos = boletos;
    }
}