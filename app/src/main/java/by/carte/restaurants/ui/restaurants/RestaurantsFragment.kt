package by.carte.restaurants.ui.restaurants


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import by.carte.restaurants.R

class RestaurantsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_main, container, false)!!

    companion object {
        fun newInstance(): RestaurantsFragment = RestaurantsFragment().apply {
            arguments = Bundle()
        }
    }
}
