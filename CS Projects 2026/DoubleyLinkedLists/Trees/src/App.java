public class App {
    public static void main(String[] args) throws Exception {
        MyList list = new MyList(5);

        list.AppendNode(10);
        MyList.PrintList(list.GetHead());

        System.out.println();

        list.AddSorted(7);
        list.AddSorted(3);
        list.AddSorted(12);
        MyList.PrintList(list.GetHead());
    }
}
