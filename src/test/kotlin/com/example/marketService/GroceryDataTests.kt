package com.example.marketService

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GroceryDataTests {
    @Test
    fun gets_a_grocery_if_can_find_by_qrUrl() {
        val subject = GroceryData()
        val mockQrUrl = "mock_qrUrl_006"
        val mockItem = GroceryItem(
                "0006",
                mockQrUrl,
                name = "mock_name",
                price = "mock_price",
                thumbnail = "mock_thumbnail"
        )
        subject.groceries.add(mockItem)
        Assertions.assertEquals(
                subject.getGroceryFromQrUrl(mockQrUrl),
                mockItem
        )
    }

    @Test
    fun errors_when_no_groceryItem_for_qrUrl() {
        val subject = GroceryData()
        val mockQrUrl = "mock_qrUrl_006"
        Assertions.assertNull(subject.getGroceryFromQrUrl(mockQrUrl))
    }
}