public class Philosopher extends Thread {
    private final Table table;
    private final int leftFork, rightFork;
    private final int id;


    public Philosopher(int id, Table table) {

        this.id = id;
        this.table = table;

        rightFork = id;
        leftFork = (id + 1) % 5;

        start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Philosopher" + id + " думає " + (i + 1) + "-й раз");
            try {
                table.waiter.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            table.getFork(rightFork);
            table.getFork(leftFork);


            System.out.println("Philosopher" + id + " поїв " + (i + 1) + "-й раз");

            table.putFork(leftFork);
            table.putFork(rightFork);
            table.waiter.release();
        }
    }

}
