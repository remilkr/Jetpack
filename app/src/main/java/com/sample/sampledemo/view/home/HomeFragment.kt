package com.sample.sampledemo.view.home
/*
 * Copyright (C) 2023
 *
 * Created on : 26-11-2023
 * Author     : Remil K R
 *
 * com.sample.sampledemo.view.home
 *
 * This file contains the implementation of HomeFragment.kt class.
 */

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sample.sampledemo.databinding.FragmentHomeBinding
import com.sample.sampledemo.model.HomeScreenApiResponseModel
import com.sample.sampledemo.utils.AppUtil.dpToSp
import com.sample.sampledemo.utils.ProductCategory
import com.sample.sampledemo.view.components.*
import com.sample.sampledemo.viewmodel.HomeViewModel


class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setComposeView()
        viewModel.getHomeProducts()
    }

    private fun setComposeView() {
        with(binding) {
            composeView.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnDetachedFromWindow)
                setContent {
                    HomeContentView()
                }
            }
        }
    }

    /**
     * Creates dynamic vertical content
     */
    @Composable
    fun HomeContentView() {
        val productList by viewModel.homeProductList.collectAsState()
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(productList) { item ->
                if (item.type == ProductCategory.BANNER_SLIDER.value) {
                    val imageUrls = arrayListOf<String>()
                    item.contents?.forEach { imageUrls.add(it?.imageUrl ?: "") }
                    MultipleImageBanner(imageUrls)
                }
                if (item.type == ProductCategory.PRODUCTS.value) {
                    HorizontalListTitleLayout("${item.title}")
                    item.contents?.let {
                        HorizontalScrollableProducts(it)
                    }
                }
                if (item.type == ProductCategory.BANNER_SINGLE.value) {
                    SingleImageBanner(item.imageUrl)
                }
                if (item.type == ProductCategory.CATEGORIES.value) {
                    HorizontalListTitleLayout("${item.title}")
                    item.contents?.let {
                        HorizontalScrollableProductCategories(it)
                    }
                }
            }
        }
    }


    @Composable
    fun HorizontalScrollableProducts(contents: List<HomeScreenApiResponseModel.HomeScreenItem.Content?>) {
        LazyRow(
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp, 16.dp, 10.dp, 0.dp)
        ) {
            items(contents) { content ->
                SingleProductCard(content)
                Spacer(modifier = Modifier.width(5.dp))
            }
        }
    }

    @Composable
    fun HorizontalScrollableProductCategories(contents: List<HomeScreenApiResponseModel.HomeScreenItem.Content?>) {
        LazyRow(
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp, 16.dp, 10.dp, 0.dp)
        ) {
            items(contents) { content ->
                SingleProductCategoryCard(content)
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }

    @Composable
    fun SingleProductCategoryCard(content: HomeScreenApiResponseModel.HomeScreenItem.Content?) {
        LazyColumn(
            modifier = Modifier
                .width(78.dp)
                .height(64.dp)
                .border(
                    1.dp,
                    Color(
                        LocalContext.current.getColor(com.sample.sampledemo.R.color.color_product_card_outline)
                    ),
                    shape = RoundedCornerShape(8.dp),
                )
        ) {
            item {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(11.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(19.dp, 0.dp)
                        .height(34.dp)


                ) {
                    ProductImage(content?.imageUrl ?: "")
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                )
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(8.dp, 0.dp)
                ) {
                    ProductNameText(content?.title ?: "", textSize = 6, TextAlign.Center)
                }

            }
        }
    }

    @Composable
    fun SingleProductCard(content: HomeScreenApiResponseModel.HomeScreenItem.Content?) {
        LazyColumn(
            modifier = Modifier
                .width(96.dp)
                .height(152.dp)
                .border(
                    1.dp,
                    Color(
                        LocalContext.current.getColor(com.sample.sampledemo.R.color.color_product_card_outline)
                    ),
                    shape = RoundedCornerShape(8.dp),
                )
        ) {
            item {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(14.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 0.dp)
                        .height(55.dp)

                ) {
                    ProductImage(content?.productImage ?: "")
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(12.dp)
                )

                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(8.dp, 0.dp)
                ) {
                    DiscountText(content?.discount ?: "")
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(7.dp)
                )
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(8.dp, 0.dp)
                ) {
                    ProductNameText(content?.productName ?: "")
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(7.dp)
                )
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(8.dp, 0.dp)
                ) {
                    RatingBar(content?.productRating ?: 0)
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(7.dp)
                )
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(8.dp, 0.dp)
                ) {
                    ProductPrice(content?.actualPrice, content?.offerPrice)
                }
            }
        }
    }


    @Composable
    fun ProductNameText(text: String, textSize: Int = 5, textAlign: TextAlign = TextAlign.Start) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            fontWeight = FontWeight.W400,
            style = TextStyle(
                color = Color.Black,
                textAlign = textAlign,
                fontWeight = FontWeight.W400,
                fontSize = dpToSp(textSize.dp),
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }


}