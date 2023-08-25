

public class Arrayop {
    public static void main(String[] args) {
        int[] counts = new int[10];
        for (int i = 0; i < counts.length; i++) {
            counts[i] = 0;
        }
        int[] bonus = new int[15];
        for (int i = 0; i < bonus.length; i++) {
            bonus[i]++;
        }
        int[] bestScores = {95, 88, 92, 78, 100};
        System.out.println("Values of bestScores array:");
        for (int score : bestScores) {
            System.out.println(score);
        }
    }
}
