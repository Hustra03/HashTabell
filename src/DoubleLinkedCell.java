public class DoubleLinkedCell {
    int code;
    String name;
    int pop;

    DoubleLinkedCell tail;
    DoubleLinkedCell previousCell;

    DoubleLinkedCell(int code, String name, int pop, DoubleLinkedCell tl, DoubleLinkedCell prv) {
        this.code = code;
        this.name=name;
        this.pop=pop;
        tail = tl;
    }

    public int getCode() {
        return this.code;
    }

    public DoubleLinkedCell getTail() {
        return this.tail;
    }

    public DoubleLinkedCell getPreviousCell() {
        return this.previousCell;
    }

    public void setTail(DoubleLinkedCell newTail) {
        this.tail = newTail;
    }

    public void setPreviousCell(DoubleLinkedCell newPrevious) {
        this.previousCell = newPrevious;
    }

}
