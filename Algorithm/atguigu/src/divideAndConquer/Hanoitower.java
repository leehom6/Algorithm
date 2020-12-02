package divideAndConquer;

public class Hanoitower {
    public static int count;//一共移动了多少步-----计数器
    public static void main(String args[]){
        count = 0;
        System.out.println("印度神话传说汉诺塔问题的解决：");
        hanoitower(64,'A','B','C');
    }

    /**
     *分治算法----------汉诺塔问题
     * 分治算法的思想精髓在于如何进行问题的分解：将一个大规模的问题分解为和原问题相似的（解决思路相同的小规模的问题）
     * @param num  需要进行移动的盘的数量
     * @param a    从哪个塔出发的
     * @param b    辅助用的塔
     * @param c    要转移到的塔
     */
    public static void hanoitower(int num,char a,char b,char c){
        if(num==1){
            count++;
            //当只有一个盘时
            System.out.println("第"+count+"步：将一个盘从塔"+a+"移动到塔"+c);
            return;
        }
        if(num>=2){
            //当需要进行移动的盘数不止一个时，分为以下三步进行
            //1.将除了最后一个盘意外的上面的所有盘移动到辅助盘b
            hanoitower( (num-1),a,c,b);
            //2.将最下面的盘移动到塔c
            hanoitower(1,a,b,c);
            //3.将b上的num-1个盘借助塔a移动到塔c
            hanoitower(num-1,b,a,c);
        }
    }
}
