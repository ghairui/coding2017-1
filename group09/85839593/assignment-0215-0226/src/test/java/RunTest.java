import java.io.*;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: guohairui
 * Date: 17-3-14
 * Time: 下午7:52
 * To change this template use File | Settings | File Templates.
 */
public class RunTest {
    private static int num=0;
    private static long ctime=0;
    @org.junit.Test
    public void run1() throws Exception {
       Object obj =  Class.forName("Print").newInstance();
        Method m = obj.getClass().getMethod("prtln", String.class);
        m.invoke(obj,"guo");
        System.out.println(m.invoke(obj,"ni hao"));

    }
    @org.junit.Test
    public void x() throws Exception {

        System.out.println("啊".getBytes().length);
        System.out.println("啊".getBytes("gbk").length);
        System.out.println(new String("啊".getBytes("gbk")));

    }
    @org.junit.Test
    public void readFile(){
        try {
            InputStream csvOriInputStream = new FileInputStream("C:\\Users\\Administrator\\Downloads\\10019_1_20160530_2030.txt");
            final BufferedReader   reader =  new BufferedReader(new InputStreamReader(csvOriInputStream, "GBK"));
            final BufferedWriter   csvFileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\Administrator\\Downloads\\10019_1_20160530_2030_new.txt",true), "GBK"), 1024);
            String tempString = null;
            int line = 0;//行号
            final long currentTime=System.currentTimeMillis();

            /*while ((tempString = reader.readLine()) != null) {
                line++;
            }*/
            csvFileWriter.write(reader.readLine());
            Runnable runnable1 = new Runnable() {
                @Override
                public void run() {
                    int line = 0;
                    String tempString = null;
                    try {
                        while ((tempString = reader.readLine()) != null) {
                            csvFileWriter.newLine();
                            csvFileWriter.write(tempString);
                            line++;
                            //Thread.currentThread().sleep(10);
                        }
                        csvFileWriter.flush();
                    } catch (Exception e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    System.out.println("runnable1:"+line);
                    long time = System.currentTimeMillis()-currentTime;
                    System.out.println("runnable1用时:"+time);
                    num=num+line;
                    ctime=ctime+time;
                }
            };
            Runnable runnable2 = new Runnable() {
                @Override
                public void run() {
                    int line = 0;
                    String tempString = null;
                    try {
                        while ((tempString = reader.readLine()) != null) {
                            csvFileWriter.newLine();
                            csvFileWriter.write(tempString);
                            line++;
                            //Thread.currentThread().sleep(10);
                        }
                        csvFileWriter.flush();
                    } catch (Exception e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    System.out.println("runnable2:"+line);
                    long time = System.currentTimeMillis()-currentTime;
                    System.out.println("runnable2用时:"+time);
                    num=num+line;
                    ctime=ctime+time;
                }
            };
            Runnable runnable3 = new Runnable() {
                @Override
                public void run() {
                    int line = 0;
                    String tempString = null;
                    try {
                        while ((tempString = reader.readLine()) != null) {
                            csvFileWriter.newLine();
                            csvFileWriter.write(tempString);
                            line++;
                            //Thread.currentThread().sleep(10);
                        }
                        csvFileWriter.flush();
                    } catch (Exception e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    System.out.println("runnable3:"+line);
                    long time = System.currentTimeMillis()-currentTime;
                    System.out.println("runnable3用时:"+time);
                    num=num+line;
                    ctime=ctime+time;
                }
            };
            Thread thread1 = new Thread(runnable1);
            Thread thread2 = new Thread(runnable2);
            Thread thread3 = new Thread(runnable3);
            thread1.start();
            thread2.start();
            thread3.start();
              thread1.join(); thread2.join();thread3.join();
            System.out.println("num:"+num);
            System.out.println("ctime:"+ctime);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    @org.junit.Test
    public void downloadFile(){

    }
}
