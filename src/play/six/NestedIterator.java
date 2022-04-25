package play.six;

/**
 * 341
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class NestedIterator implements Iterator<Integer> {

    private List<Integer> allEle = new ArrayList<>();
    private int count;

    public NestedIterator(List<NestedInteger> nestedList) {
        forAll(nestedList);
    }

    private void forAll(List<NestedInteger> nestedList) {
        for (int i = 0; i< nestedList.size(); i++) {
            NestedInteger cur = nestedList.get(i);
            if (cur.isInteger()) {
                allEle.add(cur.getInteger());
            } else {
                forAll(cur.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return allEle.get(count++);
    }

    @Override
    public boolean hasNext() {
        return count < allEle.size();
    }
}
