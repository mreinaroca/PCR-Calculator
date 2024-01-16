package GeneralTools;



public class ConsoleTable {

    public static void printTable(String[] headers, String[][] data) {
        int numColumns = headers.length;
        System.out.println("\n");
        // Print header row
        for (String header : headers) {
            System.out.printf("%-24s", header); // Adjust the width as needed
        }
        System.out.println();

        // Print data rows
        for (String[] row : data) {
            for (int i = 0; i < numColumns; i++) {
                System.out.printf("%-24s", row[i]); // Adjust the width as needed
            }
            System.out.println();
        }
        System.out.println("\n");
    }

}
