public class AforoImpl implements Aforo{
    @Override
    public int SumarAforo(int aforoAcual)
    {
        final int capacidadTotal = 5;

        System.out.println("En implementacion: " + aforoAcual);

        if (aforoAcual < capacidadTotal)
            return aforoAcual += 1;

        return -1;
    }
}
