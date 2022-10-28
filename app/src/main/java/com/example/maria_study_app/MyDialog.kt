package com.example.maria_study_app

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.maria_study_app.databinding.DialogLayoutBinding

class MyDialog : DialogFragment() {

    /**
     * Поработать с кастомным диалогом, изменить его размер (д/ш)
     * Прочитать про google maps в андроид приложениях, подключить их библиотеку,
     * !!! зарегистрироваться и получить токен
     * раскидать по папкам классы
     */

    private lateinit var binding: DialogLayoutBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        this.isCancelable = false

        return super.onCreateDialog(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }


    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

    private fun initViews(){
        binding.dialogButton.setOnClickListener{
            dismiss()
        }
    }
}