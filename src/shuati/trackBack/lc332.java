package shuati.trackBack;

import java.util.*;

public class lc332 {

    // 邻接表，但是元素本身是有排序的
    // 外层map适用于根据from找对应的to地点列表
    // 内层map时to地点列表，根据题目要求要按字典顺序排序，
    //  采用treemap结构迭代时默认字符升序排列。注意可能存在起始点相同的机票，这里用int值标记数量
    private Map<String, Map<String, Integer>> map = new HashMap<>();

    // 存放已选取的地点
    private Deque<String> deque = new LinkedList<>();

    // 记录已使用的票数
    private int count = 0;

    public List<String> findItinerary(List<List<String>> tickets) {

        //初始化邻接表
        for (List<String> ticket : tickets) {
            if (!map.containsKey(ticket.get(0))) {
                map.put(ticket.get(0), new TreeMap<>());

            }
            Map<String, Integer> treeMap = map.get(ticket.get(0));
            if (treeMap.containsKey(ticket.get(1))) {
                treeMap.put(ticket.get(1), treeMap.get(ticket.get(1)) + 1);
            } else {
                treeMap.put(ticket.get(1), 1);
            }
        }

        // 填充起始点
        deque.add("JFK");
        backTrack(tickets.size(), "JFK");
        return new ArrayList<>(deque);
    }

    private boolean backTrack(int allTicketsNum, String from) {
        if (count == allTicketsNum) {
            return true;
        }

        if (map.containsKey(from)) {
            for (Map.Entry<String, Integer> to : map.get(from).entrySet()) {
                if (to.getValue() > 0) {
                    deque.addLast(to.getKey());
                    count++;
                    to.setValue(to.getValue() - 1);
                    if (backTrack(allTicketsNum, to.getKey())) {
                        return true;
                    }
                    deque.removeLast();
                    count--;
                    to.setValue(to.getValue() + 1);
                }
            }
        }

        return false;

    }

//    public static void main(String[] args) {
//        List<List<String>> tickets = new ArrayList<>();
//        tickets.add(Arrays.asList("EZE","AXA"));
//        tickets.add(Arrays.asList("TIA","ANU"));
//        tickets.add(Arrays.asList("ANU","JFK"));
//        tickets.add(Arrays.asList("JFK","ANU"));
//        tickets.add(Arrays.asList("ANU","EZE"));
//        tickets.add(Arrays.asList("TIA","ANU"));
//        tickets.add(Arrays.asList("AXA","TIA"));
//        tickets.add(Arrays.asList("TIA","JFK"));
//        tickets.add(Arrays.asList("ANU","TIA"));
//        tickets.add(Arrays.asList("JFK","TIA"));
//        List<String> itinerary = findItinerary(tickets);
//        System.out.println(itinerary);
//    }

}
