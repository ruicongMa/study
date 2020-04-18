package cn.mark.greedy;

import java.util.*;

/**
 * 贪心算法解决集合覆盖问题
 * 如何选择最少的广播台，让所有的地区都可以接收到信号
 * 注意：贪婪算法所得到的结果不一定是最优的结果(有时候会是最优解)，但是都是相对近似(接近)最优解的结果
 *
 * @author Mark
 * @create 2020/3/5
 */
public class GreedySetCoverDemo {

    public static void main(String[] args) {
        //定义广播台与地区的映射关系
        Map<String, Set<String>> broadcasts = new HashMap<>();

        //定义所有地区的集合
        Set<String> allAreas = new HashSet<>();

        //广播台k1覆盖的地区
        Set<String> areas = new HashSet<>();
        areas.addAll(Arrays.asList("北京", "上海", "天津"));
        broadcasts.put("K1", areas);
        allAreas.addAll(areas);

        //广播台k2覆盖的地区
        areas = new HashSet<>();
        areas.addAll(Arrays.asList("广州", "北京", "深圳"));
        broadcasts.put("K2", areas);
        allAreas.addAll(areas);

        //广播台k3覆盖的地区
        areas = new HashSet<>();
        areas.addAll(Arrays.asList("成都", "上海", "杭州"));
        broadcasts.put("K3", areas);
        allAreas.addAll(areas);

        //广播台k4覆盖的地区
        areas = new HashSet<>();
        areas.addAll(Arrays.asList("上海", "天津"));
        broadcasts.put("K4", areas);
        allAreas.addAll(areas);

        //广播台k5覆盖的地区
        areas = new HashSet<>();
        areas.addAll(Arrays.asList("杭州", "大连"));
        broadcasts.put("K5", areas);
        allAreas.addAll(areas);

        //存放可选择的广播台
        List<String> selectBroadcasts = new ArrayList<>();
        //定义maxKey，指向每次遍历完，能够覆盖最大未覆盖的广播电台
        String maxKey = null;
        //定义临时变量，存放每次广播电台覆盖的地区与所有地区的交集
        Set<String> tempSet = new HashSet<>();
        //只要allAreas不为空，循环找广播台
        while (!allAreas.isEmpty()) {
            //每次遍历清空
            maxKey = null;
            //遍历所有广播台，找出最多能覆盖地区的广播台
            for (String key : broadcasts.keySet()) {
                tempSet.clear();
                //取出广播台对应的覆盖地区集合
                Set<String> areaSets = broadcasts.get(key);
                tempSet.addAll(areaSets);
                //让tempSet与allAreas取交集
                tempSet.retainAll(allAreas);
                //体现贪心算法的思想，每次都选择最优的（如果当前广播电台最多覆盖未覆盖地区的个数大于上次广播电台覆盖的未覆盖地区的个数，则选择当前广播电台）
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            if (maxKey != null) {
                //将次maxKey加入到选择的广播电台中
                selectBroadcasts.add(maxKey);
                //将maxKey指向的覆盖地区从allAreas中移除掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        System.out.println("选则的最少广播电台集合为：" + selectBroadcasts);
    }
}
