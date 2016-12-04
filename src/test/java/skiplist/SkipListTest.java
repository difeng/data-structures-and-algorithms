package skiplist;

import junit.framework.TestCase;

/**
 * @Description:TODO
 * @author:difeng
 * @date:2016年12月4日
 */
public class SkipListTest extends TestCase {

	static SkipList<Integer,Integer> skipList = new SkipList<Integer,Integer>();

	public void testInsert(){
		skipList.insert(1, 1);
		skipList.insert(3, 3);
		skipList.insert(9, 9);
		skipList.insert(5, 5);
		skipList.insert(7, 7);
		skipList.insert(13, 13);
		assertEquals(6,skipList.size);
	}

	public void testFind(){
		int x = skipList.find(3);
		assertEquals(3,x);
	}

	public void testDelete(){
		skipList.delete(3);
		assertNull(skipList.find(3));
	}
}

