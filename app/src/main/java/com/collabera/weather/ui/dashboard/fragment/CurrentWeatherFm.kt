package com.collabera.weather.ui.dashboard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.collabera.weather.databinding.FragmentCurrentWeatherBinding
import com.collabera.weather.ui.dashboard.DashBoardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrentWeatherFm : Fragment() {

    private var binding: FragmentCurrentWeatherBinding?=null
    private val viewModel: DashBoardViewModel by viewModels()

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,  savedInstanceState: Bundle? ): View? {
        binding=FragmentCurrentWeatherBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pos = arguments?.getInt(ARG_SECTION_NUMBER)
        bindObservers()
    }



    private fun bindObservers() {
        viewModel.weatherResponse.observe(viewLifecycleOwner) { weather ->
            binding?.container?.apply {
                tvTemperature.text=""
                tvDescription.text=""
                tvCountry.text=""
                tvCity.text=""
                tvSunset.text=""
                tvSunrise.text=""
            }

        }
    }


    //--------------------------------
    companion object {
        var ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(pos: Int) =
            CurrentWeatherFm().apply {
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