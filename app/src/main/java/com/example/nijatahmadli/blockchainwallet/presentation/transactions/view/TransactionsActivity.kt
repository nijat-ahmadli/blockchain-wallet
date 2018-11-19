package com.example.nijatahmadli.blockchainwallet.presentation.transactions.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.example.nijatahmadli.blockchainwallet.R
import com.example.nijatahmadli.blockchainwallet.databinding.ActivityTransactionsBinding
import com.example.nijatahmadli.blockchainwallet.domain.model.Transaction
import com.example.nijatahmadli.blockchainwallet.presentation.base.RxRecyclerViewAdapter
import com.example.nijatahmadli.blockchainwallet.presentation.transactions.viewModel.TransactionsViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject


class TransactionsActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    @Inject
    internal lateinit var viewModel: TransactionsViewModel

    @Inject
    internal lateinit var adapter: RxRecyclerViewAdapter<Transaction>

    private lateinit var binding: ActivityTransactionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setupBinding()
        setupSwipeRefresh()
        setupRecyclerView()
    }

    override fun onDestroy() {
        viewModel.clean()
        adapter.clean()
        super.onDestroy()
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_transactions)
        viewModel.initialize()
        binding.viewModel = viewModel
    }

    private fun setupSwipeRefresh() {
        binding.activityTransactionSwipeRefreshLayout.setOnRefreshListener(this)
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding.activityTransactionRecyclerView.layoutManager = layoutManager
        binding.activityTransactionRecyclerView.adapter = adapter
        adapter.subscribeToSource(viewModel.transactionsObservable)
        val dividerItemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.activityTransactionRecyclerView.addItemDecoration(dividerItemDecoration)
    }

    override fun onRefresh() {
        viewModel.refresh()
    }
}
