package com.example.base.core.view.custom

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.KeyEvent
import com.google.android.material.textfield.TextInputEditText

class KeyboardEditText : TextInputEditText {

    interface KeyboardClosedListener {
        fun onKeyboardClosed()
    }

    var listener: KeyboardClosedListener? = null
    private val watchers: MutableSet<TextWatcher> = mutableSetOf()

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr)

    fun addAfterTextChangedListener(onTextChanged: (Editable?) -> Unit) {
        object : TextWatcher {
            override fun beforeTextChanged(t: CharSequence?, s: Int, c: Int, a: Int) {}
            override fun onTextChanged(t: CharSequence?, s: Int, b: Int, c: Int) {}
            override fun afterTextChanged(editable: Editable?) {
                onTextChanged(editable)
            }
        }.let { watcher ->
            watchers.add(watcher)
            super.addTextChangedListener(watcher)
        }
    }

    private fun removeListener(watcher: TextWatcher) {
        watchers.remove(watcher)
        super.removeTextChangedListener(watcher)
    }

    fun clearAfterTextChangedListeners() {
        watchers.forEach { removeListener(it) }
    }

    override fun onKeyPreIme(keyCode: Int, event: KeyEvent): Boolean {
        if (event.keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) listener?.onKeyboardClosed()
        return super.dispatchKeyEvent(event)
    }
}
