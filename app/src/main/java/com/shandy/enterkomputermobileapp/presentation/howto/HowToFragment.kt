package com.shandy.enterkomputermobileapp.presentation.howto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shandy.enterkomputermobileapp.R

class HowToFragment : Fragment(), HowToView {

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
        return inflater.inflate(R.layout.fragment_howto, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    /*************************************************************
     *                          VIEW                             *
     *************************************************************/
    override fun showLoading(isLoading: Boolean) {
        if(isLoading){

        }
        else {

        }
    }

    override fun showError(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showHowTo(category: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}