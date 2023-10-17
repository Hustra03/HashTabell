import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ZipHash {
    DoubleLinkedList[] data;
    int[] key;
    int max;
    int mod;

    public ZipHash(String file, int mod) {
        key = new int[20000];
        data = new DoubleLinkedList[mod];
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s", ""));
                i++;
                int index=i % mod;
                data[index].add(code, row[1], Integer.valueOf(row[2]));
                key[i] = i;
            }
            this.max = i - 1;
            this.mod = mod;
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }

    public boolean lookup(int zip) {
        if (zip < 0) {
            return false;
        }

        if (true == data[zip % mod].find(zip)) {
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
        for (int i = 0; i < cols.length + 1; i++) {
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
