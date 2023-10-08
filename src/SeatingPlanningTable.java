import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeatingPlanningTable {
    public static void main(String[] args) {
        // Define the group sizes and the table size
        int[] groupSizes = {2, 2, 2, 2, 2, 3, 4, 4, 4, 4, 4, 5, 5, 6};
        int tableSize = 8;

        // Calculate the seating plan
        List<Table> seatingPlan = calculateSeatingPlan(groupSizes, tableSize);

        // Print information about each table in the seating plan
        for (Table table : seatingPlan) {
            System.out.println("Table Size: " + table.getSize());
            System.out.println("Group Sizes Seated: " + Arrays.toString(table.getSeatedGroups()));
            System.out.println("Vacant Seats: " + table.getVacantSeats());
            System.out.println();
        }

        // Print the total number of tables in the seating plan
        System.out.println("Total Number of Tables: " + seatingPlan.size());
    }

    // Method to calculate the seating plan
    public static List<Table> calculateSeatingPlan(int[] groupSizes, int tableSize) {
        // Sort the group sizes in descending order
        Arrays.sort(groupSizes);
        List<Table> seatingPlan = new ArrayList<>();

        // Iterate through each group size and assign them to tables
        for (int i = groupSizes.length - 1; i >= 0; i--) {
            int groupSize = groupSizes[i];
            boolean seated = false;

            // Try to seat the group at an existing table
            for (Table table : seatingPlan) {
                if (table.getVacantSeats() >= groupSize) {
                    table.addGroup(groupSize);
                    seated = true;
                    break;
                }
            }

            // If the group couldn't be seated at an existing table, create a new table
            if (!seated) {
                Table newTable = new Table(tableSize);
                newTable.addGroup(groupSize);
                seatingPlan.add(newTable);
            }
        }

        return seatingPlan;
    }

    // Nested class representing a table
    static class Table {
        private int size;
        private List<Integer> seatedGroups;

        public Table(int size) {
            this.size = size;
            this.seatedGroups = new ArrayList<>();
        }

        public int getSize() {
            return size;
        }

        public int[] getSeatedGroups() {
            return seatedGroups.stream().mapToInt(Integer::intValue).toArray();
        }

        public int getVacantSeats() {
            return size - seatedGroups.stream().mapToInt(Integer::intValue).sum();
        }

        public void addGroup(int groupSize) {
            seatedGroups.add(groupSize);
        }
    }
}
