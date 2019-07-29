package com.example.test.activity

import android.app.PendingIntent.getActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.ColorInt
import com.example.test.R
import com.jrummyapps.android.colorpicker.ColorPickerDialogListener
import com.dingmouren.colorpicker.OnColorPickerListener
import android.R.attr.colorPrimary
import android.graphics.Bitmap
import android.graphics.Color
import com.dingmouren.colorpicker.ColorPickerDialog
import kotlinx.android.synthetic.main.activity_color_picker.*


class ColorPickerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_picker)


        one.setOnClickListener {
            /*
* 创建支持透明度的取色器
* @param context 宿主Activity
* @param defauleColor 默认的颜色
* @param isSupportAlpha 颜色是否支持透明度
* @param listener 取色器的监听器
*/
            var mColorPickerDialog = ColorPickerDialog(
                this,
                resources.getColor(R.color.colorPrimary),
                supportAlpha,
                mOnColorPickerListener
            ).show()
        }
        two.setOnClickListener {
            opeAdvancenDialog()
        }
    }

    val DIALGE_ID = 0
    private fun opeAdvancenDialog() {
        val color =0 //ColorPickerViewModel.getColor()
        //传入的默认color
        val colorPickerDialog =  com.jrummyapps.android.colorpicker.ColorPickerDialog.newBuilder().setColor(color)
            .setDialogTitle(R.string.color_picker)
            //设置dialog标题
            .setDialogType( com.jrummyapps.android.colorpicker.ColorPickerDialog.TYPE_CUSTOM)
            //设置为自定义模式
            .setShowAlphaSlider(true)
            //设置有透明度模式，默认没有透明度
            .setDialogId(DIALGE_ID)
            //设置Id,回调时传回用于判断
            .setAllowPresets(false)
            //不显示预知模式
            .create()
        //Buider创建
        colorPickerDialog.setColorPickerDialogListener(pickerDialogListener)
        //设置回调，用于获取选择的颜色
        colorPickerDialog.show(this.getFragmentManager(), "color-picker-dialog")
    }

    private val pickerDialogListener = object : ColorPickerDialogListener {
        override fun onColorSelected(dialogId: Int, @ColorInt color: Int) {
            two.setBackgroundColor(color)
            if (dialogId == DIALGE_ID) {
               // colorPickerViewModel.setColor(color)
            }
        }

        override fun onDialogDismissed(dialogId: Int) {

        }
    }

    private val supportAlpha: Boolean = false//是否支持透明度


    //取色器的监听器
    private val mOnColorPickerListener = object : OnColorPickerListener {
        override fun onColorCancel(dialog: ColorPickerDialog) {//取消选择的颜色

        }

        override fun onColorChange(dialog: ColorPickerDialog, color: Int) {//实时监听颜色变化

        }

        override fun onColorConfirm(dialog: ColorPickerDialog, color: Int) {//确定的颜色
            one.setBackgroundColor(color)
        }
    }
}
