import java.io.BufferedReader;
import java.io.FileReader;

public class ZipKeyArray {
    Node[] data;
    int [] key;
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

    public ZipKeyArray(String file) {
        key = new int[100000];
        data = new Node[10000];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s", ""));
                data[i++] = new Node(code, row[1], Integer.valueOf(row[2]));
                key[code]=i-1;
            }
            max = i - 1;
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }

    public boolean lookup(int zip) {
        if (zip>key.length-1 || zip<0) {
            return false;
        }
        int index= key[zip];

        if (zip == data[index].getCode()) {
            return true;
        }
        return false;
    }

}
