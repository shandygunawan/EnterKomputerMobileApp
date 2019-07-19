package com.shandy.enterkomputermobileapp.presentation.product

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.shandy.enterkomputermobileapp.R
import com.shandy.enterkomputermobileapp.utils.Constants
import kotlinx.android.synthetic.main.dialog_sort_products.view.*

class ProductSortDialog(paramFrag : ProductFragment) : DialogFragment() {

    /*************************************************************
     *                          VARIABLES                        *
     *************************************************************/
    private val frag: ProductFragment = paramFrag
    private lateinit var v: View
    private var sortCategories = ArrayList<String>()
    private var sortMode = ArrayList<String>()
    private lateinit var listener: ProductsSortDialogListener
    private var selectedCategory : String = Constants.Sort.SORT_PRODUCT_NAME
    private var selectedMode: String = Constants.Sort.SORT_MODE_ASC

    /*************************************************************
     *                          INTERFACES                        *
     *************************************************************/
    interface ProductsSortDialogListener {
        fun onProductsSortDialogPositiveClick(dialog : ProductSortDialog)
        fun onProductsSortDialogNegativeClick(dialog: ProductSortDialog)
    }

    /*************************************************************
     *                          LIFECYCLE                        *
     *************************************************************/
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)

        initLayout()

        val builder : AlertDialog.Builder = AlertDialog.Builder(activity!!).setView(v)

        initViews()
        return builder.create()
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        try {
            listener = frag
        }
        catch (e: Exception){
            throw ClassCastException((frag.tag.toString() +
                    " must implement ProductsSortDialogListener"))
        }
    }

    /*************************************************************
     *                       SETTER/GETTER                       *
     *************************************************************/
    fun getSortCategory(): String {
        return selectedCategory
    }

    fun getSortMode(): String {
        return selectedMode
    }

    /*************************************************************
     *                          INITs                            *
     *************************************************************/
    private fun initLayout(){
        v = requireActivity().layoutInflater
            .inflate(R.layout.dialog_sort_products, null)
    }

    private fun initViews(){
        initPositiveNegativeButtons()
        initSpinner()
    }

    private fun initPositiveNegativeButtons(){
        v.tvSortApply.setOnClickListener {
            listener.onProductsSortDialogPositiveClick(this)
        }
        v.tvSortCancel.setOnClickListener {
            listener.onProductsSortDialogNegativeClick(this)
        }
    }

    private fun initSpinner(){
        sortCategories.add("Name")
        sortCategories.add("Brand")
        sortCategories.add("SubCategory")
        sortCategories.add("Price")
        sortMode.add("Ascending")
        sortMode.add("Descending")

        v.spinnerSortCategory.adapter =
                ArrayAdapter<String>(frag.context,
                    android.R.layout.simple_dropdown_item_1line, sortCategories)
        v.spinnerSortMode.adapter =
                ArrayAdapter<String>(frag.context,
                    android.R.layout.simple_dropdown_item_1line, sortMode)

        v.spinnerSortCategory.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selection = parent?.getItemAtPosition(position)
                selectedCategory = selection.toString()
            }
        }

        v.spinnerSortMode.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selection = parent?.getItemAtPosition(position)
                selectedMode = selection.toString()
            }
        }
    }
}