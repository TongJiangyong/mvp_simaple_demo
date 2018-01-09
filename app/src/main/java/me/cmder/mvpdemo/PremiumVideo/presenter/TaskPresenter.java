package me.cmder.mvpdemo.PremiumVideo.presenter;

import me.cmder.mvpdemo.PremiumVideo.TaskContract;
import me.cmder.mvpdemo.PremiumVideo.model.TaskAction;
import me.cmder.mvpdemo.data.bean.TaskBean;

/**
 * Created by Yao Ximing on 2018/1/9.
 */

//视图展示部分，用户动作触发
//约定，AGEventHandler不在view做触发，在presenter做实现和转发
public class TaskPresenter implements TaskContract.Presenter {

    private TaskContract.View mTask0View;
    private TaskAction taskAction = TaskAction.getInstance();
    public TaskPresenter(TaskContract.View mTask0View) {
        this.mTask0View = mTask0View;
        mTask0View.setPresenter(this);
    }

    @Override
    public void start() {
        TaskBean task = new TaskBean();
        task.setData("initial state");
        mTask0View.showData(task);
    }

    @Override
    public void fetchData() {
        TaskBean task = new TaskBean();
        task.setData("try to fetchData");
        /**
         * // 基本上能用了
        // 这里presenter直接调用UI线程，不是特别好，
        // 如果有阻塞或者网络可能需要rxjava，不然只能用hand.post，不太好....
         **/
        //TODO 最好请希明在taskAction中针对如下耗时(reBuildStringCostTime)的方法，使用rxjava做一个范例程序
        //taskAction.reBuildStringCostTime(task);
        taskAction.reBuildString(task);
        mTask0View.showData(task);
    }
}
