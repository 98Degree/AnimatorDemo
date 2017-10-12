package cn.com.anim.demo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_alpha;

    private ImageView iv_image;

    private Button btn_rotation;

    private Button btn_translation;

    private Button btn_scale;

    private Button btn_set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_alpha = (Button) this.findViewById(R.id.btn_alpha);
        iv_image = (ImageView) this.findViewById(R.id.iv_image);
        btn_rotation = (Button) this.findViewById(R.id.btn_rotation);
        btn_translation = (Button) this.findViewById(R.id.btn_translation);
        btn_scale = (Button) this.findViewById(R.id.btn_scale);
        btn_set = (Button) this.findViewById(R.id.btn_set);

        btn_alpha.setOnClickListener(this);
        btn_rotation.setOnClickListener(this);
        btn_scale.setOnClickListener(this);
        btn_translation.setOnClickListener(this);
        btn_set.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_alpha:
                //透明
                setAlpha();
                break;
            case R.id.btn_rotation:
                //旋转
                setRotation();
                break;
            case R.id.btn_translation:
                //位移
                setTranslation();
                break;
            case R.id.btn_scale:
                //缩放
                setScale();
                break;
            case R.id.btn_set:
                //组合动画
                setGroup();
                break;
        }
    }


    private void setAlpha(){
        ObjectAnimator objectAnimator =ObjectAnimator.ofFloat(iv_image, "alpha", 1f, 0f,1f,0.5f,1f,0f);
        objectAnimator.setDuration(5000);
//        objectAnimator.setRepeatCount(-1);    //执行动画次数 -1表示循环
        objectAnimator.start();
    }


    private void setRotation(){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv_image,"rotationY",0f,100f);
        objectAnimator.setDuration(1000);
        iv_image.setPivotX(100);//view.setPivotX,view.setPivotY修改旋转中心
        objectAnimator.start();
    }


    private void setTranslation(){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv_image,"translationX",0f,300f,0f);
        objectAnimator.setInterpolator(new BounceInterpolator()); //设置动画插入
        objectAnimator.setDuration(6000);
        objectAnimator.start();
    }

    private void setScale(){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv_image,"scaleX",1f,3f);
        objectAnimator.setDuration(2000);
        objectAnimator.setRepeatCount(-1);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART); //循环模式RESTART：重新从头开始执行。REVERSE：反方向执行。
        iv_image.setPivotX(-100);
        objectAnimator.start();

//        objectAnimator.end();     //执行完动画停止
//        objectAnimator.cancel();  //立即停止动画留在原地

        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                //动画开始
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                //动画结束
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                //动画被取消
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                // 动画重复
            }
        });
    }

    private void setGroup(){
        ObjectAnimator objectAnimator =ObjectAnimator.ofFloat(iv_image, "alpha", 1f, 0f,1f);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(iv_image,"translationX",0f,300f,0f);
        AnimatorSet set = new AnimatorSet();
        set.play(objectAnimator).with(objectAnimator2);
        set.setDuration(4000); //可以单独设置时间
        set.start();
    }


}
