package com.example.nbc_standard_4_week.data

fun dataList(): MutableList<Data> {
    return mutableListOf(
        Data(
            id = 0,
            type = ViewType.CARD1,
            tvName = "Anderson",
            num1 = "2423358195032412",
            YM = "21/24",
            price = 3100.30
        ),
        Data(
            id = 1,
            type = ViewType.CARD2,
            tvName = "John",
            num1 = "1234567898765432",
            YM = "21/24",
            price = 2459.60
        ),
        Data(
            id = 2,
            type = ViewType.CARD3,
            tvName = "Mash",
            num1 = "4564456478954125",
            YM = "21/24",
            price = 4567.20
        ),
    )
}