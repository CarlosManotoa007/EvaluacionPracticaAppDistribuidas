import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Aforo extends Remote {
    int SumarAforo() throws RemoteException;
}
