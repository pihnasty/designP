package aws;

import com.sun.mail.imap.protocol.Item;
import net.snowflake.client.jdbc.internal.joda.time.DateTime;


import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

class Order {
    Item item;
    DateTime time;
}

interface Tracker {
    Integer addOrder(int costomerId, Order order);
    List<Order> getOrder(int costomerId);
    int getCostomerId(Order order);
    List<Order> getRange(DateTime from, DateTime to);

}

public  class ImplTracker implements Tracker {
    TreeMap<MyOrder, Integer> orders = new TreeMap();

    @Override
    public Integer addOrder(int costomerId, Order order){
        return orders.put((MyOrder) order,  costomerId);
    }

    @Override
    public List<Order> getOrder(int costomerId) {

        return orders.entrySet().stream().map(entry -> entry.getKey()).collect(Collectors.toList());
    }
    @Override
    public int getCostomerId(Order order) {
        return orders.get(order);
    }
    @Override
    public List<Order> getRange(DateTime from, DateTime to){
        List<Order> orderList = new ArrayList();

        Order orFrom = new Order();
        orFrom.time = from;
        Order orTo = new Order();
        orTo.time = to;


        orders.keySet().stream().filter(
                order ->
                    (order.compareTo(orFrom) * order.compareTo(orTo)) < 1
                )
                .forEach(order -> orderList.add(order));

        return orderList;

    }

    public class MyOrder extends Order implements Comparable {
        public MyOrder(DateTime time) {
            this.time = time;
        }
        @Override
        public int compareTo(Object order) {
            return  this.time.compareTo(((Order)order).time);
        }
    }


    public static void main(String[] args) {

        ImplTracker tracker = new  ImplTracker();

        Order ord1 =  ((ImplTracker)tracker).new MyOrder(new DateTime(1990,1,1,1,1));

        Order ord2 =  ((ImplTracker)tracker).new MyOrder(new DateTime(1989,1,1,1,1));


        Order ord3 =  ((ImplTracker)tracker).new MyOrder(new DateTime(1988,1,1,1,1));

        Order ord4 =  ((ImplTracker)tracker).new MyOrder(new DateTime(1987,1,1,1,1));

        Order ord5 =  ((ImplTracker)tracker).new MyOrder(new DateTime(1986,1,1,1,1));

        Order ord6 =  ((ImplTracker)tracker).new MyOrder(new DateTime(1985,1,1,1,1));

        Order ord7 =  ((ImplTracker)tracker).new MyOrder(new DateTime(1984,1,1,1,1));

        Order ord8 =  ((ImplTracker)tracker).new MyOrder(new DateTime(1983,1,1,1,1));

        Order ord9 =  ((ImplTracker)tracker).new MyOrder(new DateTime(1982,1,1,1,1));

        Order ord10 =  ((ImplTracker)tracker).new MyOrder(new DateTime(1981,1,1,1,1));

        tracker.addOrder(1,ord1);
        tracker.addOrder(2,ord2);
        tracker.addOrder(3,ord3);
        tracker.addOrder(6,ord6);
        tracker.addOrder(7,ord7);
        tracker.addOrder(8,ord8);
        tracker.addOrder(4,ord4);

        tracker.addOrder(5,ord5);

        tracker.addOrder(9,ord9);
        tracker.addOrder(10,ord10);




        System.out.println( tracker.getCostomerId(ord5));

        tracker.getRange(new DateTime(1983,1,1,1,1), new DateTime(1983,1,1,1,1))
                .forEach( order ->
                        System.out.println(order.time));


        System.out.println(tracker.getOrder(2).get(0).time);


    }


}
