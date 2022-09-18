package lecture6_binary_search;

public class BinarySearch {
    static boolean check(int m, int param, int[] a) {
        if(a[m]==param){
            return true;
        } else {
            return false;
        }
    }

    static int lbinsearch(int l, int r, int param, int[] a) {
        while (l < r) {
            int m = (l + r) / 2;
            if (check(m, param, a)) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    static int rbinsearch(int l, int r, int param, int[] a) {
        while (l < r) {
            int m = (l + r + 1) / 2;
            if (check(m, param, a)) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4, 4, 4, 4, 5, 6, 7, 8};
        System.out.println(lbinsearch(0, a.length-1, 10, a));
        System.out.println(rbinsearch(0, a.length-1, 10, a));
    }
}
