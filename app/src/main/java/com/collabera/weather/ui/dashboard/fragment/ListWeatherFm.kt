package com.collabera.weather.ui.dashboard.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.collabera.weather.databinding.FragmentListWeatherBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListWeatherFm : Fragment() {

    private var binding: FragmentListWeatherBinding?=null
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,  savedInstanceState: Bundle? ): View? {
        binding= FragmentListWeatherBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pos = arguments?.getInt(ARG_SECTION_NUMBER)
        Log.d("===>",pos.toString())
    }

    companion object {
        var ARG_SECTION_NUMBER = "section_number";
        @JvmStatic
        fun newInstance(pos: Int) =
            ListWeatherFm().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, pos)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}