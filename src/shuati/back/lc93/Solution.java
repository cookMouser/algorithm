package shuati.back.lc93;

import java.util.ArrayList;
import java.util.List;

/**
 * LC: 93. Restore IP Addresses
 */
class Solution {

    private List<String> res = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        backTrack(s, 0, 0, "");
        return res;
    }

    /**
     *
     * @param s 原始字符
     * @param index 当前处理索引
     * @param count 已经添加的ip段的数量
     * @param ip ip字符
     */
    private void backTrack(String s, int index, int count, String ip) {
        if (count == 4 && index >= s.length()) {
            // 截取掉最后的"."
            ip = ip.substring(1, ip.length());
            res.add(ip);
            return;
        }

        if (count > 4 || index >= s.length()) {
            return;
        }

        char first = s.charAt(index);
        if (first == '0') {
            // 为0的话，当前ip段就只能为".0"
            backTrack(s, index + 1, count + 1, ip + ".0");
        } else {
            //一个字符为一段
            backTrack(s, index + 1, count + 1, ip + "." + first);
            if (index + 1 < s.length()) {
                backTrack(s, index + 2, count + 1, ip + "." + first + s.charAt(index + 1));
            }
            if (index + 2 < s.length()) {
                int sum = (first - '0') * 100 + (s.charAt(index + 1) - '0') * 10 + (s.charAt(index + 2) - '0');
                if (sum <= 255) {
                    backTrack(s, index + 3, count + 1, ip + "." + first + s.charAt(index + 1)+ s.charAt(index + 2));
                }
            }

        }
    }

}
