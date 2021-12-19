

public class TennisGame3 implements TennisGame {

    private int p2;
    private int p1;
    private final String p1N;
    private final String p2N;

    String[] p = new String[]{"Love", "Fifteen", "Thirty", "Forty"};

    public TennisGame3(String p1N, String p2N) {
        this.p1N = p1N;
        this.p2N = p2N;
    }

    public String getScore() {
        return Boolean.TRUE.equals(menorACuatro(p1 , p2)) ? marcador(p1 , p2) : getDifferentScore();
    }

    public String getDifferentScore() {
        return Boolean.TRUE.equals(sonIguales(p1 , p2)) ? "Deuce" : resultado(p1 , p2);
    }


    public String resultado(int p1,int p2) {
        String s = jugador(p1 , p2);
        String valor=Boolean.TRUE.equals((sonIguales(multiplicar(p1, p2),1))) ? "Advantage ": "Win for ";
        return valor.concat(s);
    }

    private int multiplicar(int p1, int p2) {
        return resta(p1, p2) * resta(p1, p2);
    }

    public int resta(int p1,int p2) {
        return p1-p2;
    }

    public String jugador(int p1,int p2) {
        return isaMayor(p1, p2) ? p1N : p2N;
    }

    private boolean isaMayor(int p1, int p2) {
        return p1 > p2;
    }

    public String marcador(int p1,int p2) {
        return Boolean.TRUE.equals(sonIguales(p1 , p2)) ? p[p1] + "-All" : p[p1] + "-" + p[p2];
    }

    public Boolean menorACuatro(int p1,int p2) {
        return isMenor(p1) && isMenor(p2) && !maxJuegos(p1,p2);
    }

    private boolean isMenor(int p1) {
        return p1 < 4;
    }

    public Boolean maxJuegos(int p1,int p2) {
        return sonIguales((p1 + p2) , 6);
    }

    public Boolean sonIguales(int p1,int p2) {
        return p1 == p2;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals("player1"))
            this.p1 += 1;
        else
            this.p2 += 1;
    }

}