import junit.framework.TestCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


public class BSTTests {

	@Rule
	public Timeout globalTimeout = new Timeout(1000);

	@Test
//	public void checkConstructor() {
//		BST<String> t = new BST<String>();
//		TestCase.assertNull(t.getRoot());
//	}
//
//
//	@Test
//	public void addOneString() {
//		BST<String> t = new BST<String>();
//		t.add("Hello");
//		TestCase.assertNotNull(t.getRoot());
//		TestCase.assertNull(t.getRoot().getParent());
//		TestCase.assertEquals("Hello", t.getRoot().getData());
//	}
//
//	@Test
//	public void addTwoStrings() {
//		BST<String> t = new BST<String>();
//		t.add("Hello");
//		t.add("Kitty");
//		TestCase.assertNotNull(t.getRoot());
//		TestCase.assertEquals("Hello", t.getRoot().getData());
//		TestCase.assertEquals("Kitty", t.getRoot().getRight().getData());
//		TestCase.assertNull(t.getRoot().getLeft());
//		TestCase.assertNull(t.getRoot().getRight().getRight());
//		TestCase.assertEquals(t.getRoot(), t.getRoot().getRight().getParent());
//	}
//
//	@Test
//	public void addThreeStrings() {
//		BST<String> t = new BST<String>();
//		t.add("Hello");
//		t.add("Crazy");
//		t.add("Pikachu");
//		TestCase.assertNotNull(t.getRoot());
//		TestCase.assertEquals("Hello", t.getRoot().getData());
//		TestCase.assertEquals("Pikachu", t.getRoot().getRight().getData());
//		TestCase.assertEquals("Crazy", t.getRoot().getLeft().getData());
//		TestCase.assertNull(t.getRoot().getRight().getRight());
//		TestCase.assertNull(t.getRoot().getRight().getLeft());
//		TestCase.assertNull(t.getRoot().getLeft().getRight());
//		TestCase.assertNull(t.getRoot().getLeft().getLeft());
//		TestCase.assertEquals(t.getRoot(), t.getRoot().getRight().getParent());
//		TestCase.assertEquals(t.getRoot(), t.getRoot().getLeft().getParent());
//	}
//
//
//	@Test
//	public void checkMinimumMaximum() {
//		BST<Integer> bsti = new BST<Integer>();
//
//		for(int i = 0; i < 100; i++) {
//			bsti.add(i);
//		}
//		TestCase.assertEquals(new Integer(0), bsti.minimum().getData());
//		TestCase.assertEquals(new Integer(99), bsti.maximum().getData());
//		}
//	@Test
//	public void checkMaximumMinimum() {
//		BST<Integer> bsti = new BST<Integer>();
//
//		for(int i = 99; i >= 0; i--) {
//			bsti.add(i);
//		}
//		TestCase.assertEquals(new Integer(0), bsti.minimum().getData());
//		TestCase.assertEquals(new Integer(99), bsti.maximum().getData());
//	}
//	@Test
//	public void checkFindOnSmallBST() {
//		BST<Integer> bsti = new BST<Integer>();
//		bsti.add(10);
//		bsti.add(20);
//		bsti.add(30);
//		bsti.add(28);
//		bsti.add(15);
//
//		BST.Node<Integer> n = bsti.find(28);
//		TestCase.assertEquals("find returns a bad node", new Integer(28), n.getData());
//		TestCase.assertEquals("find returns a bad node, or your tree structure is suspect", bsti.getRoot(), n.getParent().getParent().getParent());
//
//		n = bsti.find(15);
//		TestCase.assertEquals("find returns a bad node", new Integer(15), n.getData());
//		TestCase.assertEquals("find returns a bad node, or your tree structure is suspect", bsti.getRoot(), n.getParent().getParent());
//
//	}
//
//	@Test
//	public void checkAdd10ThenAdd20ThenAdd30() {
//		BST<Integer> bsti = new BST<Integer>();
//		bsti.add(10);
//		TestCase.assertEquals("10 should be at the root", new Integer(10), bsti.getRoot().getData());
//		bsti.add(20);
//		TestCase.assertEquals("20 should be the root's right child" , new Integer(20), bsti.getRoot().getRight().getData());
//		bsti.add(30);
//		TestCase.assertEquals("30 should be the root's right child" , new Integer(30), bsti.getRoot().getRight().getRight().getData());
//
//
//	}
//	@Test
//	public void checkEasySuccessor() {
//		BST<Integer> bsti = new BST<Integer>();
//		bsti.add(10);
//		bsti.add(5);
//		bsti.add(8);
//		bsti.add(20);
//
//		BST.Node<Integer> f = bsti.find(5);
//		TestCase.assertNotNull(f);
//		BST.Node<Integer> e = bsti.successor(f);
//		TestCase.assertEquals(8, (int) e.getData());
//
//	}
//	@Test
//	public void checkSuccessor2() {
//		BST<Integer> bsti = new BST<Integer>();
//		bsti.add(10);
//		bsti.add(5);
//		bsti.add(8);
//		bsti.add(7);
//		bsti.add(9);
//		bsti.add(20);
//
//		BST.Node<Integer> f = bsti.find(5);
//		TestCase.assertNotNull(f);
//		BST.Node<Integer> e = bsti.successor(f);
//		TestCase.assertEquals(7, (int) e.getData());
//
//	}
//		10
//	4/		\20
//		\8
//	6/		\9
//		\7

//		10
//	5/		\20
//		\8
//	7/		\9
//	 */
//	@Test
//	public void checkSuccessor3() {
//		BST<Integer> bsti = new BST<Integer>();
//		bsti.add(10);
//		bsti.add(5);
//		bsti.add(8);
//		bsti.add(7);
//		bsti.add(9);
//		bsti.add(20);
//
//		BST.Node<Integer> f = bsti.find(5);
//		TestCase.assertNotNull(f);
//		BST.Node<Integer> e = bsti.successor(f);
//		TestCase.assertEquals(7, (int) e.getData());
//		System.out.println(e.getData());
//		e = bsti.successor(e);
//		TestCase.assertEquals(8, (int) e.getData());
//		System.out.println(e.getData());
//		e = bsti.successor(e);
//		TestCase.assertEquals(9, (int) e.getData());
//		System.out.println(e.getData());
//		e = bsti.successor(e);
//		TestCase.assertEquals(10, (int) e.getData());
//		System.out.println(e.getData());
//		e = bsti.successor(e);
//		TestCase.assertEquals(20, (int) e.getData());
//		System.out.println(e.getData());
//		e = bsti.successor(e);
//		TestCase.assertNull(e);
//
//	}
	public void checkRemoveWithRightChild() {
		BST<Integer> bst = new BST<Integer>();
		bst.add(1);
		bst.add(2);
		bst.add(3);
		TestCase.assertNull(bst.find(1).getParent());
		TestCase.assertEquals(2, (int) bst.find(1).getRight().getData());
		TestCase.assertEquals(3, (int) bst.find(2).getRight().getData());
		TestCase.assertEquals(1, (int) bst.find(2).getParent().getData());
		TestCase.assertEquals(2, (int) bst.find(3).getParent().getData());
		BST.Node<Integer> beforeRemoved = bst.find(2);
		BST.Node<Integer> suc = bst.successor(bst.find(2));
		BST.Node<Integer> afterRemoved = bst.remove(2);
		BST.Node<Integer> replaced = bst.find(3);
		TestCase.assertEquals(beforeRemoved, afterRemoved);
		TestCase.assertEquals(suc, replaced);
		TestCase.assertNull(bst.find(2));
		TestCase.assertEquals(1, (int) bst.find(3).getParent().getData());
		TestCase.assertEquals(suc, bst.find(1).getRight());
	}
	@Test
	public void checkRemoveWithLeftChild() {
		BST<Integer> bst = new BST<Integer>();
		bst.add(3);
		bst.add(2);
		bst.add(1);
		TestCase.assertNull(bst.find(3).getParent());
		TestCase.assertEquals(2, (int) bst.find(1).getParent().getData());
		TestCase.assertEquals(3, (int) bst.find(2).getParent().getData());
		TestCase.assertEquals(1, (int) bst.find(2).getLeft().getData());
		TestCase.assertEquals(2, (int) bst.find(3).getLeft().getData());
		BST.Node<Integer> beforeRemoved = bst.find(2);
		BST.Node<Integer> suc = bst.find(1);
		BST.Node<Integer> afterRemoved = bst.remove(2);
		BST.Node<Integer> replaced = bst.find(1);
		TestCase.assertEquals(beforeRemoved, afterRemoved);
		TestCase.assertEquals(suc, replaced);
		TestCase.assertEquals(3, (int) bst.getRoot().getData());
		TestCase.assertNull(bst.find(2));
		TestCase.assertEquals(3, (int) bst.find(1).getParent().getData());
		TestCase.assertEquals(suc, bst.find(3).getLeft());
	}

