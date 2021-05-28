package dataStructure.sparsearray;

public class SparseArray {

    public static void main(String[] args)
    {
        //set chess initial
        int chessArr1[][]=new int[11][11];
        chessArr1[1][2]=1;
        chessArr1[2][4]=2;
        chessArr1[5][10]=9;
        //print initial chess map
        System.out.println("print initial chess map:");
        for(int[] row: chessArr1){
            for(int data: row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        //transfer chess map to sparse array
        int sum=0;
        for(int row=0; row<chessArr1.length; row++){
            for(int col=0;col<chessArr1.length;col++){
                if(chessArr1[row][col]!=0){
                    sum++;
                }
            }
        }
        //create an sparse array
        int sparseArray[][]=new int[sum+1][3];
        //set data to sparse array
        sparseArray[0][0]=chessArr1.length;
        sparseArray[0][1]=chessArr1.length;
        sparseArray[0][2]=sum;
        //search for chess map again to get data which is not equal to zearo
        int count=0;
        for(int row=0; row<chessArr1.length; row++){
            for(int col=0;col<chessArr1.length;col++){
                if(chessArr1[row][col]!=0){
                    count++;
                    sparseArray[count][0]=row;
                    sparseArray[count][1]=col;
                    sparseArray[count][2]=chessArr1[row][col];
                }
            }
        }
        //print sparse array
        System.out.println("chess map -> sparse array:");
        for(int row=0; row<sparseArray.length; row++){
            System.out.printf("%d\t%d\t%d\t\n",sparseArray[row][0],sparseArray[row][1],sparseArray[row][2]);
        }
        //recover sparse array to chess map
        int chessArr2[][]=new int[sparseArray[0][0]][sparseArray[0][1]];
        System.out.println("sparse array -> chess map:");
        for(int count2=1; count2< sparseArray.length; count2++){
            chessArr2[sparseArray[count2][0]][sparseArray[count2][1]]=sparseArray[count2][2];
        }
        for(int[] row: chessArr2){
            for(int data: row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}
