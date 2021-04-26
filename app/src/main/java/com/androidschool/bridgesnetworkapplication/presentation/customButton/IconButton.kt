package com.androidschool.bridgesnetworkapplication.presentation.customButton

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.androidschool.bridgesnetworkapplication.R

class IconButton @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    private val titleTextView: TextView
    private val iconImageView: ImageView
    private val parentView: View

    var title: String? = null
        set(value) {
            titleTextView.text = value
            field = value
        }
    var icon: Drawable? = null

    private lateinit var onClickListener: () -> Unit

    init {
        View.inflate(context, R.layout.custom_button_layout, this)

        titleTextView = findViewById(R.id.text_title_button)
        iconImageView = findViewById(R.id.icon_bell_button)
        parentView = findViewById(R.id.rootView)

        context.theme.obtainStyledAttributes(attributeSet, R.styleable.IconButton, 0, 0)
            .apply {
                try {
                    title = getString(R.styleable.IconButton_iconButton_text)
                    icon = getDrawable(R.styleable.IconButton_icon_button_icon)
                } finally {
                    recycle()
                }
            }
        titleTextView.text = title
        iconImageView.setImageDrawable(icon)

        parentView.setOnClickListener {
            if (this::onClickListener.isInitialized) {
                onClickListener()
            }
        }
    }

    fun setOnClickListener(onClick: () -> Unit) {
        onClickListener = onClick
    }
}