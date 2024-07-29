public class Localidad {
    //Atributos
    private String Nombre;
    private int Capacidad;
    private int Vendidos;
    private double Precio;

    //Constructor
    public Localidad(String Nombre, int Capacidad, double Precio){
        this.Nombre = Nombre;
        this.Capacidad = Capacidad;
        this.Vendidos = 0;
        this.Precio = Precio;
    }

    //Setters y getters
    public String getNombre() {
        return this.Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getCapacidad() {
        return this.Capacidad;
    }

    public void setCapacidad(int Capacidad) {
        this.Capacidad = Capacidad;
    }

    public int getVendidos() {
        return this.Vendidos;
    }

    public void setVendidos(int Vendidos) {
        this.Vendidos = Vendidos;
    }

    public double getPrecio() {
        return this.Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    //Disponibilidad
    public int disponibles(){
        return this.Capacidad - this.Vendidos;
    }


    //to string
    public String toString(){
        if (this.disponibles()>0) 
        return "Hay " + this.disponibles() + " boletos disponibles en " + this.Nombre 
        + ", a un precio de Q." + this.Precio;
        else 
        return "No hay boletos disponibles en " + this.Nombre;
    }

}