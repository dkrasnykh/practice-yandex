package lecture5_prefixsum;

import java.util.HashMap;
import java.util.Map;

public class Zerosum {
    static Map<Long, Integer> countprefixsum(int[] nums) {
        /*
        !!если суммы совпадат, то сумма элементов между ними равна нулю
        */
        Map<Long, Integer> ans = new HashMap<>();
        long nowSum = 0;
        for (int i : nums) {
            nowSum += i;
            ans.compute(nowSum, (key, total) -> (total == null) ? 0 : total + 1);
        }
        return ans;
    }

    static int countzerosumranges(Map<Long, Integer> prefixsumbyfalue) {
        int cntranges = 0;
        for (Map.Entry entry : prefixsumbyfalue.entrySet()) {
            int cntsum = (int) entry.getValue();
            cntranges += cntsum * (cntsum - 1) / 2; //количество пар
        }
        return cntranges;
    }

    public static void main(String[] args) {

    }
}
