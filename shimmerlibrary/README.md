# 流光字体效果

* 库文件[shimmerlibrary](shimmerlibrary)

* 使用方式 [ShimmerTextView](app/src/main/java/com/example/test/ShimmerTextView.kt)

    ```kotlin
    //继承view或者自己编写
  class ShimmerTextView: TextView {
          //     方法
        }
    //定义变量
      val shimmer = Shimmer()
     //继承view实现onSizeChnaged函数 ,做一些配置
   
     override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
           super.onSizeChanged(w, h, oldw, oldh)
           shimmer.mPaint=paint
           //shimmer.textSize=60f
           shimmer.mViewWidth=measuredWidth
           shimmer.textSize=textSize
           shimmer.mWidthDivideBy=5
           shimmer.init()
       }
     //onDraw函数绘制
       override fun onDraw(canvas: Canvas?) {
             super.onDraw(canvas)
             shimmer.run()
             postInvalidateDelayed(100)
         }
    
    ```
    
    * 重构实现方式，具体参看[ShimmerStyle1](shimmerlibrary/src/main/java/com/example/shimmerlibrary/ShimmerStyle1.kt)
    [Shimmer1](shimmerlibrary/src/main/java/com/example/shimmerlibrary/Shimmer1.kt)

