package lecture5_prefixsum;

public class Bestteam {
    static long bestteamsum(int[] players) {
        int bestsum = 0;
        int nowsum = 0;
        int last = 0;
        for (int i = 0; i < players.length; i++) {
            while((last<players.length) && (last==i || players[i]+players[i+1]<=players[last])){
                nowsum+=players[last];
                last++;
            }
            bestsum = Math.max(bestsum, nowsum);
            nowsum -= players[i];
        }
        return 0;
    }

    public static void main(String[] args) {


    }
}
