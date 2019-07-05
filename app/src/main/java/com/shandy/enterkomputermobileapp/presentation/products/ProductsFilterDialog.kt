package com.shandy.enterkomputermobileapp.presentation.products

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.shandy.enterkomputermobileapp.R
import com.shandy.enterkomputermobileapp.utils.Constants
import kotlinx.android.synthetic.main.dialog_filter_products.view.*

class ProductsFilterDialog(paramFrag: ProductsFragment) : DialogFragment(){

    /*************************************************************
     *                          VARIABLES                        *
     *************************************************************/
    private val frag: ProductsFragment = paramFrag
    private lateinit var v : View
    private lateinit var listener: ProductsFilterDialogListener
    private lateinit var filters: HashMap<String, String>

    /*************************************************************
     *                          INTERFACES                        *
     *************************************************************/
    interface ProductsFilterDialogListener{
        fun onProductsFilterDialogPositiveClick(dialog: ProductsFilterDialog)
        fun onProductsFilterDialogNegativeClick(dialog: ProductsFilterDialog)
    }

    /*************************************************************
     *                          LIFECYCLE                        *
     *************************************************************/
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)

        initLayout()

        val builder: AlertDialog.Builder = AlertDialog.Builder(activity!!)
            .setView(v)

        initViews()

        val dialog = builder.create()
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)

        return dialog
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        try{
            listener = frag
        } catch(e : ClassCastException){
            throw ClassCastException((frag.tag.toString() +
                    " must implement ProductsFilterDialogListener"))
        }
    }

    /*************************************************************
     *                          INITs                            *
     *************************************************************/
    private fun initLayout(){
        v = requireActivity().layoutInflater
            .inflate(R.layout.dialog_filter_products, null)
    }

    private fun initViews(){
        v.tvFilterApply.setOnClickListener {
            setFilters()
            listener.onProductsFilterDialogPositiveClick(this)
        }
        v.tvFilterCancel.setOnClickListener {
            listener.onProductsFilterDialogNegativeClick(this)
        }
    }


    /*************************************************************
     *                       SETTER/GETTER                       *
     *************************************************************/
    fun getFilters() : HashMap<String, String>{
        return filters
    }

    private fun setFilters(){
        filters = HashMap()
        filters[Constants.Filters.FILTER_PRODUCTS_NAME] = v.etFilterName.text.toString()
        filters[Constants.Filters.FILTER_PRODUCTS_BRAND] = v.etFilterBrand.text.toString()
    }
}