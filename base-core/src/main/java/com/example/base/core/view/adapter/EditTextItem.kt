package com.example.base.core.view.adapter

import android.text.InputFilter
import android.text.InputType
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import com.example.base.core.R
import com.example.base.core.view.custom.KeyboardEditText
import kotlinx.android.synthetic.main.item_edit_text.view.*
import kotlin.math.max
import kotlin.math.min

open class EditTextItem(
    private val id: Long? = null,
    private val text: (() -> String?)? = null,
    private val hint: String? = null,
    private val maxCharacters: Int? = null,
    private val inputType: Int = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES,
    private val imeOption: Int = EditorInfo.IME_ACTION_NEXT,
    private val onTextSet: ((EditText) -> Unit)? = null,
    private val onInputCompleted: ((String?) -> Unit)? = null,
    private val onFreeTextUpdated: ((String?) -> Unit)? = null
) : Item(), KeyboardEditText.KeyboardClosedListener {

    override fun getId(): Long = id ?: hint?.hashCode()?.toLong() ?: super.getId()

    private lateinit var editText: KeyboardEditText

    override fun getLayout() = R.layout.item_edit_text

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.apply {
            editText = item_edit_text
            item_edit_text.clearAfterTextChangedListeners()

            renderInputType()
            renderImeOption()
            renderHint()
            renderMaxCharacters()
            renderPreviousText()
            renderTextChangeListener()
            renderFocusChangedListener()
            renderKeyboardClosedListener()
        }
    }

    private fun View.renderImeOption() {
        item_edit_text.imeOptions = imeOption
    }

    private fun View.renderInputType() {
        item_edit_text.inputType = inputType
    }

    private fun View.renderPreviousText() {
        item_edit_text.setText(text?.invoke())
        onTextSet?.invoke(item_edit_text)
        item_edit_text.setSelection(text?.invoke()?.length ?: 0)
    }

    private fun View.renderHint() {
        item_edit_text_input_layout.hint = hint
    }

    private fun View.renderMaxCharacters() {
        if (maxCharacters == null) {
            item_edit_text_input_layout.isCounterEnabled = false
            item_edit_text_input_layout.counterMaxLength = 0
            item_edit_text.filters = arrayOf()
        } else {
            item_edit_text_input_layout.isCounterEnabled = true
            item_edit_text_input_layout.counterMaxLength = maxCharacters
            item_edit_text.filters = arrayOf(InputFilter.LengthFilter(maxCharacters))
        }
    }

    private fun View.renderTextChangeListener() {
        item_edit_text.addAfterTextChangedListener { text ->
            val regex: Regex = "\\s+".toRegex()
            val result: String = text.toString().replace(regex, " ")

            if (text.toString() != result) {
                val position: Int = item_edit_text
                    .selectionStart - ((text?.length ?: 0) - result.length)
                item_edit_text.setText(result)
                item_edit_text.setSelection(max(0, min(position, result.length)))
            }

            onFreeTextUpdated?.invoke(result.trim())
        }
    }

    private fun View.renderKeyboardClosedListener() {
        item_edit_text.listener = this@EditTextItem
        item_edit_text.setOnEditorActionListener { _, _, _ ->
            item_edit_text.clearFocus()
            inputCompleted()
            false
        }
    }

    private fun View.renderFocusChangedListener() {
        item_edit_text.setOnFocusChangeListener { _, hasFocus -> if (!hasFocus) inputCompleted() }
    }

    override fun onKeyboardClosed() {
        editText.clearFocus()
        inputCompleted()
    }

    private fun inputCompleted() {
        onInputCompleted?.invoke(
            editText.text?.trim()?.toString().takeIf { it?.isNotBlank() == true }
        )
    }
}
