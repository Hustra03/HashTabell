import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ZipIndexValue {
    NodeIntCode[] data;
    int max;

    public ZipIndexValue(String file) {
        data = new NodeIntCode[100000];
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s", ""));
                data[code] = new NodeIntCode(code, row[1], Integer.valueOf(row[2]));
            }
            max = i - 1;
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }

    public boolean lookupString(String zipString) {
        Integer zip = Integer.valueOf(zipString.replaceAll("\\s", ""));
        if (zip > data.length - 1 || zip < 0) {
            return false;
        }

        if (zip == data[zip].getCode()) {
            return true;
        }
        return false;
    }

    public boolean lookup(int zip) {
        if (zip > data.length - 1 || zip < 0) {
            return false;
        }

        if (zip == data[zip].getCode()) {
            return true;
        }
        return false;
    }

}
