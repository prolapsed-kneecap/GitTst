package com.example.ktorf

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect

class MainFragment : Fragment() {


    companion object {
        fun newInstance() = MainFragment()
    }

    val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.getDogs()
        val view = inflater.inflate(R.layout.main_fragment, container, false)

        view.findViewById<TextView>(R.id.textView).setOnClickListener {
            viewModel.getDogs()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.stateData.collect {
                if (it != null) {
                    if (it.isSuccess) {
                        Toast.makeText(
                            requireContext(),
                            it.getOrNull().toString(),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    } else {
                        Toast.makeText(requireContext(), "Something failed", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }
    //создание проекта
    //api
    //git

}