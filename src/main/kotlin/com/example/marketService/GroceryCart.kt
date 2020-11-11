package com.example.marketService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GroceryCart {
    @Autowired
    private var database: GroceryData = GroceryData()

    var addedGroceries: MutableList<GroceryItem> = mutableListOf<GroceryItem>()

    fun getGroceries(): MutableList<GroceryItem>? {
        return addedGroceries
    }

    fun addGroceryItem(addGroceryPayload: AddGroceryPayload): Boolean {
        // Get grocery from qrUrl or id
        var grocery: GroceryItem? = null
        if (addGroceryPayload.qrUrl != null) {
            grocery = database.getGroceryFromQrUrl(addGroceryPayload.qrUrl!!)
        } else if (addGroceryPayload.id != null) {
            grocery = database.getGroceryFromId(addGroceryPayload.id!!)
        }
        // Determine / communicate adding the grocery
        if (grocery != null) {
            addedGroceries.add(grocery)
            return true
        }
        return false
    }
}