package com.collabera.weather.ui.dashboard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.collabera.weather.R
import com.collabera.weather.databinding.FragmentCurrentWeatherBinding
import com.collabera.weather.models.UserLocationTableModel
import com.collabera.weather.ui.dashboard.DashBoardViewModel
import com.collabera.weather.util.Constants.PrimaryEmail
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
        //val pos = arguments?.getInt(ARG_SECTION_NUMBER)
        bindObservers()
    }



    private fun bindObservers() {
        var email=viewModel.localSharedPreference.getString(PrimaryEmail)

        viewModel.weatherResponse.observe(viewLifecycleOwner) { result ->
            binding?.container?.apply {
                if(result.weather[0].icon.equals("01d")){
                    //day
                    ivWeatherSun.setImageResource(R.drawable.sunshine)
                }else{
                    //night
                    ivWeatherSun.setImageResource(R.drawable.night_ic)
                }
                tvTemperature.text="Temperature- "+result.main.temp.toString()+" ℃"
                tvDescription.text="Description - "+result.weather[0].description
                tvCountry.text="Country - "+result.sys.country
                tvCity.text="City - "+result.sys.id.toString()
                tvSunset.text="Sunset - "+viewModel.utcFormatted(result.sys.sunset)
                tvSunrise.text="Sunrise - "+viewModel.utcFormatted(result.sys.sunrise)

                // store in local db
                val userLocationTableModel=UserLocationTableModel(
                    icon = result.weather[0].icon,
                    email = email,
                    temperature = result.main.temp.toString(),
                    description = result.weather[0].description,
                    country = result.sys.country,
                    city = result.sys.id.toString(),
                    sunset = result.sys.sunset.toString(),
                    sunrise = result.sys.sunrise.toString(),
                    entryDateTime = ""
                )
                viewModel.enterUserLocation(userLocationTableModel)

            }

            Toast.makeText(activity,"WELCOME -"+email, Toast.LENGTH_LONG).show()

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