package Percolation;
public class Percolation{
    private final static int rootsite = 0;
    private int wholelength;
    private int n;
    private boolean[][] grid;
    private QuickFindUF unioninstance;
    public Percolation(int N){
        n = N;
        if(N <= 0)
            throw new IllegalArgumentException("N must be greater than 0");
        wholelength = N*N + 2;
        unioninstance = new QuickFindUF(wholelength);
        grid = new boolean[n][n];

    }
    public void open(int i, int j){
        if(isOverflow(i,j))
            throw new IndexOutOfBoundsException("i and j is overflow");
        if(isOpen(i,j))
            return;
        int id_one = number(i,j);
        grid[i-1][j-1] = true;
        if(i == 1)
            unioninstance.union(id_one,rootsite);
        if(i == n)
            unioninstance.union(id_one,n*n+1);
        if(!isOverflow(i-1,j)&&isOpen(i-1,j)){
            unioninstance.union(id_one,id_one-n);
        }
        if(!isOverflow(i,j+1)&&isOpen(i,j+1)){
            unioninstance.union(id_one,id_one+1);
        }
        if(!isOverflow(i+1,j)&&isOpen(i+1,j)){
            unioninstance.union(id_one,id_one+n);
        }
        if(!isOverflow(i,j-1)&&isOpen(i,j-1)){
            unioninstance.union(id_one,id_one-1);
        }
    }
    public boolean isOpen(int i, int j){
        if(isOverflow(i,j))
            throw new IndexOutOfBoundsException("i and j is overflow");
        if(!grid[i-1][j-1])
            return false;
        else
            return true;
    }
    public boolean percolates(){
        if(unioninstance.connected(n*n+1,rootsite))
            return false;
        else
            return true;
    }
    private boolean isOverflow(int i,int j){
        if((i>0&&i<=n)&&(j>0&&j<=n))
            return false;
        else
            return true;
    }
    private int number(int i,int j){
        int temp = n*(i-1) + j;
        return temp;
    }
    public static void main(String[] args) {

    }

}