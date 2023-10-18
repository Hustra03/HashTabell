public class DoubleLinkedList {

    DoubleLinkedCell firstCell;

    public DoubleLinkedList() {
        this.firstCell = null;
    }

    public DoubleLinkedCell getFirstCell() {
        return this.firstCell;
    }

    public void setFirstCell(DoubleLinkedCell NewFirstCell) {
        this.firstCell = NewFirstCell;
    }

    void add(int code, String name, int pop) {
        DoubleLinkedCell newFirstCell = new DoubleLinkedCell( code,  name,  pop, this.firstCell, null);
        if (this.firstCell != null) {
            this.firstCell.setPreviousCell(newFirstCell);
        }
        this.firstCell = newFirstCell;
    }

    void insert(DoubleLinkedCell newFirstCell) {
        if (this.firstCell != null) {
            this.firstCell.setPreviousCell(newFirstCell);
        }
        newFirstCell.setPreviousCell(null);
        newFirstCell.setTail(this.firstCell);
        this.firstCell = newFirstCell;
    }

    int length() {
        int length = 0;
        DoubleLinkedCell nxt = this.firstCell;
        while (nxt != null) {
            nxt = nxt.tail;
            length++;
        }
        return length;
    }

    boolean find(int item) {
        DoubleLinkedCell nxt = this.firstCell;
        while (nxt != null) {
            if (nxt.getCode() == item) {
                return true;
            }
            nxt = nxt.tail;
        }
        return false;
    }

    void remove(int item) {

            DoubleLinkedCell nxt = this.firstCell;
            while (nxt != null) {
                if (nxt.getCode() == item) {
                    nxt.previousCell.setTail(nxt.getTail());
                    nxt.getTail().setPreviousCell(nxt.previousCell);
                    break;
                }
                nxt = nxt.tail;
            }
    }

    void unlink(DoubleLinkedCell item) {
        if (firstCell != item) {

            DoubleLinkedCell prv = item.getPreviousCell();
            DoubleLinkedCell nxt = item.getTail();
            if (nxt != null) {

                nxt.setPreviousCell(prv);
            }
            prv.setTail(nxt);

        } else {
            if (firstCell == null) {
                this.firstCell = null;
            } else {
                this.firstCell = this.firstCell.getTail();
            }
        }
    }

    public void append(DoubleLinkedList b) {
        DoubleLinkedCell nxt = this.firstCell;
        while (nxt.tail != null) {
            nxt = nxt.tail;
        }
        nxt.setTail(b.getFirstCell());
        nxt.getTail().setPreviousCell(nxt);
        b.setFirstCell(null);
    }
}
