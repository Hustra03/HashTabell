import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ZipHashNoBucket {
    NodeIntCode[] data;
    int[] key;
    int max;
    int mod;

    public ZipHashNoBucket(String file, int M, int expectedMax) {
        key = new int[2 * expectedMax];
        this.mod = M;
        data = new NodeIntCode[2 * expectedMax];

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String line;
            int i = 0;
            int index = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s", ""));
                i++;
                index = hashFunction(code, mod);

                while (data[index] != null) {
                    index += 1;
                    if (index > data.length - 1) {
                        index=0;
                    }
                }

                data[index] = new NodeIntCode(code, row[1], Integer.valueOf(row[2]));
                key[i] = i;
            }
            this.max = i - 1;
        } catch (Exception e) {
            System.out.println(" file " + file + " not found exception" + e);
        }
    }

    public boolean lookup(int zip) {
        if (zip < 0) {
            return false;
        }

        int index = hashFunction(zip, mod);
        int initialIndex=index;

        while (data[index] != null) {

            if (data[index].getCode() == zip) {
                return true;
            }
            index += 1;
            if (index > data.length - 1) {
                index=0;
            }
            if(index==initialIndex)
            {return false;}
        }
        return false;

    }

    public int lookUpLength(int zip)
    {
        int numberOfComparisons=0;
        if (zip < 0) {
            return numberOfComparisons;
        }

        int index = hashFunction(zip, mod);
        int initialIndex=index;

        while (data[index] != null) {

            if (data[index].getCode() == zip) {
                return numberOfComparisons;
            }
            index += 1;
            numberOfComparisons+=1;
            if (index > data.length - 1) {
                index=0;
            }
            if(index==initialIndex)
            {return numberOfComparisons;}
        }
        return numberOfComparisons;

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
        System.out.println(" ");
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
