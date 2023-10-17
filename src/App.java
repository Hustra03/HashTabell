public class App {
    public static void main(String[] args) throws Exception {
        // zipTest();
        // lookUpBenchmark();
        collisonTest();
    }

    public static void zipTest() {
        String file = "src\\postnummer.csv";
        Zip zip = new Zip(file);
        ZipCodeInt zip2 = new ZipCodeInt(file);
        ZipIndexValue zip3 = new ZipIndexValue(file);
        System.out.println("Expected True: " + zip.linearSeach("111 15"));
        System.out.println("Expected True: " + zip2.linearSeach(11115));
        System.out.println("Expected True: " + zip3.lookup(11115));

        System.out.println("Expected False: " + zip.linearSeach("999 999"));
        System.out.println("Expected False: " + zip2.linearSeach(999999));
        System.out.println("Expected False: " + zip3.lookup(999999));

    }

    public static void collisonTest() {
        String file ="src\\postnummer.csv";
        ZipHash zip = new ZipHash(file,2000);
        zip.collisions(2000);
    }

    public static void lookUpBenchmark() {
        String file = "src\\postnummer.csv";
        Zip zip = new Zip(file);

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
        System.out.println("Linear Search 111 15 Code String: " + minimum1);

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
        System.out.println("Linear Search 984 99 Code String: " + minimum2);

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
        System.out.println("Binary Search 111 15 Code String: " + minimum3);

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
        System.out.println("Binary Search 984 99 Code String: " + minimum4);

        ZipCodeInt zipint = new ZipCodeInt(file);
        minimum1 = Long.MAX_VALUE;
        minimum2 = Long.MAX_VALUE;
        minimum3 = Long.MAX_VALUE;
        minimum4 = Long.MAX_VALUE;

        for (int i = 0; i < attempts; i++) {

            t0 = System.nanoTime();
            for (int j = 0; j < searches; j++) {

                zipint.linearSeach(11115);
            }
            t1 = System.nanoTime() - t0;
            if (t1 < minimum1) {
                minimum1 = t1;
            }
        }
        System.out.println("Linear Search 111 15 Code Int: " + minimum1);

        for (int i = 0; i < attempts; i++) {

            t0 = System.nanoTime();
            for (int j = 0; j < searches; j++) {

                zipint.linearSeach(98499);
            }
            t1 = System.nanoTime() - t0;
            if (t1 < minimum2) {
                minimum2 = t1;
            }
        }
        System.out.println("Linear Search 984 99 Code Int: " + minimum2);

        for (int i = 0; i < attempts; i++) {

            t0 = System.nanoTime();
            for (int j = 0; j < searches; j++) {

                zipint.binarySearch(11115);
            }
            t1 = System.nanoTime() - t0;
            if (t1 < minimum3) {
                minimum3 = t1;
            }
        }
        System.out.println("Binary Search 111 15 Code Int: " + minimum3);

        for (int i = 0; i < attempts; i++) {

            t0 = System.nanoTime();
            for (int j = 0; j < searches; j++) {

                zipint.binarySearch(98499);
            }
            t1 = System.nanoTime() - t0;
            if (t1 < minimum4) {
                minimum4 = t1;
            }
        }
        System.out.println("Binary Search 984 99 Code Int: " + minimum4);

        ZipIndexValue zip3 = new ZipIndexValue(file);

        minimum1 = Long.MAX_VALUE;
        minimum2 = Long.MAX_VALUE;

        for (int i = 0; i < attempts; i++) {

            t0 = System.nanoTime();
            for (int j = 0; j < searches; j++) {

                zip3.lookup(11115);
            }
            t1 = System.nanoTime() - t0;
            if (t1 < minimum1) {
                minimum1 = t1;
            }
        }
        System.out.println("Look Up 111 15 : " + minimum1);

        for (int i = 0; i < attempts; i++) {

            t0 = System.nanoTime();
            for (int j = 0; j < searches; j++) {

                zip3.lookup(98499);
            }
            t1 = System.nanoTime() - t0;
            if (t1 < minimum2) {
                minimum2 = t1;
            }
        }
        System.out.println("Look Up 984 99: " + minimum2);

    }
}
