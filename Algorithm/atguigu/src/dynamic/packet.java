package dynamic;

import java.util.Arrays;

public class packet {
    public static void main(String[] args) {
//        物品价值
        int[] price = {0, 500,1500, 3000, 2000};
//        背包重量
        int[] weight = {0, 1, 2, 4, 3};
        int n = 4;//可选物品种类数量
        int m = 5;//背包当前容量  
        packet(n, m, price, weight);
    }

    /**
     * 01背包问题：不重复放入物品情况下，背包可以装入的最大价值----动态规划DP
     * 我们在这里将最优策略的装入情况也展示出来：增加path[][]数组进行记录
     *
     * @param n      可选物品种类最大数量
     * @param m      背包最大容量
     * @param price  物品价值
     * @param weight 物品重量
     */
    public static void packet(int n, int m, int[] price, int[] weight) {
        //        在定义二维数组val，表示背包当前容量下的可以装入的最大价值
        int[][] val = new int[n + 1][m + 1];//有一行一列表示容量为0和可选物品种类为0，所以要多加1
        //        val数组的初始化
        for (int i = 0; i < val.length; i++) {
            val[i][0] = 0;
        }
        for (int j = 0; j < val[0].length; j++) {
            val[0][j] = 0;
        }
        System.out.println("初始化后的背包数组");
        for (int[] ints : val) {
            System.out.println(Arrays.toString(ints));
        }
        //定义path[][]数组用来记录背包在可选为i，容量为j时的动态装入情况,1为装入，0为不装入
        int[][] path = new int[n + 1][m + 1];

        //开始将我们的物品装进背包----dp
        //其中i，表示可选物品的数量，j表示背包的当前可用容量
        for (int i = 1; i < val.length; i++) {
            for (int j = 1; j < val[0].length; j++) {
                //1.如果现在的容量不足以装入新增加的物品，那么最优背包策略仍是此物品不可选时的策略
                if (j < weight[i]) {
                    val[i][j] = val[i - 1][j];
                } else {
                    //2.如果现在的容量足以装入新增加的物品，那么最优背包策略可能会发生改变
                    //必须将新物品装入背包中才能进行策略之间的寻优比较，否则新增加的物品没有意义
                    //将不包含此新物品时的最优策略和加入后的策略（该新物品的价值+背包剩余容量下不重复装入情况下的最大价值）进行比较
//                    val[i][j] = Math.max(val[i - 1][j], price[i] + val[i - 1][j - weight[i]]);
                    if (val[i - 1][j] >= price[i] + val[i - 1][j - weight[i]]) {
                        val[i][j] = val[i - 1][j];
                    } else {
                        val[i][j] = price[i] + val[i - 1][j - weight[i]];
                        path[i][j] = 1;
                    }
                }
            }
        }
        System.out.println("背包问题动态规划数组：");
        for (int[] ints : val) {
            System.out.println(Arrays.toString(ints));
        }

        //展示我们的背包动态装入的情况(在容量最大可选物品增多的情况下)
        //我们应该进行的是逆序查找，因为我们的最优背包策略是在最后得到的
        int i = val.length - 1;//最大行数
        int j = val[0].length - 1;//最大列数
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                //如果有放入新的物品
                System.out.println("放入第" + i + "件物品");
                j = j - weight[i];//剩余重量
            }
            i--;//剩余可选放入物品种类
        }
    }
}




