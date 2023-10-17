import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ZipHash {
    NodeIntCode[] data;
    int[] key;
    int max;

    public ZipHash(String file) {
        key = new int[20000];
        data = new NodeIntCode[10000];
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s", ""));
                data[i++] = new NodeIntCode(code, row[1], Integer.valueOf(row[2]));
                key[i] = i;
            }
            this.max = i - 1;
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
        int[] dataCollision = new int[mod];
        int[] cols = new int[20];
        System.out.println("Max :" + max);
        for (int i = 0; i < max; i++) {
            Integer index = key[i] % mod;
            cols[dataCollision[index]]++;
            dataCollision[index]++;
        }

        System.out.println("Number of collisions per node");
        System.out.print("Mod");
        for (int i = 0; i < cols.length+1; i++) {
            System.out.print("\t" + i);
        }
        System.out.println("");
        System.out.print(mod);
        for (int i = 0; i < cols.length; i++) {
            System.out.print("\t" + cols[i]);
        }
        System.out.println();
    }

    public int hashFunction(int zip, int mod) {
        return zip % mod;
    }

    int R = 31;

    public Integer hashString(String key, int M) {
        char[] chars = key.toCharArray();
        int value = 0;
        for (int i = 0; i < chars.length; i++) {
            value = (R * value + chars[i]) % M;
        }
        return value;
    }

}
