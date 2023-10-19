public class App {
    public static void main(String[] args) throws Exception {
        String file = "src\\postnummer.csv";
        // zipTest(file);
        // lookUpBenchmark(file);
         collisonTest(file);
        //noBucketBenchmark(file);
    }

    public static void zipTest(String file) {
        Zip zip = new Zip(file);
        ZipCodeInt zip2 = new ZipCodeInt(file);
        ZipIndexValue zip3 = new ZipIndexValue(file);
        ZipHash zip4 = new ZipHash(file, 5000);
        ZipHashNoBucket zip5 = new ZipHashNoBucket(file, 5000, 10000);
        System.out.println("Zip First Expected True:             " + zip.linearSeach("111 15"));
        System.out.println("Zip Code Int Expected True:          " + zip2.linearSeach(11115));
        System.out.println("Zip Index Value Expected True:       " + zip3.lookup(11115));
        System.out.println("Zip Hash Linked List Expected True:  " + zip4.lookup(11115));
        System.out.println("Zip Hash No Bucket Expected True:    " + zip5.lookup(11115));

        System.out.println("");

        System.out.println("Zip First Expected False:            " + zip.linearSeach("999 999"));
        System.out.println("Zip Code Int Expected False:         " + zip2.linearSeach(999999));
        System.out.println("Zip Index Value Expected False:      " + zip3.lookup(999999));
        System.out.println("Zip Hash Linked List Expected False: " + zip4.lookup(999999));
        System.out.println("Zip Hash No Bucket Expected False:   " + zip5.lookup(999999));

    }

    public static void noBucketBenchmark(String file) {
        int currentValue = 0;
        int modulos[] = { 100, 250, 500, 1000, 1234, 2000, 4000, 8000, 10000, 12345, 15000, 20000, 30000,40000};
        int arraySizes[] = { 10000, 20000};
        for (int j : arraySizes) {
            for (int k : modulos) {

                if (j * 2 >= k) {

                    ZipHashNoBucket zip5 = new ZipHashNoBucket(file, k, j);
                    currentValue = 0;
                    for (int i = 0; i < 100000; i++) {
                        currentValue += zip5.lookUpLength(i);
                    }
                    currentValue /= 100000;
                    System.out.println(
                            "Average Comparisons for maxSize : " + j + " Modulo : " + k + " : " + currentValue);
                }
            }
        }
    }

    public static void collisonTest(String file) {
        int modulos[] = { 10000,13513,13600 ,14000,20000,30000 };
        for (int i : modulos) {

            ZipHash zip = new ZipHash(file, i);
            zip.collisions(i);
        }
    }

    public static void lookUpBenchmark(String file) {
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

        ZipHash zip4 = new ZipHash(file, 10000);

        minimum1 = Long.MAX_VALUE;
        minimum2 = Long.MAX_VALUE;

        for (int i = 0; i < attempts; i++) {

            t0 = System.nanoTime();
            for (int j = 0; j < searches; j++) {

                zip4.lookup(11115);
            }
            t1 = System.nanoTime() - t0;
            if (t1 < minimum1) {
                minimum1 = t1;
            }
        }
        System.out.println("Look Up Hash 111 15 : " + minimum1);

        for (int i = 0; i < attempts; i++) {

            t0 = System.nanoTime();
            for (int j = 0; j < searches; j++) {

                zip4.lookup(98499);
            }
            t1 = System.nanoTime() - t0;
            if (t1 < minimum2) {
                minimum2 = t1;
            }
        }
        System.out.println("Look Up Hash 984 99: " + minimum2);

        ZipHashNoBucket zip5 = new ZipHashNoBucket(file, 10000, 10000);

        minimum1 = Long.MAX_VALUE;
        minimum2 = Long.MAX_VALUE;

        for (int i = 0; i < attempts; i++) {

            t0 = System.nanoTime();
            for (int j = 0; j < searches; j++) {

                zip5.lookup(11115);
            }
            t1 = System.nanoTime() - t0;
            if (t1 < minimum1) {
                minimum1 = t1;
            }
        }
        System.out.println("No Bucket 111 15 : " + minimum1);

        for (int i = 0; i < attempts; i++) {

            t0 = System.nanoTime();
            for (int j = 0; j < searches; j++) {

                zip5.lookup(98499);
            }
            t1 = System.nanoTime() - t0;
            if (t1 < minimum2) {
                minimum2 = t1;
            }
        }
        System.out.println("No Bucket 984 99: " + minimum2);
    }
}
