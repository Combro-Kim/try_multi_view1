package com.example.nbc_standard_4_week.presentation.activity

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.nbc_standard_4_week.databinding.ActivityMainBinding
import com.example.nbc_standard_4_week.presentation.viewModel.MainViewModel
import com.example.nbc_standard_4_week.presentation.viewModel.MainViewModelFactory
import com.example.nbc_standard_4_week.presentation.adapter.DataAdapter

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val mainViewModel by viewModels<MainViewModel> {
        MainViewModelFactory()
    }

    //    private var dataSource = DataSource.getDataSource()
    private lateinit var adapter: DataAdapter


    //Live Data실습 (총금액 변경해보기)
    private val tvDollar: TextView by lazy {
        binding.tvDollar
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        showTotalPrice()
        recyclerViewDivider()
        initRecyclerView()


    }

    private fun initRecyclerView() {
        val dataList = mainViewModel.dataLiveData //viewModel에서 데이터 가져오기 todo LiveData
        adapter = DataAdapter(dataList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

/*        adapter.itemClick = object : DataAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val selectedData = mainViewModel.getDataForId(position)
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("extra_data", selectedData)
                startActivity(intent)
            }
        }*/
        adapter.itemClick = object : DataAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {
                mainViewModel.onItemSelected(position)
            }
        }
        mainViewModel.navigateToDetail.observe(this){
            val intent = Intent(this,DetailActivity::class.java).apply {
                putExtra("selectedItemId",it)
            }
            startActivity(intent)
        }
    }

    //item 간격 추가
    private fun recyclerViewDivider() {
        binding.recyclerView.addItemDecoration(object : ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.bottom = 40
            }
        })
    }

    //Live Data실습 (총금액 변경해보기)
    private fun showTotalPrice() {
        //기존 코드
//        val totalPrice = dataList().sumOf { it.price }
//        val decimal = DecimalFormat("#,##,###.00")
//        binding.tvDollar.text = decimal.format(totalPrice)

        ////Live Data로 변경
        mainViewModel.totalPrice.observe(this) {
            tvDollar.text = it
        }
        mainViewModel.updateTotalPrice()
    }
}