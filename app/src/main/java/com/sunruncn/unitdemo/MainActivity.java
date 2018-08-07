package com.sunruncn.unitdemo;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.android.voicedemo.activity.ActivityRecog;
import com.baidu.android.voicedemo.recognization.IStatus;
import com.baidu.tts.sample.SynthActivity;
import com.sunruncn.unitdemo.dto.AccessTokenDTO;
import com.sunruncn.unitdemo.dto.response.Actions;
import com.sunruncn.unitdemo.dto.response.ChatResponse;
import com.sunruncn.unitdemo.network.HttpServer;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ico.ico.adapter.BaseAdapter;
import ico.ico.ico.BaseFragActivity;
import ico.ico.util.DialogUtil;
import ico.ico.util.log;

public class MainActivity extends BaseFragActivity implements IStatus {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.et_data)
    EditText etData;
    @BindView(R.id.btn_send)
    Button btnSend;
    @BindView(R.id.btn_send1)
    Button btnSend1;
    MyAdapter myAdapter;
    ActivityRecog activityRecog;
    SynthActivity synthActivity;
    HttpServer.MyHttpCallback chatCallback = new HttpServer.MyHttpCallback() {
        @Override
        public boolean chat(int statusCode, ChatResponse baseDTO) {
            if (statusCode != 200 || baseDTO == null) {
                return super.chat(statusCode, baseDTO);
            }
            if (baseDTO.getError_code() != 0) {
                Toast.makeText(mActivity, baseDTO.getError_msg(), Toast.LENGTH_SHORT).show();
                return true;
            }
            Actions[] list = baseDTO.getResult().getResponse().getAction_list();
            Chat chat = new Chat(list[list.length - 1].getSay(), 1, baseDTO);
            myAdapter.addData(0, chat);
            myAdapter.notifyDataSetChanged();
            //合成并播放
            synthActivity.speak(chat.data);
            return true;
        }
    };
    /**
     * 语音回调
     * {@link com.baidu.android.voicedemo.recognization.MessageStatusRecogListener}
     */
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//        if (txtLog != null && msg.obj != null) {
//            txtLog.append(msg.obj.toString() + "\n-----------\n");
//        }
            log.w("===" + msg);
            switch (msg.what) { // 处理MessageStatusRecogListener中的状态回调
                case STATUS_FINISHED:
                    if (msg.arg2 == 1) {
//                    txtResult.setText(msg.obj.toString());
                        myAdapter.addData(0, new Chat(msg.obj.toString(), 0));
                        myAdapter.notifyDataSetChanged();
                        try {
                            HttpServer.chat(mActivity, chatCallback, SPU.getAccessToken(mActivity), msg.obj.toString(), myAdapter.getItemCount() + "");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(mActivity, "程序出错", Toast.LENGTH_SHORT).show();
                        }
                    }
                    activityRecog.status = msg.what;
                    updateBtnTextByStatus();
                    break;
                case STATUS_NONE:
                case STATUS_READY:
                case STATUS_SPEAKING:
                case STATUS_RECOGNITION:
                    activityRecog.status = msg.what;
                    updateBtnTextByStatus();
                    break;
                default:
                    break;

            }
        }

        public void updateBtnTextByStatus() {
            switch (activityRecog.status) {
                case STATUS_NONE:
                    btnSend1.setText("开始录音");
                    btnSend1.setEnabled(true);
                    break;
                case STATUS_WAITING_READY:
                case STATUS_READY:
                case STATUS_SPEAKING:
                case STATUS_RECOGNITION:
                    btnSend1.setText("停止录音");
                    btnSend1.setEnabled(true);
                    break;

                case STATUS_STOPPED:
                    btnSend1.setText("取消整个识别过程");
                    btnSend1.setEnabled(true);
                    break;
                default:
                    break;
            }
        }
    };
    HttpServer.MyHttpCallback tokenCallback = new HttpServer.MyHttpCallback() {
        @Override
        public boolean token(int statusCode, AccessTokenDTO accessTokenDTO) {
            if (statusCode != 200 || accessTokenDTO == null) {
                return super.token(statusCode, accessTokenDTO);
            }
            if (TextUtils.isEmpty(accessTokenDTO.getAccess_token())) {
                DialogUtil.createAlert(mActivity, null, accessTokenDTO.getError_description(), "确定", null).show();
            } else {
                SPU.setAccessToken(mActivity, accessTokenDTO.getAccess_token());
                SPU.setExpaires(mActivity, new Date().getTime() + accessTokenDTO.getExpires_in());
            }
            return true;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        recyclerView.setAdapter(myAdapter = new MyAdapter(this));


        if (TextUtils.isEmpty(SPU.getAccessToken(mActivity))) {
            long expaires = SPU.getExpaires(mActivity);
            Date date = new Date();
            if (expaires == -1l || date.getTime() < expaires) {
                HttpServer.token(this, tokenCallback);
            }
        }

        activityRecog = new ActivityRecog(mActivity, handler);
        synthActivity = new SynthActivity(mActivity, handler);
    }

    @OnClick(R.id.btn_send)
    public void onClick() {
        if (etData.getText().toString().length() == 0) {
            Toast.makeText(this, "请输入数据", Toast.LENGTH_SHORT).show();
            return;
        }
        String data = etData.getText().toString();
        Chat chat = new Chat(data, 0);
        try {
            HttpServer.chat(this, chatCallback, SPU.getAccessToken(mActivity), data, myAdapter.getItemCount() + "");
            myAdapter.addData(0, chat);
            myAdapter.notifyDataSetChanged();
            etData.setText("");
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "程序出错", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btn_send1)
    public void onClick1() {
        activityRecog.start(mActivity);
    }

    class Chat {
        String data;
        int from;//0自己1AI
        ChatResponse chatResponse;//from为1时进行保存

        public Chat(String data, int from) {
            this.data = data;
            this.from = from;
        }

        public Chat(String data, int from, ChatResponse chatResponse) {
            this.data = data;
            this.from = from;
            this.chatResponse = chatResponse;
        }
    }

    class MyAdapter extends BaseAdapter<Chat, BaseAdapter.BaseViewHolder> {

        public MyAdapter(Context context) {
            super(context, R.layout.item_chat);
        }

        public MyAdapter(Context context, int count) {
            super(context, R.layout.item_chat, count);
        }

        public MyAdapter(Context context, List<Chat> strings) {
            super(context, R.layout.item_chat, strings);
        }

        @Override
        protected void onWidgetInit(BaseViewHolder holder, int position) {
            super.onWidgetInit(holder, position);
            holder.setText(R.id.text, holder.itemData.data);
            if (holder.itemData.from == 0) {
                holder.setTextColor(R.id.text, getResources().getColor(R.color.colorPrimaryDark));
            } else {
                holder.setTextColor(R.id.text, getResources().getColor(R.color.colorAccent));
            }
        }
    }

}
