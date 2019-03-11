package es.pue.intentspractice.presentationlayer.controllers.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText

class EditTextNoKB(context: Context?, attrs: AttributeSet?): EditText(context, attrs) {
    override fun onCheckIsTextEditor(): Boolean {
        return false
    }
}