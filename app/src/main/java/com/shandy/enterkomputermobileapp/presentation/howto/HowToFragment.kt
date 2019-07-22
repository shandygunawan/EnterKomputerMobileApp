package com.shandy.enterkomputermobileapp.presentation.howto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shandy.enterkomputermobileapp.R
import kotlinx.android.synthetic.main.fragment_howto.*

class HowToFragment : Fragment() {

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
        initChildFragment()
        initBottomNav()
    }

    /*************************************************************
     *                          Fragment                         *
     *************************************************************/
    private fun initChildFragment(){
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.layoutHowTo, HowToWebFragment())
            ?.commit()
    }

    /*************************************************************
     *                          BOTTOM NAV                       *
     *************************************************************/
    private fun initBottomNav(){
        bottomNavHowTo.selectedItemId = R.id.navBottomWebsite
        bottomNavHowTo.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navBottomWebsite -> {
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.layoutHowTo, HowToWebFragment())
                        ?.commit()
                    true
                }
                R.id.navBottomWA -> {
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.layoutHowTo, HowToWebFragment())
                        ?.commit()
                    true
                }
                else -> false
            }
        }
    }
}