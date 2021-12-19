public class TennisGame2 implements TennisGame {
    private int pointA = 0;
    private int pointB = 0;

    private String pAres = "";
    private String pBres = "";
    private final String player1Name;
    private final String player2Name;
    private String score = "";

    private static final String[] diferenteScore = {"Love", "Fifteen", "Thirty", "Forty"};

    public TennisGame2(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public String love(int scoreA) {
        return isMenor(scoreA, 4) ? diferenteScore[scoreA] : "";
    }

    public String all(int scoreA) {
        String valor = isMenor(scoreA, 3) ? diferenteScore[scoreA] : "";
        return valor.concat("-All");
    }

    public String result1(int scoreA, int scoreB) {
        if (obtenerValor(scoreA, 3)) {
            pAres = diferenteScore[scoreA];
        }
        if (obtenerValor(scoreB, 1)) {
            pBres = diferenteScore[scoreB];
        }
        return pAres + "-" + pBres;
    }

    private boolean obtenerValor(int principal, int n) {
        return isIgual(principal, 2) || isIgual(principal, n);
    }

    public String result2(int scoreB, int scoreA) {
        if (obtenerValor(scoreA, 1)) {
            pAres = diferenteScore[scoreA];
        }
        if (obtenerValor(scoreB, 3)) {
            pBres = diferenteScore[scoreB];
        }
        return pAres + "-" + pBres;
    }


    private void isIgualScore(int scoreA, int scoreB) {
        if (operatorAnd(isIgual(scoreA, scoreB), isMenor(scoreA, 4)))
            score = all(scoreA);
    }

    public String getScore() {

        isIgualScore(pointA, pointB);

        if (operatorAnd(isIgual(pointA, pointB), isMayorIgual(pointA, 3)))
            score = "Deuce";


        if (operatorAnd(isMayor(pointA, 0), isIgual(pointB, 0))) {
            pAres = love(pointA);
            pBres = "Love";
            score = pAres + "-" + pBres;
        }

        if (operatorAnd(isMayor(pointB, 0), isIgual(pointA, 0))) {
            pBres = love(pointB);
            pAres = "Love";
            score = pAres + "-" + pBres;
        }

        playSets(pointA, pointB);
        advantage(pointA, pointB);
        win(pointA, pointB);
        return score;
    }

    private void playSets(int scoreA, int scoreB) {
        if (operatorAnd(isMayor(scoreA, scoreB), isMenor(scoreA, 4)))
            score = result1(scoreA, scoreB);
        if (operatorAnd(isMayor(scoreB, scoreA), isMenor(scoreB, 4)))
            score = result2(scoreB, scoreA);
    }

    private boolean operatorAnd(boolean mayor, boolean menor) {
        return mayor && menor;
    }


    private void advantage(int scoreA, int scoreB) {
        if (isAdvantage(scoreA, scoreB))
            resultAdvantageOrWin("Advantage ", player1Name);
        if (isAdvantage(scoreB, scoreA))
            resultAdvantageOrWin("Advantage ", player2Name);
    }

    private void resultAdvantageOrWin(String s, String player1Name) {
        score = s.concat(player1Name);
    }

    private void win(int scoreA, int scoreB) {
        if (isWin(scoreA, scoreB))
            resultAdvantageOrWin("Win for ", player1Name);
        if (isWin(scoreB, scoreA))
            resultAdvantageOrWin("Win for ", player2Name);
    }

    private boolean isWin(int valorA, int valorB) {
        return isMayorIgual(valorA, 4) && isMayorIgual(valorB, 0) && isMayorIgual(valorA - valorB, 2);
    }

    private boolean isAdvantage(int valorA, int valorB) {
        return operatorAnd(isMayor(valorA, valorB), isMayorIgual(valorB, 3));
    }

    private boolean isMayorIgual(int scoreA, int i) {
        return scoreA >= i;
    }

    private boolean isMayor(int scoreA, int i) {
        return scoreA > i;
    }

    private boolean isMenor(int scoreA, int i) {
        return scoreA < i;
    }

    private boolean isIgual(int scoreA, int scoreB) {
        return scoreA == scoreB;
    }

    public void paScore() {
        pointA++;
    }

    public void pbScore() {
        pointB++;
    }

    public void wonPoint(String player) {
        if (player.equals("player1"))
            paScore();
        else
            pbScore();
    }
}