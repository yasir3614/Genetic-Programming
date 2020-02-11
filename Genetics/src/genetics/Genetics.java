
package genetics;

import static java.lang.Math.abs;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import javax.swing.JFrame;



public class Genetics {
    
    
    
    ////////////////
    
    static class crossovergene{
    public int [] array1;
    public int [] array2;
    
    }
    
    
    //Function for crossOver of two arrays
    private static crossovergene crossOver(int[] geneA, int[] geneB, int pos) {
        crossovergene obj = new crossovergene();
        
        int [] x = geneA;
        int [] y = geneB;

        
        for (int i = pos; i < geneA.length; i++) {
            int temp = geneA[i];
            geneA[i] = geneB[i];
            geneB[i] = temp;
        }

        
        obj.array1 = geneA;
        obj.array2 = geneB;
       
            
        return obj;

    }
    
    
    //Function to generate a random population array of 8 queens.
    public static int[] generatePopulation(){
       Random r = new Random();
       int[ ] array = new int[8];
       for(int i =0;i<8;i++){
           array[i]=r.nextInt(8) + 1;;
       }

        return array;
    }
    
    
    //Min Value Return
    public static int getMinValue(int[] array) {
    int minValue = array[0];
    for (int i = 1; i < array.length; i++) {
        if (array[i] < minValue) {
            minValue = array[i];
        }
    }
    return minValue;
}
    
    
    //Max Value return
    public static int getMaxValue(int[] array) {
    int minValue = array[0];
    for (int i = 1; i < array.length; i++) {
        if (array[i] > minValue) {
            minValue = array[i];
        }
    }
    return minValue;
}
    
    
    
