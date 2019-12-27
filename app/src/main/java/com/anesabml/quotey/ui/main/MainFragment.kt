package com.anesabml.quotey.ui.main

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.palette.graphics.Palette
import com.anesabml.quotey.ColorUtils
import com.anesabml.quotey.R
import com.anesabml.quotey.core.domain.Quote
import com.anesabml.quotey.databinding.MainFragmentBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)

        binding.addToFavoriteButton.setOnClickListener {
            viewModel.updateFavorite()
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupObservers()

//        val notificationManager = NotificationManger(context!!)
//        notificationManager.showQodNotification(Quote.Empty)
    }

    private fun setupObservers() {
        viewModel.quote.observe(viewLifecycleOwner, Observer {
            updateUi(it)
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.progressBar.show()
            } else {
                binding.progressBar.hide()
            }
        })

        viewModel.isFavorite.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.addToFavoriteButton.setImageResource(R.drawable.ic_star_fill)
            } else {
                binding.addToFavoriteButton.setImageResource(R.drawable.ic_star)
            }
        })

        viewModel.errorText.observe(viewLifecycleOwner, Observer {
            viewModel.getLastQuote()
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
        })


        viewModel.start()
    }

    @SuppressLint("SetTextI18n")
    private fun updateUi(quote: Quote) {
        if (quote.length >= 100) {
            binding.quote.textSize = 34f
        }

        binding.quote.text = "\"${quote.quote}\""
        binding.author.text = quote.author

        // Load the background
        Glide.with(context!!)
            .load(quote.background)
            .addListener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.quoteImage.setImageDrawable(resource)
                    val bitmap = resource as BitmapDrawable
                    bindFavIcon(bitmap.bitmap)
                    bindQuoteText(bitmap.bitmap)
                    return false
                }

            })
            .into(binding.quoteImage)

        binding.addToFavoriteButton.visibility = View.VISIBLE
    }


    private fun bindFavIcon(bitmap: Bitmap) {
        val iconSize = binding.addToFavoriteButton.width

        Palette.from(bitmap)
            .maximumColorCount(3)
            .clearFilters()
            .setRegion(bitmap.width - iconSize, 0, bitmap.width, iconSize)
            .generate { palette ->
                val lightness = ColorUtils.isDark(palette)
                val isDark = if (lightness == ColorUtils.LIGHTNESS_UNKNOWN) {
                    ColorUtils.isDark(bitmap, bitmap.width - iconSize / 2, iconSize / 2)
                } else {
                    lightness == ColorUtils.IS_DARK
                }


                if (isDark) { // make back icon dark on light images
                    binding.addToFavoriteButton.setColorFilter(
                        Color.WHITE,
                        PorterDuff.Mode.SRC_ATOP
                    )
                } else {
                    binding.addToFavoriteButton.setColorFilter(
                        Color.WHITE,
                        PorterDuff.Mode.SRC_ATOP
                    )
                }
            }
    }

    private fun bindQuoteText(bitmap: Bitmap) {
        val iconSize = binding.addToFavoriteButton.width

        Palette.from(bitmap)
            .maximumColorCount(3)
            .clearFilters()
            .setRegion(0, 0, bitmap.width, bitmap.width / 2)
            .generate { palette ->
                val lightness = ColorUtils.isDark(palette)
                val isDark = if (lightness == ColorUtils.LIGHTNESS_UNKNOWN) {
                    ColorUtils.isDark(bitmap, bitmap.width - iconSize / 2, iconSize / 2)
                } else {
                    lightness == ColorUtils.IS_DARK
                }


                if (isDark) { // make back text dark on light images
                    binding.quote.setTextColor(Color.WHITE)
                    binding.author.setTextColor(Color.WHITE)
                } else {
                    binding.quote.setTextColor(Color.BLACK)
                    binding.author.setTextColor(Color.BLACK)
                }
            }
    }

}
