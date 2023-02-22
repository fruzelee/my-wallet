package com.crevado.fr.mywallet.utils.pin_view

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.crevado.fr.mywallet.R
import java.util.*
import java.util.regex.Pattern

class PinTextView : FrameLayout {

    private var itemViews: MutableList<ItemView>? = null
    private var pinChildEditText: PinChildEditText? = null
    var pinListener: PinListener? = null

    private var length: Int = 0

    private val filter: InputFilter
        get() = InputFilter { source, start, end, _, _, _ ->
            for (i in start until end) {
                if (!Pattern.compile(
                        PATTERN
                    )
                        .matcher(source[i].toString())
                        .matches()
                ) {
                    return@InputFilter ""
                }
            }
            null
        }

    val pin: String?
        get() = pinChildEditText?.text?.toString()

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        val styles = context.obtainStyledAttributes(attrs, R.styleable.PinTextView)
        styleEditTexts(styles, attrs)
        styles.recycle()
    }

    private fun styleEditTexts(styles: TypedArray, attrs: AttributeSet?) {
        length = styles.getInt(R.styleable.PinTextView_length, DEFAULT_LENGTH)
        generateViews(styles, attrs)
    }

    private fun generateViews(styles: TypedArray, attrs: AttributeSet?) {
        itemViews = ArrayList()
        if (length > 0) {
            val pin = styles.getString(R.styleable.PinTextView_pin)
            val width = styles.getDimension(
                R.styleable.PinTextView_width, Utils.getPixels(
                    context,
                    DEFAULT_WIDTH
                ).toFloat()
            ).toInt()
            val height = styles.getDimension(
                R.styleable.PinTextView_height, Utils.getPixels(
                    context,
                    DEFAULT_HEIGHT
                ).toFloat()
            ).toInt()
            val space = styles.getDimension(
                R.styleable.PinTextView_box_margin, Utils.getPixels(
                    context,
                    DEFAULT_SPACE
                ).toFloat()
            ).toInt()
            val spaceLeft = styles.getDimension(
                R.styleable.PinTextView_box_margin_left, Utils.getPixels(
                    context,
                    DEFAULT_SPACE_LEFT
                ).toFloat()
            ).toInt()
            val spaceRight = styles.getDimension(
                R.styleable.PinTextView_box_margin_right, Utils.getPixels(
                    context,
                    DEFAULT_SPACE_RIGHT
                ).toFloat()
            ).toInt()
            val spaceTop = styles.getDimension(
                R.styleable.PinTextView_box_margin_top, Utils.getPixels(
                    context,
                    DEFAULT_SPACE_TOP
                ).toFloat()
            ).toInt()
            val spaceBottom = styles.getDimension(
                R.styleable.PinTextView_box_margin_bottom, Utils.getPixels(
                    context,
                    DEFAULT_SPACE_BOTTOM
                ).toFloat()
            ).toInt()
            val params = LinearLayout.LayoutParams(width, height)
            if (space > 0) {
                params.setMargins(space, space, space, space)
            } else {
                params.setMargins(spaceLeft, spaceTop, spaceRight, spaceBottom)
            }

            val editTextLayoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            editTextLayoutParams.gravity = Gravity.CENTER
            pinChildEditText = PinChildEditText(context)
            pinChildEditText?.filters = arrayOf(filter, InputFilter.LengthFilter(length))
            setTextWatcher(pinChildEditText)
            addView(pinChildEditText, editTextLayoutParams)


            val linearLayoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            val linearLayout = LinearLayout(context)

            addView(linearLayout, linearLayoutParams)

            for (i in 0 until length) {
                val itemView = ItemView(context, attrs)
                itemView.setViewState(ItemView.INACTIVE)
                linearLayout.addView(itemView, i, params)
                itemViews?.add(itemView)
            }
            if (pin != null) {
                setPIN(pin)
            } else {
                setPIN("")
            }
        } else {
            throw IllegalStateException("Please specify the length of the pin view")
        }
    }

    private fun setTextWatcher(pinChildEditText: PinChildEditText?) {
        pinChildEditText?.addTextChangedListener(object : TextWatcher {
            /**
             * @param s
             * @param start
             * @param count
             * @param after
             */
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            /**
             * @param s
             * @param start
             * @param before
             * @param count
             */
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                pinListener?.let { otpListener ->
                    otpListener.onInteractionListener()
                    if (s.length == length) {
                        otpListener.onOTPComplete(s.toString())
                    }
                }
                setPIN(s)
                setFocus(s.length)
            }

            override fun afterTextChanged(s: Editable) {

            }
        })
    }

    private fun setFocus(length: Int) {
        itemViews?.let { itemViews ->
            for (i in itemViews.indices) {
                if (i == length) {
                    itemViews[i].setViewState(ItemView.ACTIVE)
                } else {
                    itemViews[i].setViewState(ItemView.INACTIVE)
                }
            }
            if (length == itemViews.size) {
                itemViews[itemViews.size - 1].setViewState(ItemView.ACTIVE)
            }
        }
    }

    fun setPIN(s: CharSequence) {
        itemViews?.let { itemViews ->
            for (i in itemViews.indices) {
                if (i < s.length) {
                    itemViews[i].setText(s[i].toString())
                } else {
                    itemViews[i].setText("")
                }
            }
        }
    }

    fun requestFocusPIN() {
        pinChildEditText?.requestFocus()
    }

    fun showError() {
        itemViews?.let { itemViews ->
            for (itemView in itemViews) {
                itemView.setViewState(ItemView.ERROR)
            }
        }
    }

    fun resetState() {
        pin?.let {
            setFocus(it.length)
        }
    }

    fun showSuccess() {
        itemViews?.let { itemViews ->
            for (itemView in itemViews) {
                itemView.setViewState(ItemView.SUCCESS)
            }
        }
    }

    fun setPIN(pin: String) {
        pinChildEditText?.setText(pin)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun setOnTouchListener(l: OnTouchListener) {
        super.setOnTouchListener(l)
        pinChildEditText?.setOnTouchListener(l)
    }

    companion object {

        private const val DEFAULT_LENGTH = 4
        private const val DEFAULT_HEIGHT = 48
        private const val DEFAULT_WIDTH = 48
        private const val DEFAULT_SPACE = -1
        private const val DEFAULT_SPACE_LEFT = 4
        private const val DEFAULT_SPACE_RIGHT = 4
        private const val DEFAULT_SPACE_TOP = 4
        private const val DEFAULT_SPACE_BOTTOM = 4

        private const val PATTERN = "[1234567890]*"
    }
}
