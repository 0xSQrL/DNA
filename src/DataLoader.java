import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class DataLoader {
    public final String file;
    private int[][] data;

    public DataLoader(String file){
        this.file = file;
        Scanner scanner;
        try {
            scanner = new Scanner(new File(file));

        }catch (FileNotFoundException fnfe){
            scanner = null;
            System.err.println("File \"" + file + "\" not found!");
        }
        LinkedList<Integer> i = new LinkedList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] dataBits = line.split(",");
            for(String s: dataBits){
                try {
                    i.add(Integer.parseInt(s));
                }catch (NumberFormatException nfe){
                    int[] asInts = stringToIntArray(s);
                }
            }
        }

    }

    static int[] stringToIntArray(String str){
        int[] chars = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            if(i < str.length())
                chars[i] = (byte)str.charAt(i);
            else
                chars[i] = 0;
        }
        return chars;
    }
}
