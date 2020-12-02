package kmp;

public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "我们的路上不需要迷惘";
        String str2 = "上不上";
        int index = violencematch(str1,str2);
        if(index == -1){
            System.out.println("匹配失败！");
        }else{
            System.out.println("匹配成功，匹配的起始位置："+index);
        }
    }

    /**
     *
     * @param str1 这里的str1是一个源字符串
     * @param str2 这里的str2是一个目标字符串
     * @return 返回的是匹配成功的的位置下标起始位置
     */
    public static int violencematch(String str1, String str2) {
        char[] strArr1 = str1.toCharArray();
        char[] strArr2 = str2.toCharArray();
        int i = 0;//str1的索引
        int j = 0;//str2的索引
        while (i < str1.length() && j < str2.length()) {
            if (strArr1[i] == strArr2[j]) {
                //如果索引出的字符相同，比较下一位
                i++;
                j++;
            }else{
                //如果索引处不同，匹配失败，索引回溯
                i = i - j+1;//这个地方很关键，i指针的回溯是将i前进的j步退回去，然后再向前走一步进行更新，重新开始比较
                j = 0;
            }
        }
        //结束循环有两种：1.匹配成功  2.已经全部遍历过，匹配失败
        if( j == str2.length()){
            //j刚好在str2的后面，说明str2已经在str1全部得到匹配,即字符串匹配成功
            return i - j;//返回匹配的起始位置
        }else{
            //匹配失败
            return -1;
        }
    }
}
