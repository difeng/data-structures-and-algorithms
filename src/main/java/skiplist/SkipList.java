package skiplist;
import java.util.Random;
/**	
 * @Description:跳跃列表
 * @author:difeng
 * @time:2016/12/3
 */
public class SkipList<K extends Comparable<? super K>,V> {
	SkipNode<K,V> head;
	int level;
	int size;
	public SkipList(){
		head = new SkipNode<K,V>(null,null,1);
		//刚开始为一层
		level = 1;
		size = 0;
	}
    /**
     * @Description:随机生成层数
     * @return:int
     */
	private int randomLevel(){
		int lev;
		for(lev = 1;Util.random(2) == 0;lev++);

		return lev;
	}

	public void insert(K k,V v){
		int newLevel = randomLevel();
		//调整头节点
		if(newLevel > level){
			SkipNode<K,V> tmp = head;
			head = new SkipNode<K,V>(null,null,newLevel);
			for(int i = 0;i < tmp.forward.length;i++){
				head.forward[i] = tmp.forward[i];
			}
			level = newLevel;
		}
		@SuppressWarnings("unchecked")
		SkipNode<K,V>[] update = new SkipNode[level];
		SkipNode<K,V> x = head;
		for(int i = level - 1;i >= 0;i--){
			while(x.forward[i] != null && x.forward[i].k.compareTo(k) < 0){
				x = x.forward[i];
			}
			update[i] = x;
		}
		x = x.forward[0];
		if(x != null && x.k != null && x.k.compareTo(k) == 0){
			x.v = v;
		}else{
			x = new SkipNode<K,V>(k,v,newLevel);
			for(int i = 0;i < newLevel;i++){
				x.forward[i] = update[i].forward[i];
				update[i].forward[i] = x;
			}
			size++;  
		}
	}


	public V find(K k){
		SkipNode<K,V> x = head;
		for(int i = level - 1;i >= 0;i--){
			while(x.forward[i] != null && x.forward[i].k.compareTo(k) < 0){
				x = x.forward[i];
			}
		}
		x = x.forward[0];
		if(k.compareTo(x.k) == 0){
			return x.v;
		}
		return null;
	}


	public void delete(K k){
		@SuppressWarnings("unchecked")
		SkipNode<K,V>[] update = new SkipNode[level];
		SkipNode<K,V> x = head;
		for(int i = level - 1;i >= 0;i--){
			while(x.forward[i] != null && x.forward[i].k.compareTo(k) < 0){
				x = x.forward[i];
			}
			update[i] = x;
		}
		x = x.forward[0];
		if(k.compareTo(x.k) == 0){
			for(int i = 0;i < level;i++){
				if(update[i].forward[i] != x){
					break;
				}
				update[i].forward[i] = x.forward[i];
			}
			x = null;
			do{
				level = level - 1;
			}while(level > 0 && head.forward[level] == null);
		    size--;
		}
	}
	/**
	 * @Description:按层输出(只输出key)
	 * @return:void
	 */
	public void printKeyByLevel(){
		SkipNode<K,V>  x = head;
		for(int i = level - 1;i >= 0;i--){
			System.out.print("level-" + i + ":");
			x = head.forward[0];
			System.out.print(x.k + " ");
			while(x != null){
				if(x.forward.length <= i){
					System.out.print(String.format("%5s"," ") + " ");
				}else{
					if(x.forward[i] == null){
						System.out.print(String.format("%5s"," ") + " ");
					}else{
						System.out.print(String.format("%5s", x.forward[i].k) + " ");
					}
				}
				x = x.forward[0];
			}
			System.out.println();
		}
	}
    /**
     * @Description:按节点输出(只输出key)
     * @return:void
     */
	public void printKeyByNode(){
		SkipNode<K,V> x = head;
		while(x != null){
			System.out.print(String.format("%3s forward-size-%s:",x.k == null?"":x.k,x.forward.length) + " ");
			for(int i = 0;i < x.forward.length;i++){
				if(x.forward[i] == null){
					System.out.print(String.format("%3s"," ") + " ");
				}else{
					System.out.print(String.format("%3s",x.forward[i].k,x.forward[i].v) + " ");
				}
			}
			System.out.println();
			x = x.forward[0];
		}
	}
	/**
	 * @Description: 随机数生成
	 */
	static class Util{
		static Random  random = new Random();
		public  static int random(int n){
			return Math.abs(random.nextInt()) % n;
		}
	}

	public static void main(String[] args) {
		SkipList<Integer,Integer> skipList = new SkipList<Integer, Integer>();
		skipList.insert(1, 1);
		skipList.insert(3, 3);
		skipList.insert(9, 9);
		skipList.insert(5, 5);
		skipList.insert(7, 7);
		skipList.insert(13, 13);
		System.out.println("find 3 in the skiplist:" + skipList.find(3));
		skipList.delete(7);
		System.out.println("printKeyByLevel:");
		skipList.printKeyByLevel();
		System.out.println("printKeyByNode:");
		skipList.printKeyByNode();
	}
}

