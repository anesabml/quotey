package com.anesabml.quotey.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.anesabml.quotey.R
import com.anesabml.quotey.core.domain.Quote
import com.anesabml.quotey.databinding.MainFragmentBinding
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

        getData()

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

        viewModel.error.observe(viewLifecycleOwner, Observer {
            viewModel.getLastQuote()
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
        })
    }

    private fun getData() {
        viewModel.getRandomQuote()
    }

    @SuppressLint("SetTextI18n")
    private fun updateUi(quote: Quote) {
        if (quote.length >= 100) {
            binding.quote.textSize = 34f
        }
        binding.quote.text = "\"${quote.quote}\""
        binding.author.text = quote.author
    }
}
