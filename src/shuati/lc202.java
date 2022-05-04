package shuati;

import java.util.HashSet;
import java.util.Set;

public class lc202 {

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (true) {
            if (n == 1) {
                return true;
            }
            if (set.contains(n)) {
                return false;
            }
            set.add(n);
            n = trans(n);
        }

    }

    private int trans(int n) {
        int k = 0;
        int newNum = 0;
        while (true) {
            newNum += (n % 10) * (n % 10);
            k++;
            n = n / 10;
            if (n == 0) {
                break;
            }
        }
        return newNum;
    }
}
