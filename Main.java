import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main{

    static Random generator = new Random();

    public static void main(String[] args) {

        List<Integer> numberList = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            numberList.add(generator.nextInt(10001));
        }

        System.out.println(numberList);
        System.out.println();

        Sorter sort = new Sorter(numberList);

        long start = System.currentTimeMillis();

        sort.start();

        try {
            sort.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - start);
        System.out.println();

        System.out.println(sort.getSortedList());
        System.out.println();

        long start2 = System.currentTimeMillis();
        
        for (int i = 0; i < numberList.size(); i++) {
            for (int j = 0; j < numberList.size(); j++) {

                if (j < numberList.size() -1) {

                    int n1 = numberList.get(j), n2 = numberList.get(j+1);
    
                    if (n1 > n2) {
                        numberList.remove(j);
                        numberList.add(j, n2);
                        
                        numberList.remove(j+1);
                        numberList.add(j+1, n1);
                    }
    
                }

            }
        }

        System.out.println(System.currentTimeMillis() - start2);
        System.out.println(numberList);
        System.out.println();

    }
}