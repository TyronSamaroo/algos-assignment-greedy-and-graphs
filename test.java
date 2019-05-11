public class test {

    public static void main(String[] args) {
        int num = 3;
        int um = 6;
        int[][] a= new int[num][um];

        for (int i = 0; i < num; i++) {int count = 0;
            System.out.println("Im row 1");

            System.out.println(count - i);

            for (int j = 0; j < um; j++) {
               // System.out.print(a[i][j]);

                count ++;
                System.out.println(count);


            }
            System.out.println();

        }





    }
}
