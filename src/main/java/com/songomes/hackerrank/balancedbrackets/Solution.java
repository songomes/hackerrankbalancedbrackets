package com.songomes.hackerrank.balancedbrackets;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class Result {

    /*
     * Complete the 'isBalanced' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */
    public static String isBalanced(String s) {

        final String YES = "YES";
        final String NO = "NO";

        if (s.length() >= 1 && s.length() <= 1000) {
            // do nothing
        } else {
            throw new IllegalArgumentException();
        }

        String[] chaveA = new String[]{"{", "[", "("};
        String[] chaveF = new String[]{"}", "]", ")"};
        List<Bracket> brackets = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {

            String c = s.substring(i, i + 1);

            boolean fechado = false;
            for (String chaveF_value : chaveF) {
                if (c.equals(chaveF_value)) {
                    fechado = true;
                    break;
                }
            }

            Bracket b = new Bracket(c, fechado);

            if (fechado) {
                for (int j = (brackets.size() - 1); j >= 0; j--) {

                    Bracket j_bracket = brackets.get(j);

                    if (j_bracket.possuiPar()) {
                        continue;
                    }

                    if (j_bracket.isFechado()) {
                        // do nothing
                    } else {

                        if (getIndex(j_bracket.getBracket(), chaveA) == getIndex(b.getBracket(), chaveF)) {
                            b.possuiPar(true);
                            j_bracket.possuiPar(true);
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }

            brackets.add(b);
        }

        for (Bracket b : brackets) {
            if (b.possuiPar()) {
                // do nothings
            } else {
                return NO;
            }
        }

        return YES;
    }

    public static int getIndex(String c, String[] arrChave) {
        for (int i = 0; i < arrChave.length; i++) {
            if (c.equals(arrChave[i])) {
                return i;
            }
        }
        throw new IllegalArgumentException();
    }

}

class Bracket {

    private String bracket = null;

    private boolean fechado = false;

    private boolean possuiPar = false;

    public Bracket(String bracket, boolean fechado) {
        this.bracket = bracket;
        this.fechado = fechado;
    }

    public boolean isFechado() {
        return fechado;
    }

    public String getBracket() {
        return this.bracket;
    }

    public void setFechado(boolean fechado) {
        this.fechado = fechado;
    }

    public boolean possuiPar() {
        return possuiPar;
    }

    public void possuiPar(boolean possuiPar) {
        this.possuiPar = possuiPar;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String s = bufferedReader.readLine();

                String result = Result.isBalanced(s);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
