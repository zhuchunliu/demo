package thread.lock;

/**
 * Created by zhuchunliu on 2017/6/19.
 */
public class LockThread extends Thread{
    private LockService service;
    public LockThread(LockService lockService){
        this.service = lockService;
    }
    @Override
    public void run() {
        super.run();
        service.testMethod();
    }
}
