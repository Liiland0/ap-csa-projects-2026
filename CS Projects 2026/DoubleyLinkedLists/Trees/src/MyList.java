public class MyList {
    private Node head, tail;

    public MyList(float firstDada) {
        head = new Node(firstDada);
        tail = head;
    }

    public Node GetHead() {
        return head;
    }

    public void AppendNode(float dada) {
        if (head == null) {
            head = new Node(dada);
            tail = head;
        } else {
            Node newNode = new Node(dada);
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public boolean AddAfter(float dada, int index) {
        Node newNode = new Node(dada);
        Node curNode = head;

        for (int i = 0; i <= index; i++) {
            if (curNode == null)
                return false;
            curNode = curNode.next;
        }

        if (curNode.next != null) {
            curNode.next.prev = newNode;
            newNode.next = curNode.next;
        } else {
            tail = newNode;
        }
        curNode.next = newNode;
        newNode.prev = curNode;
        return true;
    }

    public void AddSorted(float dada) {
        Node newNode = new Node(dada);

        if (head == null) {
            head = tail = newNode;
            return;
        }

        Node curNode = head;

        if (dada <= head.GetDada()) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            return;
        }

        while (curNode.next != null && curNode.next.GetDada() < dada) {
            curNode = curNode.next;
        }

        newNode.next = curNode.next;
        newNode.prev = curNode;

        if (curNode.next != null) {
            curNode.next.prev = newNode;
        } else {
            tail = newNode;
        }

        curNode.next = newNode;
    }

    public void RemoveLast() {
        if (tail == head) {
            tail = null;
            head = null;
        } else if (tail != null) {

            tail = tail.prev;
            tail.next = null;
        } else
            return;
    }

    public void RemoveAt(int index) {
        Node curNode = head;

        for (int i = 0; i < index; i++) {
            if (curNode == null)
                return;
            curNode = curNode.next;
        }

        if (curNode.next != null)
            curNode.next.prev = curNode.prev;
        if (curNode.prev != null)
            curNode.prev.next = curNode.next;

        curNode = null;
    }

    public static void PrintList(Node head) {
        while (head != null) {
            System.out.println(head.GetDada());
            head = head.next;
        }
    }
}
