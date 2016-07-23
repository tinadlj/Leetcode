//Binary search tree 9ms
public class Solution {
    class Node{
        Node right;
        Node left;
        int v,d=1,preSum=0; // preSum is the smaller before current node so far.
        
        public Node(int val){
            v=val;
        }
    }
    
    public List<Integer> countSmaller(int[] nums) {
           if (nums.length==0){
               return Collections.emptyList();
           }
           
           Integer[] arr=new Integer[nums.length];
           Node root=null;
           for (int i=nums.length-1;i>=0;i--){
               root=insertNode(nums[i],root,arr,i,0);
           }
           
           return Arrays.asList(arr);
    }
    
    public Node insertNode(int val, Node root, Integer[] arr, int index, int preSums){//preSums is all the right nodes preSum sum.
        if (root==null){
            root=new Node(val);
            arr[index]=preSums;
        }else if (root.v==val){
            root.d++;
            arr[index]=preSums+root.preSum;
        }else if (root.v>val){
            root.preSum++;
            root.left=insertNode(val,root.left,arr,index,preSums);
        }else{
            root.right=insertNode(val,root.right,arr,index,preSums+root.d+root.preSum);
        }
        return root; 
    }

}

//TreeMap + Binary index tree 40ms
public class Solution {
    private int size;
    private int [] BIT;
    private Map<Integer,Integer> tm;
    public List<Integer> countSmaller(int[] nums) {
        if (nums.length==0){
            return Collections.emptyList();
        }
        size=nums.length+1;
        Integer[] result=new Integer[size-1];
        BIT=new int[size];
        sort(nums);
        for (int i=nums.length-1;i>=0;i--){
            int t=tm.get(nums[i]);
            result[i]=get(t);
            add(t+1,1);
        }
        
        return Arrays.asList(result);
    }
    
    public void sort(int[] arr){
        HashMap<Integer,Integer> hm=new HashMap<>();
        for (int i=0;i<arr.length;i++){
            hm.put(arr[i],1);
        }
        tm=new TreeMap<>(hm);
        int i=0;
        for (Integer key : tm.keySet()){
            tm.put(key,i++);
        }
    }
    
    public void add(int index,int value){
        while(index<size){
            BIT[index]+=1;
            index+=(index&-index);
        }
    }
    
    public int get(int index){
        int value=0;
        while(index>0){
            value+=BIT[index];
            index-=(index&-index);
        }
        return value;
    }
}
