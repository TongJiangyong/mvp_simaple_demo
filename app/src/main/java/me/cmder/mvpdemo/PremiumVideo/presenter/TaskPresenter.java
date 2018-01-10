package me.cmder.mvpdemo.PremiumVideo.presenter;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.cmder.mvpdemo.PremiumVideo.ITaskContract;
import me.cmder.mvpdemo.PremiumVideo.model.TaskAction;
import me.cmder.mvpdemo.data.bean.TaskBean;

/**
 * Created by Yao Ximing on 2018/1/9.
 */

//视图展示部分，用户动作触发
//约定，AGEventHandler不在view做触发，在presenter做实现和转发
public class TaskPresenter implements ITaskContract.Presenter {

    private ITaskContract.View mTask0View;
    private TaskAction taskAction = TaskAction.getInstance();

    public TaskPresenter(ITaskContract.View mTask0View) {
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
        final TaskBean task = new TaskBean();
        task.setData("try to fetchData");

        /**
         * // 基本上能用了
         // 这里presenter直接调用UI线程，不是特别好，
         // 如果有阻塞或者网络可能需要rxjava，不然只能用hand.post，不太好....
         **/
        Observable.just(taskAction.reBuildStringCostTime(task))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TaskBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TaskBean value) {
                        mTask0View.showData(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        /*taskAction.reBuildString(task);
        mTask0View.showData(task);*/
    }
}
