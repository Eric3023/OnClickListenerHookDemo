package study.hank.com.androidhookdemo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * 我们的目的：
 * 下面的代码里，有一个view被设置了点击事件
 * 我要求，
 * 在点击事件执行之前，或者之后，加入我自己的代码逻辑，不准变动原先点击事件的代码
 *
 * Hook步骤：
 * 1. 根据需求确定 要hook的对象(OnClickListener)
 * 2. 寻找要hook的对象的持有者，拿到要hook的对象(ListenerInfo)
 * 3. 定义hook的对象的代理类(ProxyOnClickListener)，并且创建该类的对象
 * 4. 使用上一步创建出来的对象，替换掉要hook的对象
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View v = findViewById(R.id.tv);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "别点啦，再点我咬你了...", Toast.LENGTH_SHORT).show();
            }
        });

        HookSetOnClickListenerHelper.hook(this, v);//这个hook的作用，是 用我们自己创建的点击事件代理对象，替换掉之前的点击事件。
        //所以，这个hook动作，必须在setOnClickListener之后，不然就不起作用
    }
}
