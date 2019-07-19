package com.shandy.enterkomputermobileapp.presentation.product

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shandy.enterkomputermobileapp.R
import com.shandy.enterkomputermobileapp.models.Product
import com.shandy.enterkomputermobileapp.utils.Constants
import com.shandy.enterkomputermobileapp.utils.inflate
import com.shandy.enterkomputermobileapp.utils.string.CurrencyFormatter
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
        private lateinit var product: Product

        fun bind(paramProduct: Product){
            this.product = paramProduct
            view.tvProductName.text = product.name
            view.tvBrand.text = product.brand_description
            if(product.subcategory == null || product.subcategory_description.isNullOrBlank()){
                view.ivSubCategory.visibility = View.GONE
                view.tvSubCategory.visibility = View.GONE
            }
            else {
                view.tvSubCategory.text = product.subcategory_description
            }

            view.tvPrice.text = CurrencyFormatter.getIDRCurrencyFormat( product.price.toInt())

            initImageButtons()
            optimizeViews()
        }

        private fun initImageButtons(){
            if(!isLinkAvailable(Constants.ECommerces.ECOMMERCE_TOKOPEDIA)) view.ibTokopediaLink.visibility = View.GONE
            if(!isLinkAvailable(Constants.ECommerces.ECOMMERCE_BUKALAPAK)) view.ibBukalapakLink.visibility = View.GONE
            if(!isLinkAvailable(Constants.ECommerces.ECOMMERCE_SHOPEE)) view.ibShopeeLink.visibility = View.GONE

            view.ibGoogleLink.setOnClickListener{
                val intent = Intent(Intent.ACTION_WEB_SEARCH).apply {
                    putExtra(SearchManager.QUERY, product.name)
                }
                if(intent.resolveActivity(view.context.packageManager) != null){
                    it.context.startActivity(intent)
                }
            }

            view.ibTokopediaLink.setOnClickListener {
                if(isLinkAvailable(Constants.ECommerces.ECOMMERCE_TOKOPEDIA)){
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(product.link_toped))
                    if(intent.resolveActivity(view.context.packageManager) != null){
                        it.context.startActivity(intent)
                    }
                }
            }

            view.ibBukalapakLink.setOnClickListener {
                if(isLinkAvailable(Constants.ECommerces.ECOMMERCE_BUKALAPAK)){
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(product.link_bukalapak))
                    if(intent.resolveActivity(view.context.packageManager) != null){
                        it.context.startActivity(intent)
                    }
                }
            }

            view.ibShopeeLink.setOnClickListener {
                if(isLinkAvailable(Constants.ECommerces.ECOMMERCE_SHOPEE)){
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(product.link_shopee))
                    if(intent.resolveActivity(view.context.packageManager) != null){
                        it.context.startActivity(intent)
                    }
                }
            }
        }

        private fun isLinkAvailable(link: String): Boolean{
            return when(link){
                Constants.ECommerces.ECOMMERCE_TOKOPEDIA ->
                    (product.link_toped != "" && product.link_toped != null)
                Constants.ECommerces.ECOMMERCE_BUKALAPAK ->
                    (product.link_bukalapak != "" && product.link_bukalapak != null)
                Constants.ECommerces.ECOMMERCE_SHOPEE ->
                    (product.link_shopee != "" && product.link_shopee != null)
                else -> false
            }
        }

        private fun optimizeViews(){
            /* Optimize Image Buttons */
            Glide.with(view).load(R.drawable.icon_google_colored)
                .fitCenter().into(view.ibGoogleLink)
            Glide.with(view).load(R.drawable.icon_tokopedia_colored)
                .fitCenter().into(view.ibTokopediaLink)
            Glide.with(view).load(R.drawable.icon_bukalapak_colored)
                .fitCenter().into(view.ibBukalapakLink)
            Glide.with(view).load(R.drawable.icon_shopee_colored)
                .fitCenter().into(view.ibShopeeLink)

            /* Optimize RecyclerView Holder */
            Glide.with(view).load(R.drawable.icon_brand_colored)
                .fitCenter().into(view.ivBrand)
            Glide.with(view).load(R.drawable.icon_subcategory_black)
                .fitCenter().into(view.ivSubCategory)
            Glide.with(view).load(R.drawable.icon_price_colored)
                .fitCenter().into(view.ivPrice)
        }
    }
}