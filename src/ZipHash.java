import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ZipHash {
    DoubleLinkedList[] data;
    int[] key;
    int max;
    int mod;

    public ZipHash(String file, int M) {
        key = new int[20000];
        this.mod = M;
        data = new DoubleLinkedList[mod];
        for (int i = 0; i < data.length; i++) {
            data[i]=new DoubleLinkedList();
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s", ""));
                i++;
                int index = code % mod;
                data[index].add(code, row[1], Integer.valueOf(row[2]));
                key[i] = code;
            }
            this.max = i - 1;
        } catch (Exception e) {
            System.out.println(" file " + file + " not found exception"+ e);
        }
    }

    public boolean lookup(int zip) {
        if (zip < 0) {
            return false;
        }

        int index = zip%mod;
        Boolean found = data[index].find(zip);

        return found;

    }

    public void collisions(int mod) {
        int[] dataCollision = new int[mod];
        int[] cols = new int[70];
        System.out.println("Max :" + max);
        for (int i = 0; i < max; i++) {
            Integer index = key[i] % mod;
            cols[dataCollision[index]]++;
            dataCollision[index]++;
        }

        System.out.println("Mod"+mod);
        for (int i = 0; i < cols.length; i++) {
            System.out.print("{ "+ i +", " + cols[i]+"}"+"\t" );
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
