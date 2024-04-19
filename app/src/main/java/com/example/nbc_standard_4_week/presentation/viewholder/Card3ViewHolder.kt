package com.example.nbc_standard_4_week.presentation.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.nbc_standard_4_week.data.Data
import com.example.nbc_standard_4_week.databinding.ItemCard3Binding
import java.text.DecimalFormat

class Card3ViewHolder(private val binding: ItemCard3Binding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: Data){
        binding.apply {
            tvName.text = data.tvName
            tvCardNum1.text = data.num1.toString()
            tvCardNum2.text = data.num2.toString()
            tvCardNum3.text = data.num3.toString()
            tvCardNum4.text = data.num4.toString()
            tvYM.text = data.YM
            val decimal = DecimalFormat("#,##,###.00")
            tvPrice.text = decimal.format(data.price)
        }
    }
}