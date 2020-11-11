package com.example.marketService

class GroceryItem {
    lateinit var id: String
    lateinit var qrUrl: String
    lateinit var name: String
    lateinit var price: String
    lateinit var thumbnail: String
    constructor(
            id: String,
            qrUrl: String,
            name: String,
            price: String,
            thumbnail: String
    ) {
        this.id = id
        this.qrUrl = qrUrl
        this.name = name
        this.price = price
        this.thumbnail = thumbnail
    }
}