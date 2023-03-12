package com.areeb.fakejsoon.ui.home.dialog

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.areeb.fakejsoon.databinding.BottomSheetDialogBinding
import com.areeb.fakejsoon.ui.home.viewModel.PostViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PostDialog @Inject constructor() : BottomSheetDialogFragment(), View.OnClickListener {
    private val model: PostViewModel by activityViewModels()
    lateinit var binding: BottomSheetDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = BottomSheetDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set height to half the screen height
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        val screenHeight = displayMetrics.heightPixels
        view.layoutParams.height = screenHeight / 2
        onItemViewClick()
    }

    private fun onItemViewClick() {
        binding.let {
            it.postButton.setOnClickListener(this)
        }
    }

    override fun onClick(view: View?) {
        binding.let {
            when (view?.id) {
                it.postButton.id -> {
                    model.postUser(
                        it.userNameEditText.text.toString(),
                        it.userJobEditText.toString(),
                    )
                    dialog?.dismiss()
                }
                else -> {
                    Log.e("error", "some error occur")
                }
            }
        }
    }
}
