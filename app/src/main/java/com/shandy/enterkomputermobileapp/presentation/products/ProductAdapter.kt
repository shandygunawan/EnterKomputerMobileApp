package com.shandy.enterkomputermobileapp.presentation.products

import android.app.SearchManager
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.shandy.enterkomputermobileapp.R
import com.shandy.enterkomputermobileapp.models.Product
import com.shandy.enterkomputermobileapp.utils.inflate
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter(private val products : List<Product>?) : RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val inflatedView = parent.inflate(R.layout.item_product, false)
        return ProductHolder(
            inflatedView
        )
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.bind(products!![position])
    }

    override fun getItemCount() = products?.size ?: 0 // If not null return size, if null return 0

    class ProductHolder(v: View) : RecyclerView.ViewHolder(v){

        private var view: View = v

        fun bind(product: Product){
            view.tvProductName.text = product.name
            view.tvBrand.text = product.brand_description
            view.tvSubCategory.text = product.subcategory_description
            view.tvPrice.text = product.price

            initImageButtons(product)
        }

        private fun initImageButtons(product: Product){
            val ibGoogle : ImageButton = view.findViewById(R.id.ibGoogleLink)
            val ibTokped : ImageButton = view.findViewById(R.id.ibTokopediaLink)
            val ibBL : ImageButton = view.findViewById(R.id.ibBukalapakLink)
            val ibShopee: ImageButton = view.findViewById(R.id.ibShopeeLink)

            if(product.link_toped == "" || product.link_toped == null) ibTokped.visibility = View.GONE
            if(product.link_bukalapak == "" || product.link_bukalapak == null) ibBL.visibility = View.GONE
            if(product.link_shopee == "" || product.link_shopee == null) ibShopee.visibility = View.GONE

            ibGoogle.setOnClickListener{
                val intent = Intent(Intent.ACTION_WEB_SEARCH).apply {
                    putExtra(SearchManager.QUERY, product.name)
                }
                it.context.startActivity(intent)
            }
        }
    }
}