package me.cmder.mvpdemo.PremiumVideo;

import android.content.Context;

import me.cmder.mvpdemo.base.IBasePresenter;
import me.cmder.mvpdemo.base.IBaseView;
import me.cmder.mvpdemo.data.bean.TaskBean;

/**
 * Created by Yao Ximing on 2018/1/9.
 */

//统一管理view与presenter的所有的接口,每一个完整的业务，实现一个contract
//一个继承view基类的接口类+一个继承presenter的接口类
public interface ITaskContract {

    interface View extends IBaseView<Presenter> {

        void showData(TaskBean taskBean);

        Context obtainContext();

    }

    interface Presenter extends IBasePresenter {

        void fetchData();

    }
}
