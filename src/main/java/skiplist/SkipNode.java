package skiplist;
import java.util.Arrays;
/**
 * @Description:条约列表节点
 * @author:difeng
 * @time:2016年12月3日 下午5:07:18
 */
public class SkipNode<K,V>{
    K k;
    V v;
    SkipNode<K,V>[] forward; 
    
    @SuppressWarnings("unchecked")
	public SkipNode(K k,V v,int level){
    	this.k = k;
    	this.v = v;
    	forward = (SkipNode<K,V>[])new SkipNode[level];
        for(int i = 0;i < level;i++){
        	forward[i] = null;
        }
    }
    
	@Override
	public String toString() {
		return "SkipNode [k=" + k + ", v=" + v + ", forward=" + Arrays.toString(forward) + "]";
	}
}

