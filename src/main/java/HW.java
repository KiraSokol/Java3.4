public class HW {
    static final Object object = new Object();
    static String letter = "A";

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (object) {
                        while (!letter.equals("A")) {
                            object.wait();
                        }
                        System.out.print(letter);
                        letter = "B";
                        object.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (object) {
                        while (!letter.equals("B")) {
                            object.wait();
                        }
                        System.out.print(letter);
                        letter = "C";
                        object.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (object) {
                        while (!letter.equals("C")) {
                            object.wait();
                        }
                        System.out.print(letter);
                        letter = "A";
                        object.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
