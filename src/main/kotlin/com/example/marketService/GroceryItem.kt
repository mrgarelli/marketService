package com.example.marketService

class GroceryItem(
    var id: String,
    var qrUrl: String,
    var name: String,
    var price: String,
    var thumbnail: String
) {}

class AddGroceryPayload(
    var qrUrl: String? = null,
    var id: String? = null
) {}
