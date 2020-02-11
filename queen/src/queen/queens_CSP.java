package queen;
/*
N-Queens problem: There is a n x n grid where the value of n is queens. Your task is to place queens queens on this board.
As per the rules of chess, a queen should have no other queen in its respective column,
neither it should have any other queen in its row nor it should have any within its diagonal cells.
You can consider this case as placing each queen individually per column such that it does not violate any of the
constraints mentioned. It’s quite easy to find solution manually but your task is now to code it and find the correct positions
for the queens.
This problem has to be solved through the concept of CSP. So initially you will place the
first queen randomly at any location within column 1. With respect to its location,
now next 7 queens’ domain might shrink up. This is where the concept of consistency will get applied. Further up,
when we will move forward, there might be a point where domain gets empty for any particular queen,
at that instance apply backtrack concept, which means that location of previous queen will have to get changed.
You can use any other logic too involving CSP concept.
You are free to use any language. Comments your code where necessary to make it clean and clear.


*/



import static java.lang.System.exit;
class nQueenCSP{
//  Domains of Variables Q1-Qqueens  
    String Q1[]= {"(0 0)","(1 0)","(2 0)","(3 0)","(4 0)","(5 0)" ,"(6 0)","(7 ,0)"};	
    String Q2[]= {"(0 1)","(1 1)","(2 1)","(3 1)","(4 1)","(5 1)" ,"(6 1)","(7 ,1)"};	
    String Q3[]= {"(0 2)","(1 2)","(2 2)","(3 2)","(4 2)","(5 2)" ,"(6 2)","(7 ,2)"};	
    String Q4[]= {"(0 3)","(1 3)","(2 3)","(3 3)","(4 3)","(5 3)" ,"(6 3)","(7 ,3)"};	
    String Q5[]= {"(0 4)","(1 4)","(2 4)","(3 4)","(4 4)","(5 4)" ,"(6 4)","(7 ,4)"};	
    String Q6[]= {"(0 5)","(1 5)","(2 5)","(3 5)","(4 5)","(5 5)" ,"(6 5)","(7 ,5)"};	
    String Q7[]= {"(0 6)","(1 6)","(2 6)","(3 6)","(4 6)","(5 6)" ,"(6 6)","(7 ,6)"};	
    String Qqueens[]= {"(0 7)","(1 7)","(2 7)","(3 7)","(4 7)","(5 7)" ,"(6 7)","(7 ,7)"};	

