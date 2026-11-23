public class AforoImpl implements Aforo {
    private int aforoActual = 0;
    private final int capacidadTotal = 5;

    @Override
    public synchronized int SumarAforo() {
        if (aforoActual < capacidadTotal) {
            aforoActual++;
            return aforoActual;
        }

        return -1;
    }
}
