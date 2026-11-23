import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServidorRMI {
    public static void main(String[] args) throws Exception{
        Registry registro = LocateRegistry.createRegistry(1099);

        AforoImpl Aforo = new AforoImpl();

        Aforo stub = (Aforo) UnicastRemoteObject.exportObject(Aforo, 0);

        registro.rebind("Aforo", stub);

        System.out.println("Servidor RMI levantado");
        System.out.println("Esperando clientes...");
    }
}
