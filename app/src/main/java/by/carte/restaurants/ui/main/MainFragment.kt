package by.carte.restaurants.ui.main


import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import by.carte.restaurants.R

class MainFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle) =
            inflater.inflate(R.layout.fragment_main, container, false)!!

    companion object {
        fun newInstance(): MainFragment = MainFragment().apply {
            arguments = Bundle()
        }
    }
}
