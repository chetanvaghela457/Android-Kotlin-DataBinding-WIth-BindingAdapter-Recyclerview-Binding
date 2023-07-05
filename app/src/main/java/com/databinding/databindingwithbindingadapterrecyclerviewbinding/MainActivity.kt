package com.databinding.databindingwithbindingadapterrecyclerviewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import com.databinding.databindingwithbindingadapterrecyclerviewbinding.adapters.ProductsItemAdapter
import com.databinding.databindingwithbindingadapterrecyclerviewbinding.databinding.ActivityMainBinding
import com.databinding.databindingwithbindingadapterrecyclerviewbinding.model.ProductData
import com.databinding.databindingwithbindingadapterrecyclerviewbinding.utils.getJsonDataFromRaw
import com.databinding.databindingwithbindingadapterrecyclerviewbinding.utils.observeByLambda
import com.databinding.databindingwithbindingadapterrecyclerviewbinding.utils.viewModelFactory
import com.databinding.databindingwithbindingadapterrecyclerviewbinding.viewmodel.ProductsViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val productsViewModel: ProductsViewModel by viewModels {
        viewModelFactory {
            ProductsViewModel(this@MainActivity)
        }
    }

    val productsItemAdapter by lazy { ProductsItemAdapter(productsViewModel)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.apply {

            onBackPressedDispatcher.addCallback {
                finish()
            }

            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)

            toolbar.setNavigationOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }

            getProductsItem().observeByLambda(this@MainActivity) {

                recyclerViewProducts.isNestedScrollingEnabled = false
                recyclerViewProducts.adapter = productsItemAdapter
                productsItemAdapter.submitList(it)

            }


        }
    }

    fun getProductsItem(): MutableLiveData<ArrayList<ProductData>> {
        val encodedString = MutableLiveData<ArrayList<ProductData>>()

        val rawFile = R.raw.products_items
        val jsonFileString = getJsonDataFromRaw(
            this,
            rawFile
        )
        val type = object : TypeToken<ArrayList<ProductData>>() {}.type
        encodedString.postValue(Gson().fromJson(jsonFileString, type))

        return encodedString
    }
}