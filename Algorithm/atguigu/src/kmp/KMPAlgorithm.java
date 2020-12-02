package kmp;

import java.util.Arrays;
//妙不可言！！！！！
//kmp算法的精华：找到匹配部分的前缀后缀相同部分的最大长度，因为字符串匹配都是从头部开始要求全部按位匹配上，所以我们利用前后缀
//的相同部分可以在失配时，不必回溯源串的指针，因为回溯带来的匹配成功的情况必回导致前追缀后缀相同，已经全部包含在部分匹配表next中
//而且我们可以直接调整指针j，跳过前后缀相同部分直接比较下一位字符
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "aaabaaacacad";
        String str2 = "aaac";
        int[] next = kmpNext(str2);
        System.out.println(Arrays.toString(next));
        int res = kmpMatch(next, str2, str1);
        System.out.println(res);

    }

    /**
     * 在求得目标串的基础上进行字符串的kmp的匹配算法
     *
     * @param next 部分匹配表
     * @param dest 目标串
     * @param src  源串
     * @return 返回的是匹配的初始位置下标
     */
    public static int kmpMatch(int[] next, String dest, String src) {
        char[] destArr = dest.toCharArray();
        char[] srcArr = src.toCharArray();
        //对源串进行一个循环匹配
        int i = 0, j = 0;
        while (i < src.length() && j < dest.length()) {
            while (j > 0 && srcArr[i] != destArr[j]) {
                j = next[j - 1];
                //kmp的核心思想：取消源串指针的回溯---利用部分匹配表 ！！！！！
                //当进行到i和j出现失配，说明j前面是匹配上的部分，0~j-1个匹配到的目标串部分具有的前后缀相同部分，源串也同样具有
                //这时就代表着，源串的后缀可以变为前缀头继续与目标串进行匹配，而匹配的位置应该是从前后缀相同部分的长度next[j-1]进行
                //i不用动，j指向前缀头的下一位:destArr[next[j-1]] 表示前面next[j-1]个字符已经是相同的，直接比较下一位是否相同即可
            }
            if (srcArr[i] == destArr[j]) {
                j++;
            }
            i++;
        }
        if (j == dest.length()) {
            return i - j ;
        }else {
            return -1;
        }
    }


    /**
     * 部分匹配表
     * 为了指针的加速回溯，我们需要利用子串的已知信息，先对其有效信息进行提取再进行字符串的匹配（目标串的结构特点）
     *
     * @param dest 目标串
     * @return 返回一个部分匹配表：在当前达到的匹配长度下，最长的相同的前后缀
     */
    public static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];//部分匹配表,记录dest目标串的每一个位置的回溯帮助信息
        next[0] = 0;//匹配的字符串长度为1，由于只有一个字符所以没有相同的前后缀，next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //这里i代表目标串与源串匹配成功达到的目标的位置，也就是我们部分匹配能达到的位置
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                //当不符合时，根据部分匹配表进行回溯
                j = next[j - 1];
                //kmp的前后缀结构信息在这里再次体现当我们的i j失配时，说明i 和 j前面的0 ~j-1个字符是匹配上的
                //所以我们可以利用部分匹配表知道已经匹配上的部分的前后缀相同的长度：next[j-1]
                //这样后缀变成前缀头，也是可以直接进行第next[j-1]+1个元素的比较
                //只需要调整j，i指针同样不用进行回溯
            }
            if (dest.charAt(i) == dest.charAt(j)) {
                //如果相等，说明前后缀的当前相同部分的后面一个字符也是相同的，next[i]长度可以加1
                j++;
            }
            next[i] = j;//保存匹配表信息
        }
        return next;
    }
}