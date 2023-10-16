public class App {
    public static void main(String[] args) throws Exception {
        lookUpBenchmark();
    }

    public static void lookUpBenchmark() {
        Zip zip = new Zip("src\\postnummer.csv");

        long t0;
        long t1;
        long minimum1 = Long.MAX_VALUE;

        long minimum2 = Long.MAX_VALUE;
        long minimum3 = Long.MAX_VALUE;
        long minimum4 = Long.MAX_VALUE;
        int attempts = 100;
        int searches = 10;
        for (int i = 0; i < attempts; i++) {

            t0 = System.nanoTime();
            for (int j = 0; j < searches; j++) {

                zip.linearSeach("111 15");
            }
            t1 = System.nanoTime() - t0;
            if (t1 < minimum1) {
                minimum1 = t1;
            }
        }
        System.out.println("Linear Search 111 15 : " + minimum1);

        for (int i = 0; i < attempts; i++) {

            t0 = System.nanoTime();
            for (int j = 0; j < searches; j++) {

                zip.linearSeach("984 99");
            }
            t1 = System.nanoTime() - t0;
            if (t1 < minimum2) {
                minimum2 = t1;
            }
        }
        System.out.println("Linear Search 984 99 : " + minimum2);

        for (int i = 0; i < attempts; i++) {

            t0 = System.nanoTime();
            for (int j = 0; j < searches; j++) {

                zip.binarySearch("111 15");
            }
            t1 = System.nanoTime() - t0;
            if (t1 < minimum3) {
                minimum3 = t1;
            }
        }
        System.out.println("Binary Search 111 15 : " + minimum3);

        for (int i = 0; i < attempts; i++) {

            t0 = System.nanoTime();
            for (int j = 0; j < searches; j++) {

                zip.binarySearch("984 99");
            }
            t1 = System.nanoTime() - t0;
            if (t1 < minimum4) {
                minimum4 = t1;
            }
        }
        System.out.println("Binary Search 984 99 : " + minimum4);

    }
}
