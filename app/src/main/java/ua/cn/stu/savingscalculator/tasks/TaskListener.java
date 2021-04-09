package ua.cn.stu.savingscalculator.tasks;

public interface TaskListener<T> {
    void onSuccess(T result);
    void onError(Throwable error);
}
