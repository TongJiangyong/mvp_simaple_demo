package me.cmder.mvpdemo.PremiumVideo.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.cmder.mvpdemo.PremiumVideo.TaskContract;
import me.cmder.mvpdemo.PremiumVideo.presenter.TaskPresenter;
import me.cmder.mvpdemo.R;
import me.cmder.mvpdemo.data.bean.TaskBean;

//activity的作用主要是创建View（fragment），以及创建presenter，并把view传递给presenter
public class TaskActivity extends Activity implements TaskContract.View, View.OnClickListener {


    @BindView(R.id.tv_showData)
    TextView tv_ShowData;
    @BindView(R.id.bt_setData)
    Button bt_SetData;
    private TaskContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bt_SetData.setOnClickListener(this);
        //一定要加，比较重要
        new TaskPresenter(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //TODO 感觉缺少presenter的销毁方法，有隐患，请希明补充下
        // presenter的销毁方法请在各个异步回调过程中自行处理，不要进行统一处理，主要就是防止是实际类型为Activity的Context被强引用导致的内存泄漏
        // Context可以通过实现View层的obtainContext来获取
    }

    @Override
    public void setPresenter(TaskContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showData(TaskBean taskBean) {
        tv_ShowData.setText(taskBean.getData());
    }

    @Override
    public Context obtainContext() {
        return this;
    }


    //统一通过activity或者fragment的接口处理事件监听
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_setData:
                mPresenter.fetchData();
                break;
            default:
                break;
        }
    }


}