    String[][] chess ;
    int column=0;
     int queens=8;
public void make_chess(){
    chess=new String[queens][queens];
    for(int i=0;i<queens;i++){
        for (int j=0;j<queens;j++){
            chess[i][j]="#";
        }
    }
}
public  void updatedConsts() 
{ 
    System.out.println("Domains of Variables Q1-Qqueens");
    for (int i = 0; i < queens; i++) { 
        for (int j = 0; j < queens; j++) {
            if(!chess[j][i].contains("!"))
             {  if(chess[j][i].equals("Q"))
                    System.out.print("Q"+(i+1)+"  (" + j+" "+i + ")\t checked");
                System.out.print("Q"+(i+1)+"  (" + j+" "+i + ")\t ");
             }
            }
       System.out.println(); 
    } 
  
}

public void ConstsAbove(int position,int col){
    int i,j=1,k=1;
            for (i = position-1; i >= 0; i--){
                if (chess[i][col].equals("!"+col))  
                {   
                    chess[i][col] = "#";
                }
                if(col-j>=0)
                {      
                    int ind=col-j;
                    if (chess[i][ind].equals("!"+col)){
                        chess[i][ind] = "#";     
                    }
                    j+=1;
                }
                 if((col+k)<queens)
                {    
                    if (chess[i][col+(k)].equals("!"+col))
                    {
                        chess[i][col+(k)] = "#";

                    }
                    k+=1;
                }
        }

}
public void ConstsBelow(int position,int col){
    int i,j=1,k=1;
     for (i = position+1; i < queens; i++){
            if (chess[i][col].equals("!"+col))
                chess[i][col] = "#";
            
                        if((col-j)>=0)
            {
                if (chess[i][col-(j)].equals("!"+col)){
                        chess[i][col-(j)] = "#";
                        
                }
                j+=1;
            }
            if((col+k)<queens)
            {
                if (chess[i][col+(k)].equals("!"+col)){
                    chess[i][col+(k)] = "#";                    
                }
                k+=1;
            }
            
        } 
}
public  void AddConsts(int position, int col) //After backtrack add the constraints back to domains of the queens
{
    int i=0,j=1,k=1;
        
    
        this.ConstsAbove(position, col);
        this.ConstsBelow(position, col);
            
        for (i = col-1; i >= 0; i--) 
        {   
            if (chess[position][i].equals("!"+col)) 
            {    
                chess[position][i] = "#";
            }
        }
        for (i = col+1; i < queens; i++)
            if (chess[position][i].equals("!"+col))
                    chess[position][i] = "#";

        
        
}

public  boolean checkDomain(int col)//checks for an domain is not empty 
{
    for(int i=0;i<queens;i++)
    {
        if(chess[i][col].equals("#")) //Space Available
            return true;
    }
    
    return false;
}
public int getPos(int col)
{
    for(int i=0;i<queens;i++) 
    {
        if(chess[i][col]=="Q")
        {
            chess[i][col]="#"; 
            return i;  
                      
        }
    }
    return 0;
}
 public  boolean checkQ( int col)//Checks for the queen in the column
{
    if(col>=1){
        for(int i=0;i<queens;i++){
            if(chess[i][col]=="Q")
                return true;
        }
        return false;
    }
    return true;
}   
  

public  void Backtrack( int col)
{
    int pos=this.getPos(col);   
    AddConsts(pos,col); //Add to domain if for given positions of that queen
    System.out.println("***************backtrack Q:"+col+"***********");
    System.out.println("*******************Updated Constraints***************");
    updatedConsts();
    boolean flag=false;
 
    for(int i=pos+1 ;i<queens;i++)    
    {
        if( chess[i][col]=="#" )
        {
            chess[i][col]="Q";
            RemoveConsts(i, col);
            flag=true;
            break;
        }
     flag=false;
    }
   
      if(pos==queens && checkQ( col+1)==false)
       {
           Backtrack( col-1);
           column=col-1;
      }
    
    if(flag==false)
    {
        if((col-1)>=0)  
        {    
                Backtrack(col-1);
                column=col-1;
        }
    }
    
}        
 public void RemoveAbove(int row,int col){
        
   int i=0,j=1,k=1;
 
        for (i = row-1; i >= 0; i--)
        {
                if (chess[i][col] != "Q" && !chess[i][col].contains("!")) 
                    chess[i][col] = ("!"+col);
                if((col-j)>=0)
                   {
                       if (chess[i][col-(j)] != "Q" && !chess[i][col-(j)].contains("!")){
                           chess[i][col-(j)] = ("!"+col);     
                       }
                       j+=1;
                   }
                if(col+k<queens)
                {    
                    if (chess[i][col+(k)] != "Q" && !chess[i][col+(k)].contains("!"))
                    {
                        chess[i][col+(k)] = ("!"+col);

                    }
                    k+=1;
                }
        }

 }
public void Removeblow(int row,int col){
    int i,j=1,k=1;
            for (i = row+1; i < queens; i++) 
            {
                if (chess[i][col] != "Q" && !chess[i][col].contains("!"))
                    chess[i][col] = ("!"+col);
                if(col-j>=0)
                {
                    if (chess[i][col-(j)] != "Q" && !chess[i][col-(j)].contains("!")){
                            chess[i][col-(j)] = ("!"+col);

                    }
                    j+=1;
                }

                if(col+k<queens)
                {
                    if (chess[i][col+(k)] != "Q" && !chess[i][col+(k)].contains("!"))
                    {
                        chess[i][col+(k)] = ("!"+col);
                    }
                    k++;
                }
            }

}
public  void RemoveConsts( int row, int col) //Removing the inconsistant domains of variables
{
   
        int i,j=1,k=1;

        this.RemoveAbove(row, col);
        this.Removeblow(row, col);
        for (i = col-1; i >= 0; i--) 
        {   
            if (chess[row][i] != "Q" && !chess[row][i].contains("!")) 
            {    
                chess[row][i] = ("!"+col);
            }
        }
        for (i = col+1; i < queens; i++){
            if (chess[row][i] != "Q" && !chess[row][i].contains("!"))
                    chess[row][i] = ("!"+col);
        }

}
 public boolean tempBack(boolean x){
        boolean flag=false;
        return flag;
 }

public  void eightQueen(){
        int r=0;
        System.out.println(column);
        boolean flag=false;
        while(column<queens)
        {
             for(r=0;r<queens;r++)
            {
                if(!checkQ((column-1)))//checking queen in previous column
                {
                    column=column-1;
                }
                else
                {
                     if(!chess[r][column].contains("!"))
                      {
                          chess[r][column]="Q";
                          flag=true;
                          break;
                      }
                      flag=false;
                }
            }
            if(flag==false)
            {
                System.out.println("******Backtrack Q:"+column+"*********");
                Backtrack(column-1);
            }
            if(r<queens){
                System.out.println(column+" "+r);
                RemoveConsts(r,column);
            }
            updatedConsts();
            column++;
        }
}
}
public class queens_CSP {
 
    
    public static void main(String[] args) {
        int queens=8;
        
        nQueenCSP x=new nQueenCSP();
        System.out.println("Variables");
        x.make_chess();
        x.updatedConsts();
        x.eightQueen();
        
        for(int i=0;i<queens;i++){
            for (int j=0;j<queens;j++)
                System.out.print(x.chess[i][j].charAt(0)+" ");
            System.out.println("");
        }
        
    }
    
}

