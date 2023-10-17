import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ZipCodeInt {
    NodeIntCode[] data;
    int max;


    public ZipCodeInt(String file) {
        data = new NodeIntCode[10000];
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s", ""));
                data[i++] = new NodeIntCode(code, row[1], Integer.valueOf(row[2]));
            }
            max = i - 1;
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }

    public boolean linearSeach(int zip) {
        int position = 0;
        while (zip!= data[position].getCode()) {
            position += 1;
            if (position > max) {
                return false;
            }
        }
        return true;
    }

    public boolean binarySearch(int zip) {

        int currentMin = 0;
        int currentMax = max;
        int position = (currentMin + currentMax) / 2;
        while (true) {
            if ( zip < data[position].getCode()) {
                currentMax = position - 1;
                position = (currentMin + currentMax) / 2;
            }
            if (zip == data[position].getCode()) {
                return true;
            }
            if (zip > data[position].getCode()) {
                currentMin = position + 1;
                position = (currentMin + currentMax) / 2;
            }
            if (currentMin > currentMax) {
                return false;
            }
        }

    }

}
