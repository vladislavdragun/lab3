package ua.cn.stu.savingscalculator.tasks;

public interface Task<T> {
    void execute(TaskListener<T> listener);

    void cancel();
}
