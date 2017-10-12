# Android动画
实现动画方式：
1. **帧动画：** 将一个完整的动画分成一张张图片，然后再将它们连贯起来播放
**特点：** 逐帧动画由于是一张张图片，所以会导致apk大小增加，但是可以实现较难动画
2. **补间动画：** 设置初始值跟末尾值，慢慢过渡
**特点** 相对简单，视觉上变化，并不是真正位置上变化
3. **属性动画：** 也是补间动画一种，可以控制属性实现动画
**特点**弥补补间动画的缺点，位置 + 视觉同时变化，并且可以自定义插值器，实现各种效果


----------

### 属性动画
android3.0提出的全新动画。弥补补间&逐帧的缺点
#### 1.ObjectAnimator
```
//第一个参数为 view对象，第二个参数为 动画改变的类型，第三，第四个参数依次是开始透明度和结束透明度。
ObjectAnimator alpha = ObjectAnimator.ofFloat(text, "alpha",0f,1f);
alpha.setDuration(2000);//设置动画时间
alpha.setInterpolator(new DecelerateInterpolator());//设置动画插入器，减速
alpha.setRepeatCount(-1);//设置动画重复次数，这里-1代表无限
alpha.setRepeatMode(Animation.REVERSE);//设置动画循环模式。
alpha.start();//启动动画。
```
##### PropertyName（动画类型）
常见动画类型有以下几种
**translationX，translationY** 控制view平移动画
**rotation，rotationX，rotationY** 控制旋转
**scaleX，scaleY** 缩放
**alpha**  透明度 0～1
**backgroundColor**  设置背景颜色


> 可以根据设置view.setPivotX,view.setPivotY修改旋转中心或拉伸

##### Interpolator（动画插入器）
`Interpolator `被用来修饰动画效果，定义动画的变化率，可以使存在的动画效果`accelerated(加速)`，`decelerated(减速)`,`repeated(重复)`,`bounced(弹跳)`等。

**1.AccelerateDecelerateInterpolator：** 在动画开始与结束的地方速率改变比较慢，在中间的时候加速
**2.AccelerateInterpolator：** 在动画开始的地方速率改变比较慢，然后开始加速
**3.AnticipateInterpolator：** 开始的时候向后然后向前甩
**4.AnticipateOvershootInterpolator：** 开始的时候向后然后向前甩一定值后返回最后的值
**5.BounceInterpolator：** 动画结束的时候弹起
**6.CycleInterpolator：** 动画循环播放特定的次数，速率改变沿着正弦曲线
**7.DecelerateInterpolator：** 在动画开始的地方快然后慢
**8.LinearInterpolator：** 以常量速率改变
**9.OvershootInterpolator：** 向前甩一定值后再回到原来位置


##### RepeatMode（循环模式）
**RESTART：** 重新从头开始执行。
**REVERSE：** 反方向执行。
