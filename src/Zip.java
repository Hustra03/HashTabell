import java.io.BufferedReader;
import java.io.FileReader;

public class Zip {
    Node[] data;
    int max;

    public class Node {

        String code;
        String name;
        int pop;

        public Node(String code, String name, int pop) {
            this.code = code;
            this.name = name;
            this.pop = pop;
        }

        public String getCode() {
            return this.code;
        }

    }

    public Zip(String file) {
        data = new Node[10000];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                data[i++] = new Node(row[0], row[1], Integer.valueOf(row[2]));
            }
            max = i - 1;
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }

    public boolean linearSeach(String zip) {
        int position = 0;
        while (zip.equalsIgnoreCase(data[position].getCode()) == false) {
            position += 1;
            if (position > max) {
                return false;
            }
        }
        return true;
    }

    public boolean binarySearch(String zip) {

        int currentMin = 0;
        int currentMax = max;
        int position = (currentMin + currentMax) / 2;
        while (true) {
            if (0 > zip.compareToIgnoreCase(data[position].getCode())) {
                currentMax = position-1;
                position = (currentMin + currentMax) / 2;
            }
            if (0 == zip.compareToIgnoreCase(data[position].getCode())) {
                return true;
            }
            if (0 < zip.compareToIgnoreCase(data[position].getCode())) {
                currentMin = position+1;
                position = (currentMin + currentMax) / 2;
            }
            if (currentMin > currentMax) {
                return false;
            }
        }

    }

}
