package ua.cn.stu.savingscalculator.tasks;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceTask<T> extends CalculateTask<T> {

    private Callable<T> callable;
    private Future<?> future;

    private  static final ExecutorService SERVICE  = Executors.newCachedThreadPool();
    public ExecutorServiceTask(Callable<T> callable) {
        this.callable = callable;
    }

    @Override
    protected void onCancelled() {
        if(future!=null)
        {
            future.cancel(true);
            future=null;
        }
    }

    @Override
    protected void start() {
        future = SERVICE.submit(()->{
            try{
                T result =callable.call();
                publishSuccess(result);
            }
            catch (Exception e){
                publishError(e);
            }
        });
    }
}