    public static void printQueens(int[] arr){
        
        //Queens 8x8 
        char Queens[][]={
                    {'.','.','.','.','.','.','.','.'},
                    {'.','.','.','.','.','.','.','.'},
                    {'.','.','.','.','.','.','.','.'},
                    {'.','.','.','.','.','.','.','.'},
                    {'.','.','.','.','.','.','.','.'},
                    {'.','.','.','.','.','.','.','.'},
                    {'.','.','.','.','.','.','.','.'},
                    {'.','.','.','.','.','.','.','.'},
                           
            };
        
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++)
            {
                Queens[i][j]='*';
            }
        }
        
            for(int i=1;i<=8;i++){
                Queens[arr[i-1]-1][i-1] = 'q';
            }
        
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++)
            {
                System.out.print(Queens[i][j]+ " ");
            }
            System.out.println("");
        }
        
    }
    
    //function to calculate clashes in a 8x8 queen array
    public static int calculateConflict(int[] array){
         
       int totalConflicts = 0;
       int conflict = 0;
       char Queens[][]={
                    {'.','.','.','.','.','.','.','.'},
                    {'.','.','.','.','.','.','.','.'},
                    {'.','.','.','.','.','.','.','.'},
                    {'.','.','.','.','.','.','.','.'},
                    {'.','.','.','.','.','.','.','.'},
                    {'.','.','.','.','.','.','.','.'},
                    {'.','.','.','.','.','.','.','.'},
                    {'.','.','.','.','.','.','.','.'},
                           
            };
       
        
            for(int i=1;i<=8;i++){
                Queens[array[i-1]-1][i-1] = 'q';
            }
            
        
        
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
            {
                if(Queens[i][j]=='.'){
                    Queens[i][j]='*';
                }
            }

        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                  conflict=0;
                if(Queens[i][j]=='q'){
                
                int a = i;
                int b = j;
                    
                //Left (Row Wise)
                      for(int left=j-1;left>=0;left--){
                        if(Queens[i][left]=='q'){
                            conflict++;
                        }
                    }
                    
                    
                //Right ( Row Wise )      
                        for(int right=j+1;right<8;right++)
                        {
                               if(Queens[i][right]=='q'){
                                conflict++;
                            }
                        }

                    
                    
                    
                //Diagonal Left and Right        
                for(int k=0;k<8;k++){
                    for(int p=0;p<8;p++){
                        if(k!=i && j!=p && Queens[k][p]=='q'){
                            float m = abs(i-k);
                            float n = abs(j-p);
                            
                            if(m==n){
                            conflict++;
                            }
                        }
                    }
                }
                    //Total Number Of Conflicts for each queen
                    totalConflicts+=conflict;

                }
            }
            
        }

          return totalConflicts;
    }
    
    
    //Mutation Function for a single array
    public static int[] mutate(int[] arr){
    
        /*If there is a repeated value in a array of queens, flip the mutated value to some other.*/
    Random r = new Random();
    
    int x=r.nextInt(8) + 1;
    for (int i=0;i<8;i++){
        for(int j=0;j<8;j++){
            if(i!=j && arr[i]==arr[j]){
                while(x==arr[i])
                {
                    x= r.nextInt(8) + 1;
                }
//                System.out.println("here in");
                arr[i]=x;
                
//                System.out.println("here mutat "+Arrays.toString(arr));
                return arr;
            }
        }
    }
    
//    System.out.println("---here--");
    int randomIndex =r.nextInt(7) ;
    int randomValue =r.nextInt(8-1+1) + 1;
    
    arr[randomIndex] = randomValue;
    return arr;
    
    }
    

    
    public static void main(String[] args) {
        
        int generations  = 0 ;
        char Queens[][]={
                    {'.','.','.','.','.','.','.','.'},
                    {'.','.','.','.','.','.','.','.'},
                    {'.','.','.','.','.','.','.','.'},
                    {'.','.','.','.','.','.','.','.'},
                    {'.','.','.','.','.','.','.','.'},
                    {'.','.','.','.','.','.','.','.'},
                    {'.','.','.','.','.','.','.','.'},
                    {'.','.','.','.','.','.','.','.'},
                           
            };

        
        //Generating Population
        int[] population1 = generatePopulation();
        int[] population2 = generatePopulation();
        int[] population3 = generatePopulation();
        int[] population4 = generatePopulation();
//      int[] p5 = generatePopulation();
        
        boolean x = false;
        
         while(x!=true){
           
           System.out.println("\n\n------Generation #: "+generations + "-------------------");
           
        System.out.println("-------Current Population--------");
        System.out.println(Arrays.toString(population1));
        System.out.println(Arrays.toString(population2));
        System.out.println(Arrays.toString(population3));
        System.out.println(Arrays.toString(population4));
             System.out.println("");
    
        //Calculating Conflicts in each population 
        int p1c = calculateConflict(population1); 
        int p2c = calculateConflict(population2);
        int p3c = calculateConflict(population3);
        int p4c = calculateConflict(population4);
       
        
        int conflicts[] = {p1c,p2c,p3c,p4c};
        
        int [] fitnessPercentage = {0,0,0,0};
        
        
       //Calculating Fitness Functions for each population
        for(int i=0;i<4;i++){
            fitnessPercentage[i] =28-conflicts[i];
        }
        
        int minPercentage = getMinValue(fitnessPercentage);
        int maxPercentage = getMaxValue(fitnessPercentage);
       
        
        //Selecting all populations except the one having the maximum conflicts
        int [] selection1 = new int[8]; 
        int [] selection2 = new int[8] ;
        int [] selection3 = new int[8];
        int [] selection4 = new int[8];
        
        for(int i=0;i<fitnessPercentage.length;i++){
            if(fitnessPercentage[i]==minPercentage){
               if(i+1==1){
                   if(fitnessPercentage[1]==maxPercentage){
                       selection4=population2;
                   }
                   if(fitnessPercentage[2]==maxPercentage){
                       selection4=population3;
                   }if(fitnessPercentage[3]==maxPercentage){
                       selection4=population4;
                   }
                    selection1=population2;
                    selection2=population3;
                    selection3=population4;
      
                            
                }
                if(i+1==2){
                     if(fitnessPercentage[0]==maxPercentage){
                       selection4=population1;
                   }
                   if(fitnessPercentage[2]==maxPercentage){
                       selection4=population3;
                   }if(fitnessPercentage[3]==maxPercentage){
                       selection4=population4;
                   }
                    selection1=population4;
                    selection2=population3;
                    selection3=population1;

                }
                if(i+1==3){
                     if(fitnessPercentage[0]==maxPercentage){
                       selection4=population1;
                   }
                   if(fitnessPercentage[1]==maxPercentage){
                       selection4=population2;
                   }if(fitnessPercentage[3]==maxPercentage){
                       selection4=population4;
                   }
                    selection1=population4;
                    selection2=population2;
                    selection3=population1;

                }
                if(i+1==4){
                     if(fitnessPercentage[0]==maxPercentage){
                       selection4=population1;
                   }
                   if(fitnessPercentage[1]==maxPercentage){
                       selection4=population2;
                   }if(fitnessPercentage[2]==maxPercentage){
                       selection4=population3;
                   }
                    selection1=population2;
                    selection2=population3;
                    selection3=population1;

                }
                
               
            }
        }
        
        //Selection of Population
             System.out.println("-------Selection--------");
        System.out.println(Arrays.toString(selection1));
        System.out.println(Arrays.toString(selection2));
        System.out.println(Arrays.toString(selection3));
        System.out.println(Arrays.toString(selection4));
             System.out.println("");
        
        //CROSSOVER Between selections
        
        Random r = new Random();
        int randomIndex = r.nextInt(8+1)+1;
        int randomMutation = r.nextInt(8+1)+1;
        
        int [] c1 = new int[8];
        int [] c2 = new int[8];
        int [] c3 = new int[8];
        int [] c4 = new int[8];
        
        
        int cross=r.nextInt(6-3+1)+3;
        
        crossovergene gene1  = crossOver(selection1,selection2,2);
        
        randomIndex = r.nextInt(8+1)+1;
        crossovergene gene2  = crossOver(selection3,selection4,3);
        
        

        c1 = gene1.array1;
        c2 = gene1.array2;
        c3 = gene2.array1;
        c4 = gene2.array2;

        System.out.println("-------Crossover--------");
        System.out.println(Arrays.toString(c1));
        System.out.println(Arrays.toString(c2));
        System.out.println(Arrays.toString(c3));
        System.out.println(Arrays.toString(c4));
             System.out.println("");

        //Mutations
        c3=mutate(c3);
        c4=mutate(c4);
       
        System.out.println("-------Mutation--------");
        System.out.println(Arrays.toString(population1));
        System.out.println(Arrays.toString(population2));
        System.out.println(Arrays.toString(population3));
        System.out.println(Arrays.toString(population4));
             System.out.println("");
             System.out.println("***********************************************");

        population1 = c1;
        population2 = c2;
        population3 = c3;
        population4 = c4;
         
        //The generations stop once once the total conflicts are zero
        if(calculateConflict(population1)==0 || calculateConflict(population2)==0 || calculateConflict(population3)==0 || calculateConflict(population4)==0  ){
            
            System.out.println("@@@@@@@@@@@@@@@@@@@");
            System.out.println("Found Solution!");
            System.out.println("Total Generations: "+generations);
            System.out.println("Found Solution!");
            System.out.println(Arrays.toString(population1));
            System.out.println("Total Conflicts: "+calculateConflict(population1));
            System.out.println("Queens On Board:");
            
            printQueens(population1);
           
               System.out.println("@@@@@@@@@@@@@@@@@@@");
            
            x = true;
        }
           
           generations++;
    }}
    
    JFrame newframe = new JFrame();
    
}
    
        