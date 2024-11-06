import java.util.ArrayList;
import java.util.List;

public class Sorter extends Thread {

    private List<Integer> unsortedList;
    private List<Integer> sortedList;

    public Sorter(List<Integer> list) {
        this.unsortedList = new ArrayList<>(list);
    }

    @Override
    public void run() {

        List<Integer> hereList;

        if (this.unsortedList.size() > 10) {
            hereList = divideList(this.unsortedList);
        } else {
            hereList = this.unsortedList;
        }

        for (int i = 0; i < hereList.size(); i++) {
            for (int j = i+1; j < hereList.size(); j++) {

                if (j <= hereList.size() -1) {

                    int n1 = hereList.get(i), n2 = hereList.get(j);
    
                    if (n1 > n2) {
                        hereList.remove(i);
                        hereList.add(i, n2);
                        
                        hereList.remove(j);
                        hereList.add(j, n1);
                    }
    
                }

            }
        }
             
        this.sortedList = hereList;
        
    }

    private List<Integer> divideList(List<Integer> list) {
        int index = list.size() /2;

        List<Integer> list1 = new ArrayList<>(list.subList(0, index));
        List<Integer> list2 = new ArrayList<>(list.subList(index, list.size()));

        Sorter sort1 = new Sorter(list1);
        Sorter sort2 = new Sorter(list2);

        sort1.start();
        sort2.start();

        try {
            sort1.join();
            sort2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Integer> joinedLists = new ArrayList<>(sort1.getSortedList());
        joinedLists.addAll(sort2.getSortedList());

        return joinedLists;
    }

    public List<Integer> getSortedList() {
        return sortedList.isEmpty() ? null : this.sortedList;
    }

}
