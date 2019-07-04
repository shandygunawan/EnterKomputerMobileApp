package com.shandy.enterkomputermobileapp.presentation.howto

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.shandy.enterkomputermobileapp.R
import kotlinx.android.synthetic.main.fragment_howto_website.*

class HowToWebFragment() : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_howto_website, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        optimizeImageViews()
        animateViewWebsite()
    }

    private fun optimizeImageViews(){
        if(Build.VERSION.SDK_INT >= 23){
            view?.let {
                Glide.with(it).load(resources.getDrawable(R.drawable.icon_1motherboard_colored, it.context.theme)).into(ivHowToIcon1)
                Glide.with(it).load(resources.getDrawable(R.drawable.icon_2cpu_colored, it.context.theme)).into(ivHowToIcon2)
                Glide.with(it).load(resources.getDrawable(R.drawable.icon_3gpu_colored, it.context.theme)).into(ivHowToIcon3)
                Glide.with(it).load(resources.getDrawable(R.drawable.icon_4cooler_colored, it.context.theme)).into(ivHowToIcon4)
                Glide.with(it).load(resources.getDrawable(R.drawable.icon_5psu_colored, it.context.theme)).into(ivHowToIcon5)
                Glide.with(it).load(resources.getDrawable(R.drawable.icon_6ram_colored, it.context.theme)).into(ivHowToIcon6)
                Glide.with(it).load(resources.getDrawable(R.drawable.img_howto_1, it.context.theme)).into(ivHowToText1)
                Glide.with(it).load(resources.getDrawable(R.drawable.img_howto_2, it.context.theme)).into(ivHowToText2)
                Glide.with(it).load(resources.getDrawable(R.drawable.img_howto_3, it.context.theme)).into(ivHowToText3)
                Glide.with(it).load(resources.getDrawable(R.drawable.img_howto_4, it.context.theme)).into(ivHowToText4)
                Glide.with(it).load(resources.getDrawable(R.drawable.img_howto_5, it.context.theme)).into(ivHowToText5)
                Glide.with(it).load(resources.getDrawable(R.drawable.img_howto_6, it.context.theme)).into(ivHowToText6)
            }
        }
        else {
            view?.let {
                Glide.with(it).load(resources.getDrawable(R.drawable.icon_1motherboard_colored)).into(ivHowToIcon1)
                Glide.with(it).load(resources.getDrawable(R.drawable.icon_2cpu_colored)).into(ivHowToIcon2)
                Glide.with(it).load(resources.getDrawable(R.drawable.icon_3gpu_colored)).into(ivHowToIcon3)
                Glide.with(it).load(resources.getDrawable(R.drawable.icon_4cooler_colored)).into(ivHowToIcon4)
                Glide.with(it).load(resources.getDrawable(R.drawable.icon_5psu_colored)).into(ivHowToIcon5)
                Glide.with(it).load(resources.getDrawable(R.drawable.icon_6ram_colored)).into(ivHowToIcon6)
                Glide.with(it).load(resources.getDrawable(R.drawable.img_howto_1)).into(ivHowToText1)
                Glide.with(it).load(resources.getDrawable(R.drawable.img_howto_2)).into(ivHowToText2)
                Glide.with(it).load(resources.getDrawable(R.drawable.img_howto_3)).into(ivHowToText3)
                Glide.with(it).load(resources.getDrawable(R.drawable.img_howto_4)).into(ivHowToText4)
                Glide.with(it).load(resources.getDrawable(R.drawable.img_howto_5)).into(ivHowToText5)
                Glide.with(it).load(resources.getDrawable(R.drawable.img_howto_6)).into(ivHowToText6)
            }
        }

    }

    private fun animateViewWebsite(){
        val transLeft: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_translate_left)
        val transRight: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_translate_right)
        layoutHowToWeb1.layoutAnimation = transLeft
        layoutHowToWeb2.layoutAnimation = transRight
        layoutHowToWeb3.layoutAnimation = transLeft
        layoutHowToWeb4.layoutAnimation = transRight
        layoutHowToWeb5.layoutAnimation = transLeft
        layoutHowToWeb6.layoutAnimation = transRight
    }
}