package Util;

/**
 * @author hx
 * @version 1.0
 * @date 2025/10/13 1:16
 */
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class SSQSmartPicker {

    private static final String HISTORY_CSV = "D:\\CODE\\HXSC\\HXUtil\\src\\main\\resources\\ssq_history.csv";
    private static final int RED_COUNT = 6;
    private static final int BLUE_MAX = 16;

    public static void main(String[] args) throws Exception {
        List<Draw> history = loadHistory(HISTORY_CSV);
        System.out.println("已加载历史数据：" + history.size() + " 期");

        Set<String> historySet = history.stream()
                .map(Draw::toKey)
                .collect(Collectors.toSet());

        Map<Integer, Integer> redFreq = new HashMap<>();
        Map<Integer, Integer> blueFreq = new HashMap<>();
        for (int i = 1; i <= 33; i++) redFreq.put(i, 0);
        for (int i = 1; i <= 16; i++) blueFreq.put(i, 0);

        for (Draw d : history) {
            for (int r : d.reds) redFreq.put(r, redFreq.get(r) + 1);
            blueFreq.put(d.blue, blueFreq.getOrDefault(d.blue, 0) + 1);
        }

        // 热号冷号划分
        List<Integer> hotReds = redFreq.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(15).map(Map.Entry::getKey).collect(Collectors.toList());

        List<Integer> coldReds = redFreq.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(15).map(Map.Entry::getKey).collect(Collectors.toList());

        Random random = new Random();
        System.out.println("\n推荐号码（避开历史组合，冷热结合、奇偶平衡、分区均匀）:");
        int generated = 0;
        int attempts = 0;

        while (generated < 1000 && attempts < 5000) {
            attempts++;
            List<Integer> candidate = new ArrayList<>();

            // 3 热 + 3 冷
            Collections.shuffle(hotReds);
            Collections.shuffle(coldReds);
            candidate.addAll(hotReds.subList(0, 3));
            candidate.addAll(coldReds.subList(0, 3));

            Collections.sort(candidate);

            // 检查奇偶平衡
            long even = candidate.stream().filter(n -> n % 2 == 0).count();
            if (even < 2 || even > 4) continue;

            // 检查分区平衡
            long low = candidate.stream().filter(n -> n <= 11).count();
            long mid = candidate.stream().filter(n -> n >= 12 && n <= 22).count();
            long high = candidate.stream().filter(n -> n >= 23).count();
            if (low == 0 || mid == 0 || high == 0) continue;

            int blue = random.nextInt(BLUE_MAX) + 1;

            Draw newDraw = new Draw(candidate, blue);
            if (historySet.contains(newDraw.toKey())) continue;

            System.out.printf("红球: %s 蓝球: %02d%n", candidate, blue);
            generated++;
        }
    }

    // 加载历史数据
    static List<Draw> loadHistory(String path) throws IOException {
        List<Draw> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
            br.readLine(); // 跳过表头
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length < 8) continue;
                List<Integer> reds = new ArrayList<>();
                for (int i = 1; i <= 6; i++) reds.add(Integer.parseInt(p[i]));
                int blue = Integer.parseInt(p[7]);
                list.add(new Draw(reds, blue));
            }
        }
        return list;
    }

    // 代表一期开奖结果
    static class Draw {
        List<Integer> reds;
        int blue;

        Draw(List<Integer> reds, int blue) {
            this.reds = new ArrayList<>(reds);
            this.blue = blue;
        }

        String toKey() {
            return reds.stream().sorted()
                    .map(n -> String.format("%02d", n))
                    .collect(Collectors.joining("-")) + "-" + String.format("%02d", blue);
        }
    }
}
