package com.shandy.enterkomputermobileapp.presentation.product

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.shandy.enterkomputermobileapp.R
import com.shandy.enterkomputermobileapp.models.Product
import com.shandy.enterkomputermobileapp.utils.Constants
import kotlinx.android.synthetic.main.dialog_filter_products.view.*
import org.jetbrains.anko.sdk27.coroutines.onCheckedChange

class ProductFilterDialog(paramFrag: ProductFragment, paramProducts: List<Product>?) : DialogFragment(){

    /*************************************************************
     *                          VARIABLES                        *
     *************************************************************/
    private val frag: ProductFragment = paramFrag
    private val products = paramProducts
    private var brands = ArrayList<String>()
    private var subCategories = ArrayList<String>()
    private var filters = HashMap<String, String>()
    private lateinit var v : View
    private lateinit var listener: ProductsFilterDialogListener

    /*************************************************************
     *                          INTERFACES                        *
     *************************************************************/
    interface ProductsFilterDialogListener{
        fun onProductsFilterDialogPositiveClick(dialog: ProductFilterDialog)
        fun onProductsFilterDialogNegativeClick(dialog: ProductFilterDialog)
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
     *                       SETTER/GETTER                       *
     *************************************************************/
    fun getFilters() : HashMap<String, String>{
        return filters
    }

    private fun setFilter(key: String, value: String){
        filters[key] = value
    }

    /*************************************************************
     *                          INITs                            *
     *************************************************************/
    private fun initLayout(){
        v = requireActivity().layoutInflater
            .inflate(R.layout.dialog_filter_products, null)
    }

    private fun initViews(){
        initPositiveNegativeButtons()
        initTILs()
        initSpinnersContents()
        initSpinners()
        initCheckboxes()
    }

    private fun initPositiveNegativeButtons(){
        v.tvFilterApply.setOnClickListener {
            if(isInputsCorrect()) listener.onProductsFilterDialogPositiveClick(this)
        }
        v.tvFilterCancel.setOnClickListener {
            listener.onProductsFilterDialogNegativeClick(this)
        }
    }

    private fun initTILs(){
        v.etFilterName.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                setFilter(Constants.Filters.FILTER_PRODUCTS_NAME, s.toString())
            }
        })

        v.etFilterBrand.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                setFilter(Constants.Filters.FILTER_PRODUCTS_BRAND, s.toString())
            }
        })

        v.etFilterMinPrice.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                v.tilFilterMinPrice.isErrorEnabled = false
                setFilter(Constants.Filters.FILTER_PRODUCTS_PRICE,
                    s.toString() + Constants.Separators.SEPARATOR_COMMA + v.etFilterMaxPrice.text.toString())
            }
        })

        v.etFilterMaxPrice.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                v.tilFilterMaxPrice.isErrorEnabled = false
                setFilter(Constants.Filters.FILTER_PRODUCTS_PRICE,
                    v.etFilterMinPrice.text.toString() + Constants.Separators.SEPARATOR_COMMA + s.toString())
            }
        })
    }

    private fun initSpinners(){

        // Spinner for brands

        // Spinner for SubCategories
        v.spinnerFilterSubCategory.adapter =
            ArrayAdapter(frag.context,
                android.R.layout.simple_dropdown_item_1line, subCategories)

        v.spinnerFilterSubCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do Nothing
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selection = parent?.getItemAtPosition(position)
                setFilter(Constants.Filters.FILTER_PRODUCTS_SUBCATEGORY, selection.toString())
            }
        }
    }

    private fun initCheckboxes(){
        setFilter(Constants.Filters.FILTER_PRODUCTS_LINK_BUKALAPAK,
            Constants.States.CHECKBOX_UNCHECKED)
        setFilter(Constants.Filters.FILTER_PRODUCTS_LINK_TOKOPEDIA,
            Constants.States.CHECKBOX_UNCHECKED)
        setFilter(Constants.Filters.FILTER_PRODUCTS_LINK_SHOPEE,
            Constants.States.CHECKBOX_UNCHECKED)
        v.cbFilterBukalapak.onCheckedChange { _, isChecked ->
            if(isChecked) {
                setFilter(Constants.Filters.FILTER_PRODUCTS_LINK_BUKALAPAK,
                    Constants.States.CHECKBOX_CHECKED)
            }
            else {
                setFilter(Constants.Filters.FILTER_PRODUCTS_LINK_BUKALAPAK,
                    Constants.States.CHECKBOX_UNCHECKED)
            }
        }

        v.cbFilterTokopedia.onCheckedChange { _, isChecked ->
            if(isChecked) {
                setFilter(Constants.Filters.FILTER_PRODUCTS_LINK_TOKOPEDIA,
                    Constants.States.CHECKBOX_CHECKED)
            }
            else {
                setFilter(Constants.Filters.FILTER_PRODUCTS_LINK_TOKOPEDIA,
                    Constants.States.CHECKBOX_UNCHECKED)
            }
        }

        v.cbFilterShopee.onCheckedChange { _, isChecked ->
            if(isChecked) {
                setFilter(Constants.Filters.FILTER_PRODUCTS_LINK_SHOPEE,
                    Constants.States.CHECKBOX_CHECKED)
            }
            else {
                setFilter(Constants.Filters.FILTER_PRODUCTS_LINK_SHOPEE,
                    Constants.States.CHECKBOX_UNCHECKED)
            }
        }
    }

    private fun initSpinnersContents(){
        brands.clear()
        subCategories.clear()
        if(products != null){
            val isSubCatInserted = HashMap<String, Boolean>()
            val isBrandInserted = HashMap<String, Boolean>()

            for(product in products){
                if(product.subcategory_description != Constants.Strings.STRING_EMPTY &&
                    (isSubCatInserted[product.subcategory_description] != true
                            || isSubCatInserted[product.subcategory_description] == null)){
                    subCategories.add(product.subcategory_description)
                    isSubCatInserted[product.subcategory_description] = true
                }

                if(product.brand_description != Constants.Strings.STRING_EMPTY &&
                    (isSubCatInserted[product.brand_description] != true
                            || isSubCatInserted[product.brand_description] == null)){
                    brands.add(product.brand_description)
                    isBrandInserted[product.brand_description] = true
                }

                subCategories.sort()
                brands.sort()
                subCategories.add(0, getString(R.string.all_subcategory))
                brands.add(0, "All Brands")
            }
        }
    }

    /*************************************************************
     *                          CHECKS                           *
     *************************************************************/
    private fun isInputsCorrect() : Boolean{
        // Check prices (min and max)
        val minPrice = v.etFilterMinPrice.text.toString()
        val maxPrice = v.etFilterMaxPrice.text.toString()
        if(minPrice == Constants.Strings.STRING_EMPTY && maxPrice == Constants.Strings.STRING_EMPTY){
            return true
        }
        if(minPrice == Constants.Strings.STRING_EMPTY && maxPrice != Constants.Strings.STRING_EMPTY){
            v.tilFilterMinPrice.error = "Field should be filled"
            v.tilFilterMinPrice.isErrorEnabled = true
            return false
        }
        if(minPrice != Constants.Strings.STRING_EMPTY && maxPrice == Constants.Strings.STRING_EMPTY){
            v.tilFilterMaxPrice.error = "Field should be filled"
            v.tilFilterMaxPrice.isErrorEnabled = true
            return false
        }
        if(minPrice.toInt() > maxPrice.toInt()){
            v.tilFilterMaxPrice.error = "Number should be bigger"
            v.tilFilterMaxPrice.isErrorEnabled = true
            return false
        }
        return true
    }
}