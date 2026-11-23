import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

//kd55-tv 2662d7

public class ClienteRMI {
    public static void main(String[] args) {
        try
        {
            Registry registro = LocateRegistry.getRegistry("localhost",1099);
            Aforo aforo = (Aforo) registro.lookup("Aforo");

            System.out.println("Servidor Conectador correctamente");

            int cantidadActual = 0;
            int repeticiones = 0;

            while (repeticiones <= 10)
            {
                cantidadActual = aforo.SumarAforo(cantidadActual);

                if (cantidadActual == -1)
                {
                    System.out.println("Aforo lleno");
                    break;
                }

                System.out.println("Aforo actual: " + cantidadActual);


                repeticiones++;
            }
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
}
