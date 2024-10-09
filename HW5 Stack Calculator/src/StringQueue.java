public class StringQueue {
    String[] q;
    int length;
    StringQueue(int size) {
        q = new String[size];
        length = 0;
    }

    public void enqueue(String s) {
        if (length >= q.length) {
            System.out.println("The queue is full. " + s + " couldn't fit.");
        } else {
            q[length] = s;
            length++;
        }
    }
    public String dequeue(){
        String local = q[0];
        int i;
        for (i = 0; i < length-1; i++) {
            q[i] = q[i+1];
        }
        q[i] = "";
        length--;
        return local;
    }
    public void myPrint(){
        int i;
        System.out.print("[");
        for(i = 0; i < length-1; i++) {
            System.out.print(q[i]+", ");
        }
        System.out.print(q[i]+"]\n");
    }
    public static void main(String[] args) {
        StringQueue myQueue = new StringQueue(5);
        myQueue.enqueue("Aaron");
        myQueue.enqueue("Nikolo");
        myQueue.enqueue("Ryan");
        myQueue.enqueue("Lucas");
        myQueue.enqueue("Devin");
        myQueue.enqueue("Mustaph");
        myQueue.myPrint();
        myQueue.dequeue();
        myQueue.myPrint();
    }
}
