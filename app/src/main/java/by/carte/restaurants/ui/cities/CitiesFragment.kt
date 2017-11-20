package by.carte.restaurants.ui.cities


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import by.carte.restaurants.R

class CitiesFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_cities, container, false)!!

    companion object {
        fun newInstance(): CitiesFragment = CitiesFragment().apply {
            arguments = Bundle()
        }
    }
}