	@Test
	public void checkRemoveNoChildren() {
		BST<Integer> bst = new BST<>();
		bst.add(1);
		TestCase.assertEquals(1, (int) bst.find(1).getData());
		TestCase.assertEquals(1, (int) bst.getRoot().getData());
		TestCase.assertNull(bst.find(1).getLeft());
		TestCase.assertNull(bst.find(1).getRight());
		TestCase.assertNull(bst.find(1).getParent());
		bst.add(2);
		TestCase.assertEquals(2, (int) bst.find(1).getRight().getData());
		TestCase.assertNull(bst.find(1).getLeft());
		TestCase.assertEquals(1, (int) bst.find(2).getParent().getData());
		BST.Node<Integer> node = bst.remove(2);
		TestCase.assertNull(bst.find(1).getRight());
		TestCase.assertNull(bst.successor(bst.find(1)));
	}
	@Test
	public void checkRemoveSimple() {
		BST<Integer> bst = new BST<>();
		bst.add(20);
		bst.add(30);
		bst.add(25);
		bst.add(40);
		bst.add(27);
		BST.Node<Integer> beforeRemoved = bst.find(30);
		BST.Node<Integer> suc = bst.find(40);
		BST.Node<Integer> afterRemoved = bst.remove(30);
		BST.Node<Integer> replaced = bst.find(40);
		TestCase.assertEquals(beforeRemoved, afterRemoved);
		TestCase.assertEquals(suc, replaced);
		TestCase.assertNull(bst.find(30));
		TestCase.assertEquals(20, (int) bst.find(40).getParent().getData());
		TestCase.assertEquals(25, (int) bst.find(40).getLeft().getData());
		TestCase.assertEquals(suc, bst.find(25).getParent());
	}
}
