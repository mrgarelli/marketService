package com.example.marketService

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GroceryCartTests {
    @Test
    fun adds_grocery_if_exists() {
        val subject = GroceryCart()
        val appleQrUrl = "https://zxing.org/w/chart?cht=qr&chs=350x350&chld=L&choe=UTF-8&chl=0001"
        val addPayload = AddGroceryPayload(qrUrl = appleQrUrl)
        val didAdd: Boolean = subject.addGroceryItem(addPayload)
        Assertions.assertTrue(didAdd)
        Assertions.assertNotNull(subject.addedGroceries.find { it.qrUrl == appleQrUrl })
        Assertions.assertEquals(1, subject.addedGroceries.size)
    }

    @Test
    fun does_not_add_grocery_if_not_in_database() {
        val subject = GroceryCart()
        val invalidQrUrl = "https://zxing.org/w/chart?cht=qr&chs=350x350&chld=L&choe=UTF-8&chl=0024"
        val addPayload = AddGroceryPayload(qrUrl = invalidQrUrl)
        val didAdd: Boolean = subject.addGroceryItem(addPayload)
        Assertions.assertFalse(didAdd)
        Assertions.assertNull(subject.addedGroceries.find { it.qrUrl == invalidQrUrl })
        Assertions.assertEquals(0, subject.addedGroceries.size)
    }
}
