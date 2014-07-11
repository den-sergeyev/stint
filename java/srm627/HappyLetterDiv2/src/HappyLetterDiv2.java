import java.util.*;

public class HappyLetterDiv2 {
    public char getHappyLetter(String letters) {
        int[] counters = new int[26];
        for (int i = 0; i < letters.length(); i++)
            counters[(int) letters.charAt(i) - (int) ('a')]++;

        int candidate = 0;
        for (int i = 1; i < counters.length; i++)
            if (counters[i] > counters[candidate])
                candidate = i;


        if (counters[candidate] << 1 > letters.length())
            return (char) ((int) 'a' + candidate);

        return '.';
    }
}
