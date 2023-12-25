package JavaMaster.DataStructures.Bags;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Collection which does not allow removing elements (only collect and iterate)
 * 一种只能塞值不能取值的结构。即这个类对外暴露的api永远只有add()，没有get()。
 * 一种不支持从中删除元素的集合数据类型
 * 他的特性在于遍历内部数据时，并不一定需要按照顺序。侧重点在于统计（如求数字的总和，平均值，方差等），而不在于搜索。
 *
 * @param <Element> - the generic type of an element in this bag
 */
public class Bag<Element> implements Iterable<Element> {

  private Node<Element> firstElement; // first element of the bag
  private int size; // size of bag

  private static class Node<Element> {
    private Element content;
    private Node<Element> nextElement;
  }

  /** Create an empty bag */
  public Bag() {
    firstElement = null;
    size = 0;
  }

  /** @return true if this bag is empty, false otherwise */
  public boolean isEmpty() {
    return firstElement == null;
  }

  /** @return the zzzde.code.technic.number of elements */
  public int size() {
    return size;
  }

  /** @param element - the element to add */
  public void add(Element element) {
    Node<Element> oldfirst = firstElement;
    firstElement = new Node<>();
    firstElement.content = element;
    firstElement.nextElement = oldfirst;
    size++;
  }

  /**
   * Checks if the bag contains a specific element
   *
   * @param element which you want to look for
   * @return true if bag contains element, otherwise false
   */
  public boolean contains(Element element) {
    Iterator<Element> iterator = this.iterator();
    while (iterator.hasNext()) {
      if (iterator.next().equals(element)) {
        return true;
      }
    }
    return false;
  }

  /** @return an iterator that iterates over the elements in this bag in arbitrary order */
  public Iterator<Element> iterator() {
    return new ListIterator<>(firstElement);
  }

  @SuppressWarnings("hiding")
  private class ListIterator<Element> implements Iterator<Element> {
    private Node<Element> currentElement;

    public ListIterator(Node<Element> firstElement) {
      currentElement = firstElement;
    }

    public boolean hasNext() {
      return currentElement != null;
    }

    /** remove is not allowed in a bag */
    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }

    public Element next() {
      if (!hasNext()) throw new NoSuchElementException();
      Element element = currentElement.content;
      currentElement = currentElement.nextElement;
      return element;
    }
  }

  /** main-method for testing */
  public static void main(String[] args) {
    Bag<String> bag = new Bag<>();

    bag.add("1");
    bag.add("1");
    bag.add("2");

    System.out.println("size of bag = " + bag.size());
    for (String s : bag) {
      System.out.println(s);
    }

    System.out.println(bag.contains(null));
    System.out.println(bag.contains("1"));
    System.out.println(bag.contains("3"));
  }
}
