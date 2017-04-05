# CardView
类似小票效果的卡片列表，并且可以修改阴影颜色

# Usage
可以直接引用CardView
```
CardView cardView=new CardView(context);
//修改CardView的主题颜色
cardView.changeTheme(0xff01a3a1);
```
# 原理
用paint.setShadowLayer来设置阴影颜色和尺寸，进行阴影绘制，用path来画出drawable的形状。
封装了changeTheme的方法，可以直接设置主题的颜色。

本示例中为了更好地实现效果，用了```TopDrawable、CenterDrawable、BottomDrawable```三部分拼接而成，也可以用来实现打印小票的视觉效果。另外添加了一个类似<b>FloatingButton</b>效果的```CircleShadowDrawable```。
# 效果图
<div>
<img hspace="20" src="https://github.com/vivian8725118/ShadeDemo/blob/master/art/S61201-155859.jpg" width="40%"/>
<img src="https://github.com/vivian8725118/ShadeDemo/blob/master/art/S61201-163058.jpg" width="40%"/>
</div>
