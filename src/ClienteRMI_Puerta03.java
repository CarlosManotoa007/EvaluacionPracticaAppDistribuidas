import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClienteRMI_Puerta03 {
    public static void main(String[] args) {
        try
        {
            Registry registro = LocateRegistry.getRegistry("localhost",1099);
            Aforo aforo = (Aforo) registro.lookup("Aforo");

            System.out.println("Servidor conectado correctamente");

            int cantidadActual = aforo.SumarAforo();
            validar(cantidadActual);

            cantidadActual = aforo.SumarAforo();
            validar(cantidadActual);
        }
        catch (RemoteException e)
        {
            System.out.println("Error de comunicacion: " + e.getMessage());
        }
        catch (NotBoundException e)
        {
            System.out.println("Servidor no encontrado: " + e.getMessage());
        }
    }

    public static void validar(int cantidadActual)
    {
        if (cantidadActual == -1) {
            System.out.println("Aforo lleno");
        } else {
            System.out.println("Aforo actual: " + cantidadActual);
        }
    }
}
