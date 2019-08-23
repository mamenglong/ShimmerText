package com.example.test

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_stage.*
import java.io.Serializable
import java.util.*
import kotlin.concurrent.timer

class StageActivity : AppCompatActivity() {

    private lateinit var mArgParams: ActivityParams
    private lateinit var mTypeMap: MutableMap<String, () -> Unit>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mTypeMap = mutableMapOf(
            TYPE_TEXT_CLOCK to { caseTextClock() }
//            TYPE_SHADOW_LAYOUT to { caseShadowLayout() },
//            TYPE_RADAR_VIEW to { caseRadarView() }
        )

        //处理参数数据
//        mArgParams = if (savedInstanceState != null) {
//            savedInstanceState.getSerializable(ARG_PARAMS) as ActivityParams
//        } else {
//            intent.getSerializableExtra(ARG_PARAMS) as ActivityParams
//        }
//
//        mTypeMap[mArgParams.showType]?.invoke()
        caseTextClock()
    }

    //********************************
    //* 文字时钟
    //********************************
    private var mTimer: Timer? = null

    private fun caseTextClock() {
        setContentView(R.layout.activity_stage)

        mTimer = timer(period = 1000) {
            runOnUiThread {
                stage_textClock.doInvalidate()
            }
        }

    }

    //********************************
    //* 阴影布局
    //********************************
    private fun caseShadowLayout() {
        setContentView(R.layout.activity_stage_shadow_layout)
    }

    //********************************
    //* 雷达图
    //********************************
    val oldProgressList = listOf(20, 20, 20, 20, 20, 20)
    //    val progressList = listOf(100, 20, 30, 40, 50, 60)
    val progressList = mutableListOf(20, 20, 20, 20, 20, 20)
    val tempProgressList = listOf(100, 20, 30, 40, 50, 60)
//    private fun caseRadarView() {
//        setContentView(R.layout.activity_stage_radar_view)
//        val textList = listOf(
//            "数学抽象",
//            "逻辑推理",
//            "数据分析",
//            "数学建模",
//            "直观想象",
//            "数学运算"
//        )
//        stage_radarView.setTextArray(textList)
//        //demo：各属性动画一起执行
////        stage_radarView.setOldProgressList(oldProgressList)
//        stage_radarView.setProgressList(progressList)
////        stage_radarView.doInvalidate()
////        stage_radarView.setOnClickListener {
////            stage_radarView.doInvalidate()
////        }
//
//        //demo：各属性动画依次执行
//        doAnimSuccessive(0)
//        stage_radarView.setOnClickListener {
//            progressList.forEachIndexed { index, _ ->
//                progressList[index] = oldProgressList[index]
//            }
//            doAnimSuccessive(0)
//        }
//    }
//
//    private fun doAnimSuccessive(index: Int) {
//        if (progressList.size == tempProgressList.size && index >= 0 && index < progressList.size) {
//            progressList[index] = tempProgressList[index]
//            stage_radarView.setOldProgressList(oldProgressList)
//            stage_radarView.setProgressList(progressList)
//
//            stage_radarView.doInvalidate(index) {
//                doAnimSuccessive((it + 1))
//            }
//        }
//    }

    override fun onDestroy() {
        super.onDestroy()
        mTimer?.cancel()
    }

    /**
     * 保存参数数据
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (::mArgParams.isInitialized) {
            outState?.putSerializable(ARG_PARAMS, mArgParams)
        }
    }

    companion object {
        private const val ARG_PARAMS = "ARG_PARAMS"

        const val TYPE_TEXT_CLOCK = "TYPE_TEXT_CLOCK"
        const val TYPE_SHADOW_LAYOUT = "TYPE_SHADOW_LAYOUT"
        const val TYPE_RADAR_VIEW = "TYPE_RADAR_VIEW"

        @JvmStatic
        fun navigate(context: Context, params: ActivityParams) {
            val intent = Intent(context, StageActivity::class.java).apply {
                if (context !is Activity) {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
            }
            intent.putExtra(ARG_PARAMS, params)
            context.startActivity(intent)
        }
    }

    data class ActivityParams(
        val showType: String
    ) : Serializable


}
