import java.io.BufferedReader;
import java.io.FileReader;

public class ZipCodeInt {
    Node[] data;
    int max;

    public class Node {

        int code;
        String name;
        int pop;

        public Node(int code, String name, int pop) {
            this.code = code;
            this.name = name;
            this.pop = pop;
        }

        public int getCode() {
            return this.code;
        }

    }

    public ZipCodeInt(String file) {
        data = new Node[10000];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s", ""));
                data[i++] = new Node(code, row[1], Integer.valueOf(row[2]));
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
