package dataStructure.recursion;

public class MazeRecursionProblem {
    public static void main(String[] args) {
        //先创建一个二维数组模拟迷宫
        int[][] map = new int[8][7];
        //使用1表示墙
        //先把上下置为1
        for (int col = 0; col < 7; col++) {
            map[0][col] = 1;
            map[7][col] = 1;
        }
        //左右全部置为1
        for (int row = 0; row < 8; row++) {
            map[row][0] = 1;
            map[row][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        //输出地图
        System.out.println("地图的情况:");
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 7; col++) {
                System.out.printf(map[row][col] + "\t");
            }
            System.out.println();
        }
        //使用递归回溯给小球找路
        setWay(map, 1, 1);
        //输出新的地图,小球走过,并标识过的地图
        System.out.println("小球走过,并标识过的地图情况:");
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 7; col++) {
                System.out.printf(map[row][col] + "\t");
            }
            System.out.println();
        }
    }

    //使用递归回溯来给小球找路

    /**
     * 说明:
     * 1.map表示地图
     * 2.row,col 表示从地图的哪个位置出发(1,1)
     * 3.如果小球能到达map[6][5],则说明通路找到
     * 4.约定:当map[row][col]为0 表示该点没有走过,当为1表示墙,2表示通路可以走,3表示该点已经走过但走不通
     * 5.在走迷宫时,需要确定一个策略(方法) 下->右->上->左,如果该点走不通再回溯
     *
     * @param map 表示地图
     * @param row 起点行坐标
     * @param col 起点列坐标
     * @return 如果找到通路返回true, 否则返回false
     */
    public static boolean setWay(int[][] map, int row, int col) {
        if (map[6][5] == 2) { //通路已经找到
            return true;
        } else {
            if (map[row][col] == 0) {//该点没有走过
                //按照策略 下->右->上->左
                map[row][col] = 2;//假定该点能够走通
                if (setWay(map, row + 1, col)) {//向下走
                    return true;
                } else if (setWay(map, row, col + 1)) {//向右走
                    return true;
                } else if (setWay(map, row - 1, col)) {//向上走
                    return true;
                } else if (setWay(map, row, col - 1)) {//向左走
                    return true;
                } else {
                    //说明该点是死路
                    map[row][col] = 3;
                    return false;
                }
            } else { //如果map[row][col]!=0,可能是1 2 3
                return false;
            }
        }
    }
}
