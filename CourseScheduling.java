
import java.util.*;
class CourseScheduling {
    public static void main(String[] args){
       
        int n=11;
        int[][] pre={{1,0},{3,1},{4,1},{5,1},{6,1},{2,1},{8,2},{9,8},{9,3},{7,6},{7,5},{7,4},{10,7},{7,9}};
     /*     for(int i=0;i<9;i++){
            for(int j=0;j<2;j++){
                pre[i][j]=sc.nextInt();
            }
        } */
        Find f=new Find();
        int[] res= f.OrderOfCourses(n,pre);
        if(res.length==1 && res[0]==-1) System.out.println("not possible to order the courses");
        for(int i=0;i<res.length;i++) {
            String s="";  //print the order of courses
            if(res[i]==0) s="computer basics";
            else if(res[i]==1) s="programming language";
            else if(res[i]==2) s="html";
            else if(res[i]==3) s="javascript";
            else if(res[i]==4)  s="data structures";
           else if(res[i]==5)   s="database";
           else if(res[i]==6)    s="frame works";

           else if(res[i]==7)  s="backend";
           else if(res[i]==8)  s="css";
           else if(res[i]==9)   s="front end";
           else s="full stack";
           System.out.print(s+"-->");
        }
         
    }
}
class Find {
    public int[] OrderOfCourses (int n,int[][] pre) {
      
        Map<Integer,List<Integer>> adjacencyList =new HashMap<Integer, List<Integer>>();
        int[] indegree =new int[n]; // to keep track of indegree of courses
        int[] order=new int[n]; //order of courses
        ////  creating adjacency list contains courses which is prerequisite for its corresponding courses.
        // it takes E time
         for(int i=0;i< pre.length;i++){
            int dest=pre[i][0];
            int src=pre[i][1];
            List<Integer> lst = adjacencyList.getOrDefault(src, new ArrayList<Integer>());
            lst.add(dest);
            adjacencyList.put(src,lst);
            indegree[dest]+=1;  // increaing indegree of course
         }
         System.out.println(adjacencyList); // print adjacency list
         //{key:prerequisite ,values: courses to which key is prerequisite}
         //{0=[1], 1=[3, 4, 5, 6, 2], 2=[8], 3=[9], 4=[7], 5=[7], 6=[7], 7=[10], 8=[9], 9=[7]} ---adjacency list
         for(int i=0;i<n;i++)   // print indegrees at initial step
         System.out.println(i+" "+indegree[i]);
         /*coure  indegree
          0 0
         1 1
         2 1
        3 1
        4 1
        5 1
        6 1
        7 4
        8 1
        9 2
       10 1
       */
         Queue<Integer> q= new LinkedList<Integer>();
         // find the courses which has indegree 0
         for(int i=0;i<n;i++){
            if(indegree[i]==0){
                q.add(i);
                System.out.println(i+" ");
            }
         }
         int i=0;
         // it takes V time
         while(!q.isEmpty()){
            int node = q.remove(); 
            order[i++]=node;        // adding the course to order array
            if(adjacencyList.containsKey(node)){
               for (Integer neighbor : adjacencyList.get(node)) {
                   indegree[neighbor]--;        // decrease the indegree nothing but removing edges
    
                    if(indegree[neighbor]==0) q.add(neighbor);   // add the course which has indegree is 0
                }
         }  
}
if(i==n) return order; // defines that it is possible to order the courses.
return new int[-1];
    }

}
//output : omputer basics-->programming language-->javascript-->data structures-->database-->frame works-->html-->css-->front end-->backend-->full stack

//create adjlist--> E
//travelling all the vertices ---V     Time complexity: O(E+V)
//storing inorder array: V, queue: V ,order : V  space complexity: O(V)