package com.example.marketService

import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

// TODO: convert this to an actual database
@Component
class GroceryData {
    final val groceries = mutableListOf<GroceryItem>()

    init {
        groceries.add(GroceryItem(
                "0001",
                "https://zxing.org/w/chart?cht=qr&chs=350x350&chld=L&choe=UTF-8&chl=0001",
                name = "Banana",
                price = "$1.00",
                thumbnail = "https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/120/apple/237/banana_1f34c.png"
        ))
        groceries.add(GroceryItem(
                "0002",
                "https://zxing.org/w/chart?cht=qr&chs=350x350&chld=L&choe=UTF-8&chl=0002",
                name = "Apple",
                price = "$3.00",
                thumbnail = "https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/120/apple/237/red-apple_1f34e.png"
        ))
        groceries.add(GroceryItem(
                "0003",
                "https://zxing.org/w/chart?cht=qr&chs=350x350&chld=L&choe=UTF-8&chl=0003",
                name = "Rotisserie Chicken",
                price = "$10.00",
                thumbnail = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcT3yZvqLdS3l6pQZhsL8VXuA4g_Owe-UQPbhQ&usqp=CAU"
        ))
    }

    fun getGroceryFromQrUrl(qrUrl: String) =
        groceries.find { it.qrUrl == qrUrl }

    fun getGroceryFromId(id: String) =
            groceries.find { it.id == id }
}

