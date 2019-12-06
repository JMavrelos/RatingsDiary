package gr.blackswamp.core.widget

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import gr.blackswamp.core.R
import java.util.concurrent.atomic.AtomicInteger


class NumberPicker @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private var mView: TextView? = null
    private var mListener: OnValueChangedListener? = null
    private var mValue = AtomicInteger(0)
    private val mMin: Int
    private val mMax: Int
    private val mStep: Int
    private val mTextSize: Int
    private val mBigStep: Int

    init {
        var resource = R.layout.np_horizontal
        if (attrs == null) {
            mMin = 0
            mMax = 100
            mStep = 1
            mTextSize = 0
            mBigStep = 10
        } else {
            val typed = context.obtainStyledAttributes(attrs, R.styleable.NumberPicker)
            mMin = typed.getInt(R.styleable.NumberPicker_min, 0)
            mMax = typed.getInt(R.styleable.NumberPicker_max, 99).coerceAtLeast(mMin + 1)
            setValue(typed.getInt(R.styleable.NumberPicker_val, 0))
            mTextSize = typed.getDimensionPixelSize(R.styleable.NumberPicker_android_textSize, 0)
            val orientation = typed.getInt(R.styleable.NumberPicker_android_orientation, HORIZONTAL)
                .coerceIn(HORIZONTAL, VERTICAL)
            if (orientation == VERTICAL)
                resource = R.layout.np_vertical

            mStep = typed.getInt(R.styleable.NumberPicker_step, 1).coerceAtLeast(1)
            mBigStep = typed.getInt(R.styleable.NumberPicker_step_big, 0).coerceAtLeast(0)
            typed.recycle()
        }
        LayoutInflater.from(context).inflate(resource, this, true)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        mView = findViewById(R.id.number_picker_value)

        findViewById<View>(R.id.number_picker_increase)?.setOnClickListener { changeValue(mStep) }
        findViewById<View>(R.id.number_picker_decrease)?.setOnClickListener { changeValue(-mStep) }
        findViewById<View>(R.id.number_picker_increase_big)?.apply {
            setOnClickListener { changeValue(mBigStep) }
            visibility = if (mBigStep == 0) View.GONE else View.VISIBLE
        }
        findViewById<View>(R.id.number_picker_decrease_big)?.apply {
            setOnClickListener { changeValue(-mBigStep) }
            visibility = if (mBigStep == 0) View.GONE else View.VISIBLE
        }
        if (mTextSize > 0)
            mView?.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize.toFloat())
        updateValue()
    }


    fun changeValue(byValue: Int) {
        setValue(mValue.get() + byValue)
    }

    fun setValue(value: Int) {
        mValue.set(value.coerceIn(mMin, mMax))
        updateValue()
    }

    fun getValue(): Int = mValue.get()

    private fun updateValue() {
        val value = mValue.get()
        mListener?.onValueChanged(this, value)
        mView?.setText(value.toString())
    }

    interface OnValueChangedListener {
        fun onValueChanged(me: NumberPicker, value: Int)
    }
}