package com.shandy.enterkomputermobileapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shandy.enterkomputermobileapp.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment: Fragment(), HomeView {

    /*************************************************************
     *                          VARIABLES                        *
     *************************************************************/

    /*************************************************************
     *                          LIFECYCLE                        *
     *************************************************************/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wvHome.loadUrl("http://www.enterkomputer.com")
    }

    /*************************************************************
     *                          VIEW                             *
     *************************************************************/
    override fun showLoading(isLoading: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}