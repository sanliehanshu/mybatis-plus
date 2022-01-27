package com.windsun.wangs.thread;

import java.util.*;
import java.util.concurrent.*;

/**
 * @ClassName : Ticket
 * @Description :
 * @Author : ws
 * @Date: 2022-01-13 16:15
 * @Version 1.0
 */
public class Ticket implements Callable<Map> {

    SellTicket ticket = new SellTicket();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService ser = Executors.newFixedThreadPool(3);
        List list = new ArrayList<>();
        Ticket testCallable = new Ticket();
        for (int i = 0; i < 500; i++) {
            Thread.sleep(1);
            Future<Map> s1 = ser.submit(testCallable);
            Thread.sleep(1);
            Map map = s1.get();
            list.add(map);
        }
        ser.shutdown();
        Map<String, Integer> resultMap = new HashMap<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();


        for (int i = 0; i < list.size(); i++) {
            Map<String, Integer> map = (Map) list.get(i);
            for (Map.Entry maps : map.entrySet()) {
                if (maps.getKey().equals("pool-1-thread-1")) {
                    list1.add(map.get("pool-1-thread-1"));
                }
                if (maps.getKey().equals("pool-1-thread-2")) {
                    list2.add(map.get("pool-1-thread-2"));
                }
                if (maps.getKey().equals("pool-1-thread-3")) {
                    list3.add(map.get("pool-1-thread-3"));
                }
            }
        }

        System.out.println(list);
        resultMap.put("pool-1-thread-1", list1.size());
        resultMap.put("pool-1-thread-2", list2.size());
        resultMap.put("pool-1-thread-3", list3.size());
        System.out.println(resultMap);
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Map<String, Integer> call() throws Exception {
        for (int i = 0; i < 500; i++) {
            return ticket.sale();
        }
        return null;
    }
}

class SellTicket {
    int count = 100;

    public synchronized Map sale() {
        if (count > 0) {
            Map map = new HashMap();
            System.err.println(Thread.currentThread().getName() + "  卖出了第 " + count-- + "号，剩下：" + count);
            map.put(Thread.currentThread().getName(), count);
            return map;
        }
        return new HashMap();
    }

}
