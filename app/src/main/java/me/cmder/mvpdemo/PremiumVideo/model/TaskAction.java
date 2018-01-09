package me.cmder.mvpdemo.PremiumVideo.model;

import me.cmder.mvpdemo.data.bean.TaskBean;

/**
 * Created by yong on 2018/1/10.
 */

//业务逻辑实现部分，取名manager/action（重名最好不用service....）随意.....
public class TaskAction {
    private TaskAction() {}
    private static TaskAction taskAction =null;
    //静态工厂方法
    public static TaskAction getInstance() {
        if (taskAction == null) {
            taskAction = new TaskAction();
        }
        return taskAction;
    }


    //业务逻辑代码....
    public TaskBean reBuildString(TaskBean taskBean){
        TaskBean taskBeanTemp = taskBean;
        taskBeanTemp.setData(taskBeanTemp.getData()+"rebuild");
        return taskBeanTemp;
    }

    //耗时方法
    public TaskBean reBuildStringCostTime(TaskBean taskBean){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TaskBean taskBeanTemp = taskBean;
        taskBeanTemp.setData(taskBeanTemp.getData()+"rebuild");
        return taskBeanTemp;
    }
}
