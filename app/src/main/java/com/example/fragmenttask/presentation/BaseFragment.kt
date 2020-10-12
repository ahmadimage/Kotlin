package com.example.fragmenttask.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fragmenttask.base.BaseViewModel
import com.example.fragmenttask.domain.usecase.base.Outcome
import com.example.fragmenttask.presentation.main.MainActivity

abstract class BaseFragment : Fragment() {
    private var baseViewModel: BaseViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseViewModel = getViewModel()
        baseViewModel?.outcomeLiveData?.observe(viewLifecycleOwner, {
            when (it) {
                is Outcome.Start -> {
                    (activity as MainActivity)
                }
                is Outcome.End -> {
                    (activity as MainActivity)
                }
                is Outcome.Failure -> {
                    Toast.makeText(
                        activity,
                        "Some error occurred, Please try later",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Outcome.NetworkError -> {
                    Toast.makeText(
                        activity,
                        "Please check your internet and try later",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        })
    }

    abstract fun getViewModel(): BaseViewModel?
}