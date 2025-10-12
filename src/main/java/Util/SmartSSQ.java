package Util;

/**
 * @author hx
 * @version 1.0
 * @date 2025/10/13 0:46
 */
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class SmartSSQ {

    public static void main(String[] args) {
        String filePath = "D:\\CODE\\HXSC\\HXUtil\\src\\main\\resources\\ssq_history.csv"; // 历史数据文件（同目录）
        List<int[]> redRecords = new ArrayList<>();
        List<Integer> blueRecords = new ArrayList<>();

        // 1. 读取CSV
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // 跳过表头
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 8) continue;
                int[] reds = new int[6];
                for (int i = 0; i < 6; i++) {
                    reds[i] = Integer.parseInt(parts[i + 1].trim());
                }
                redRecords.add(reds);
                blueRecords.add(Integer.parseInt(parts[7].trim()));
            }
        } catch (IOException e) {
            System.out.println("❌ 读取文件失败：" + e.getMessage());
            return;
        }

        // 2. 统计频率
        Map<Integer, Integer> redFreq = calcFrequency(redRecords, 33);
        Map<Integer, Integer> blueFreq = calcFrequencyBlue(blueRecords, 16);

        // 3. 输出热/冷号
        System.out.println("🔥 红球热号前10：");
        redFreq.entrySet().stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .limit(10)
                .forEach(e -> System.out.printf("红球 %02d：%d 次%n", e.getKey(), e.getValue()));

        System.out.println("\n❄️ 红球冷号前10：");
        redFreq.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .limit(10)
                .forEach(e -> System.out.printf("红球 %02d：%d 次%n", e.getKey(), e.getValue()));

        System.out.println("\n🎯 推荐号码（综合冷热号+奇偶+分布+和值）：");
        for (int i = 0; i < 1000; i++) {
            List<Integer> reds = generateSmartRedBalls(redFreq);
            int blue = generateSmartBlueBall(blueFreq);
            Collections.sort(reds);
            System.out.printf("第 %d 组：红球 %s 蓝球 %02d%n", i + 1, reds, blue);
        }
    }

    // -------- 工具方法 --------

    // 统计红球出现次数
    private static Map<Integer, Integer> calcFrequency(List<int[]> records, int max) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 1; i <= max; i++) freq.put(i, 0);
        for (int[] reds : records)
            for (int r : reds)
                freq.put(r, freq.get(r) + 1);
        return freq;
    }

    // 统计蓝球出现次数
    private static Map<Integer, Integer> calcFrequencyBlue(List<Integer> records, int max) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 1; i <= max; i++) freq.put(i, 0);
        for (int b : records)
            freq.put(b, freq.get(b) + 1);
        return freq;
    }

    // 智能生成红球（保证 6 个不重复、满足约束）
    private static List<Integer> generateSmartRedBalls(Map<Integer, Integer> freq) {
        Random rand = new Random();
        List<Integer> reds = new ArrayList<>();
        // 准备热、冷、全部号码列表
        List<Integer> hot = freq.entrySet().stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .limit(12)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        List<Integer> cold = freq.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .limit(12)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        List<Integer> all = new ArrayList<>(freq.keySet());

        int attempts = 0;
        final int MAX_ATTEMPTS = 200; // 尝试上限，避免死循环

        while (attempts++ < MAX_ATTEMPTS) {
            Set<Integer> pick = new HashSet<>();
            pick.addAll(randomPickUnique(hot, 3, rand));
            pick.addAll(randomPickUnique(cold, 2, rand));
            pick.addAll(randomPickUnique(all, 1, rand));

            // 如果不够 6 个（例如热/冷集合太小或重复），从 all 中补齐
            if (pick.size() < 6) {
                pick.addAll(randomPickUnique(all, 6 - pick.size(), rand));
            }

            List<Integer> candidate = new ArrayList<>(pick);
            // 调整奇偶比例（倾向 3:3 或 4:2）
            candidate = adjustOddEven(candidate, rand);

            // 校验分布与和值
            if (isDistributedWell(candidate) && isSumInRange(candidate, 80, 130) && !hasTooManyConsecutive(candidate)) {
                return candidate.stream().distinct().sorted().collect(Collectors.toList());
            }
            // 否则继续尝试
        }

        // 若超过尝试次数仍未找到合适组合，返回一个随机的安全组合（保证不重复）
        List<Integer> fallback = randomPickUnique(new ArrayList<>(freq.keySet()), 6, new Random());
        return fallback.stream().sorted().collect(Collectors.toList());
    }

    // 智能生成蓝球
    private static int generateSmartBlueBall(Map<Integer, Integer> freq) {
        Random rand = new Random();
        List<Integer> hot = freq.entrySet().stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        List<Integer> cold = freq.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // 70% 取热号，30% 取冷号
        if (rand.nextDouble() < 0.7 && !hot.isEmpty()) {
            return hot.get(rand.nextInt(hot.size()));
        } else if (!cold.isEmpty()) {
            return cold.get(rand.nextInt(cold.size()));
        } else {
            // 兜底：任意蓝球
            List<Integer> all = new ArrayList<>(freq.keySet());
            return all.get(rand.nextInt(all.size()));
        }
    }

    // 从 source 中不重复随机取 count 个元素（如果 count > source.size()，则返回全部并打乱）
    private static List<Integer> randomPickUnique(List<Integer> source, int count, Random rand) {
        List<Integer> copy = new ArrayList<>(source);
        Collections.shuffle(copy, rand);
        if (count >= copy.size()) {
            return new ArrayList<>(copy);
        } else {
            return copy.subList(0, count);
        }
    }

    // 调整奇偶使得更平衡（返回长度可能 >6 的候选，外部会去重与裁剪）
    private static List<Integer> adjustOddEven(List<Integer> candidate, Random rand) {
        // 保证最多 6 个元素
        List<Integer> list = new ArrayList<>(candidate);
        Collections.shuffle(list, rand);
        // 如果多于 6 个，先截取
        if (list.size() > 6) list = new ArrayList<>(list.subList(0, 6));

        long odd = list.stream().filter(n -> n % 2 == 1).count();
        long even = list.size() - odd;

        // 目标：奇偶比 3:3 或 4:2
        if (list.size() == 6) {
            if (odd >= 5) {
                // 替换部分奇数为偶数
                for (int i = 0; i < list.size() && odd > 4; i++) {
                    int v = list.get(i);
                    if (v % 2 == 1) {
                        // 随机找一个不在 list 中的偶数替换
                        int candidateEven = findReplacement(list, false, rand);
                        if (candidateEven != -1) {
                            list.set(i, candidateEven);
                            odd--;
                        }
                    }
                }
            } else if (even >= 5) {
                // 替换部分偶数为奇数
                for (int i = 0; i < list.size() && even > 4; i++) {
                    int v = list.get(i);
                    if (v % 2 == 0) {
                        int candidateOdd = findReplacement(list, true, rand);
                        if (candidateOdd != -1) {
                            list.set(i, candidateOdd);
                            even--;
                        }
                    }
                }
            }
        }
        // 最终去重并返回
        return list.stream().distinct().collect(Collectors.toList());
    }

    // 在 1..33 中找一个奇/偶替换值且不在 current 中
    private static int findReplacement(List<Integer> current, boolean needOdd, Random rand) {
        List<Integer> pool = new ArrayList<>();
        for (int i = 1; i <= 33; i++) {
            if ((i % 2 == 1) == needOdd && !current.contains(i)) pool.add(i);
        }
        if (pool.isEmpty()) return -1;
        return pool.get(rand.nextInt(pool.size()));
    }

    // 检查三个区间是否都有号码
    private static boolean isDistributedWell(List<Integer> reds) {
        Set<Integer> set = new HashSet<>(reds);
        long g1 = set.stream().filter(n -> n <= 11).count();
        long g2 = set.stream().filter(n -> n > 11 && n <= 22).count();
        long g3 = set.stream().filter(n -> n > 22).count();
        return g1 >= 1 && g2 >= 1 && g3 >= 1;
    }

    // 检查和值是否在区间
    private static boolean isSumInRange(List<Integer> reds, int min, int max) {
        int sum = reds.stream().mapToInt(Integer::intValue).sum();
        return sum >= min && sum <= max;
    }

    // 检查是否有超过 2 个连续号（通常不建议超过 2）
    private static boolean hasTooManyConsecutive(List<Integer> reds) {
        List<Integer> list = new ArrayList<>(new HashSet<>(reds));
        Collections.sort(list);
        int consecutive = 1;
        int maxConsecutive = 1;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) == list.get(i - 1) + 1) {
                consecutive++;
                if (consecutive > maxConsecutive) maxConsecutive = consecutive;
            } else {
                consecutive = 1;
            }
        }
        return maxConsecutive > 2; // 如果出现 3 个或以上连续号则认为过多
    }
}

