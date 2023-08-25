import java.util.ArrayList;
class Sales{
    public static void main(String[] args){
        String[] salaryRanges = {
            "$200-299", "$300-399", "$400-499", "$500-599",
            "$600-699", "$700-799", "$800-899", "$900-999", "$1,000 and over"
        };
        int[] counters = new int[salaryRanges.length];
        int[] salesData = {5000, 6200, 3800, 7500, 8900, 4500, 1100, 6700, 9800, 300};
        for (int sales : salesData) {
            double salary = 200 + 0.09 * sales;
            if (salary < 300) {
                counters[0]++;
            } else if (salary < 400) {
                counters[1]++;
            } else if (salary < 500) {
                counters[2]++;
            } else if (salary < 600) {
                counters[3]++;
            } else if (salary < 700) {
                counters[4]++;
            } else if (salary < 800) {
                counters[5]++;
            } else if (salary < 900) {
                counters[6]++;
            } else if (salary < 1000) {
                counters[7]++;
            } else {
                counters[8]++;
            }
        }
        System.out.printf("%-15s %s%n", "Salary Range", "Number of Salespeople");
        System.out.println("==============================");
        for (int i = 0; i < salaryRanges.length; i++) {
            System.out.printf("%-15s %d%n", salaryRanges[i], counters[i]);
        }
    }
}