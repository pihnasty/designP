package aws.seach;

import java.util.ArrayList;
import java.util.List;

public class BinarySeach {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<100; i++) {
            list.add(i);
        }
        int m = biseach(list,80);
        System.out.println(m);
        m = biseachRecursive(list, 80,0, list.size()-1);
        System.out.println(m);
    }


    public static int biseach(List<Integer>list, Integer value) {
        int f = 0;
        int e = list.size();
        int m=0;
       do {
            switch(value.compareTo(list.get(m))) {
                case -1: e = m-1;
                    break;
                case 1: f = m+1;
                    break;
               default: return m;
            }
           m = (f + e) / 2;
        } while (f!=e);
      return -1;
    }

    public static int biseachRecursive(List<Integer>list, Integer value,int f1, int e1) {
        if (e1-f1==0)
            return -1;
        int f = f1;
        int e = e1;
        int m = (f + e) / 2;
        switch(value.compareTo(list.get(m))) {
            case -1: e=m;
                break;
            case 1: f=m;
                break;
            default:
                return m;
        }
        return biseachRecursive(list, value,f, e);
    }
}
//
//    public static int recursiveBinarySearch(int arr[], int firstElement, int lastElement, int elementToSearch) {
//
//        // условие прекращения
//        if (lastElement >= firstElement) {
//            int mid = firstElement + (lastElement - firstElement) / 2;
//
//            // если средний элемент - целевой элемент, вернуть его индекс
//            if (arr[mid] == elementToSearch)
//                return mid;
//
//            // если средний элемент больше целевого
//            // вызываем метод рекурсивно по суженным данным
//            if (arr[mid] > elementToSearch)
//                return recursiveBinarySearch(arr, firstElement, mid - 1, elementToSearch);
//
//            // также, вызываем метод рекурсивно по суженным данным
//            return recursiveBinarySearch(arr, mid + 1, lastElement, elementToSearch);
//        }
//
//        return -1;
//    }
