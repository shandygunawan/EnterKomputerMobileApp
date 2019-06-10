package com.shandy.enterkomputermobileapp.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.shandy.enterkomputermobileapp.R
import com.shandy.enterkomputermobileapp.models.Product
import com.shandy.enterkomputermobileapp.utils.inflate
import kotlinx.android.synthetic.main.item_product.view.*

class ListProductAdapter(private val products : List<Product>?) : RecyclerView.Adapter<ListProductAdapter.ProductHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val inflatedView = parent.inflate(R.layout.item_product, false)
        return ProductHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ListProductAdapter.ProductHolder, position: Int) {
        holder.bind(products!![position])
    }

    override fun getItemCount() = products?.size ?: 0 // If not null return size, if null return 0

    class ProductHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener{

        private var view: View = v

        init {
            v.setOnClickListener(this)
        }

        fun bind(product: Product){
            view.tvProductName.text = product.name
            view.tvBrand.text = product.brand_description
            view.tvSubCategory.text = product.subcategory_description
            view.tvPrice.text = product.price
        }

        override fun onClick(v: View?) {
            Log.d("ListProductAdapter", "CLICKED")
        }
    }
}