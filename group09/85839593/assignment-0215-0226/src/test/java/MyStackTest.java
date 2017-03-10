import org.junit.*;
import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created with IntelliJ IDEA.
 * User: guohairui
 * Date: 17-2-26
 * Time: 下午7:20
 * To change this template use File | Settings | File Templates.
 */
public class MyStackTest {
    @org.junit.Test
    public void testPush() throws Exception {
        MyStack myStack = new MyStack();
        myStack.push("abc1");
        myStack.push("abc2");
        myStack.push("abc3");
        System.out.println(myStack);
        myStack.push("abc4");
        System.out.println(myStack);
        System.out.println("myStack.peek:"+myStack.peek());
        myStack.pop();
        System.out.println("myStack.size"+myStack.size());
        System.out.println(myStack);
    }

    @Test
    public void testPop() throws Exception {
        RandomAccessFile raf = new RandomAccessFile("C:\\Users\\Administrator\\Downloads\\taikanglife_003001_20170223.csv","r");
        FileChannel inChannel = raf.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(280);
        raf.seek(4);
        System.out.println(raf.read());
        System.out.println(raf.getFilePointer());
        inChannel.read(buf);
        System.out.println(new String(buf.array()));
        System.out.println("-------------");
        inChannel.read(buf);
        System.out.println(new String(buf.array()));
        System.out.println("-------------");
        inChannel.read(buf);
        System.out.println(new String(buf.array()));
    }

    @Test
    public void testPeek() throws Exception {
        byte[] th1 = "线程1".getBytes();
        byte[] th2 = "线程2".getBytes();
        byte[] th3 = "线程3".getBytes();
        final int len = th1.length;

        final RandomAccessFile raf = new RandomAccessFile(
                "C:\\Users\\Administrator\\Downloads\\76819532.txt", "rw");
        raf.seek(1);
        raf.write(th1);
        raf.seek(200);
        raf.write(th2);
        raf.seek(300);
        raf.write(th3);

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    byte[] b = new byte[len];
                    try {
                        synchronized (raf) {
                            raf.seek(1);
                            raf.read(b);
                        }

                        if ("线程1".equals(new String(b))) {
                            System.out.println("ok1");
                        } else {

                            System.out.println("线程1-" + new String(b));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        byte[] b = new byte[len];
                        synchronized (raf) {
                            raf.seek(200);
                            raf.read(b);
                        }
                        if ("线程2".equals(new String(b))) {
                            System.out.println("ok2");
                        } else {

                            System.out.println("线程2-" + new String(b));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Runnable runnable3 = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        byte[] b = new byte[len];
                        synchronized (raf) {
                            raf.seek(300);
                            raf.read(b);
                        }
                        if ("线程3".equals(new String(b))) {
                            System.out.println("ok3");
                        } else {

                            System.out.println("线程3-" + new String(b));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        for (int i = 0; i < 20; i++) {
            new Thread(runnable1).start();
            new Thread(runnable2).start();
            new Thread(runnable3).start();
        }
        System.out.println("主线程完了");

    }
}
