package com.anesabml.quotey.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.anesabml.quotey.databinding.FragmentFavoriteQuotesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteQuotesFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteQuotesBinding
    private val viewModel by viewModel<FavoriteQuotesViewModel>()
    private lateinit var adapter: MyItemQuoteRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteQuotesBinding.inflate(inflater, container, false)

        // Set the adapter
        adapter = MyItemQuoteRecyclerViewAdapter(listOf())
        with(binding.list) {
            layoutManager = LinearLayoutManager(context)
            adapter = this@FavoriteQuotesFragment.adapter
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.quotes.observe(viewLifecycleOwner, Observer {
            adapter.changeContent(it)
        })

        viewModel.errorText.observe(viewLifecycleOwner, Observer {
            binding.errorText.text = getString(it)
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.progressBar.show()
            } else {
                binding.progressBar.hide()
            }
        })

        viewModel.start()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            FavoriteQuotesFragment()
    }
}
