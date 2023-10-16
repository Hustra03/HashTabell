import java.io.BufferedReader;
import java.io.FileReader;

public class ZipHash {
    NodeIntCode[] data;
    int[] key;
    int max;

    public ZipHash(String file) {
        key = new int[100000];
        data = new NodeIntCode[10000];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s", ""));
                data[i++] = new NodeIntCode(code, row[1], Integer.valueOf(row[2]));
                key[code] = i - 1;
            }
            max = i - 1;
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }

    public boolean lookup(int zip) {
        if (zip > key.length - 1 || zip < 0) {
            return false;
        }
        int index = key[zip];

        if (zip == data[index].getCode()) {
            return true;
        }
        return false;
    }

    public void collisions(int mod) {
        int[] data = new int[mod];
        int[] cols = new int[mod];
        
        for (int i = 0; i < max; i++) {
            Integer index = ( key[i] % mod);
            cols[index]++;
            data[index]++;
        }
        System.out.print(mod);
        for (int i = 0; i < 10; i++) {
            System.out.print("\t" + cols[i]);
        }
        System.out.println();
    }

}
