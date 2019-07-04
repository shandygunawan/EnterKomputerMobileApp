package com.shandy.enterkomputermobileapp.presentation.howto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.shandy.enterkomputermobileapp.R
import com.shandy.enterkomputermobileapp.presentation.home.HomeFragment
import com.shandy.enterkomputermobileapp.utils.Constants
import kotlinx.android.synthetic.main.fragment_howto.*
import kotlinx.android.synthetic.main.fragment_howto_website.*

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