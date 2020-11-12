package com.example.marketService

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GroceryCartTests {
    private fun assert_adds_grocery_if_exists(addGroceryPayload: AddGroceryPayload) {
        val subject = GroceryCart()
        val didAdd: Boolean = subject.addGroceryItem(addGroceryPayload)
        Assertions.assertTrue(didAdd)
        Assertions.assertEquals(1, subject.addedGroceries.size)
        if (addGroceryPayload.qrUrl != null) {
            Assertions.assertNotNull(subject.addedGroceries.find { it.qrUrl == addGroceryPayload.qrUrl })
        } else if (addGroceryPayload.id != null) {
            Assertions.assertNotNull(subject.addedGroceries.find { it.id == addGroceryPayload.id })
        }
    }

    @Test
    fun adds_grocery_by_qrUrl_if_exists() {
        val appleQrUrl = "https://zxing.org/w/chart?cht=qr&chs=350x350&chld=L&choe=UTF-8&chl=0001"
        val addPayload = AddGroceryPayload(qrUrl = appleQrUrl)
        assert_adds_grocery_if_exists(addPayload)
    }

    @Test
    fun adds_grocery_by_id_if_exists() {
        val existentId = "0003"
        val addPayload = AddGroceryPayload(id = existentId)
        assert_adds_grocery_if_exists(addPayload)
    }

    fun assert_does_not_add_grocery_if_not_in_database(addGroceryPayload: AddGroceryPayload) {
        val subject = GroceryCart()
        val didAdd: Boolean = subject.addGroceryItem(addGroceryPayload)
        Assertions.assertFalse(didAdd)
        Assertions.assertEquals(0, subject.addedGroceries.size)
        if (addGroceryPayload.qrUrl != null) {
            Assertions.assertNull(subject.addedGroceries.find { it.qrUrl == addGroceryPayload.qrUrl })
        } else if (addGroceryPayload.id != null) {
            Assertions.assertNull(subject.addedGroceries.find { it.id == addGroceryPayload.id })
        }
    }

    @Test
    fun does_not_add_grocery_if_qrUrl_not_in_database() {
        val invalidQrUrl = "https://zxing.org/w/chart?cht=qr&chs=350x350&chld=L&choe=UTF-8&chl=0024"
        val addPayload = AddGroceryPayload(qrUrl = invalidQrUrl)
        assert_does_not_add_grocery_if_not_in_database(addPayload)
    }

    @Test
    fun does_not_add_grocery_if_id_not_in_database() {
        val invalidId = "0024"
        val addPayload = AddGroceryPayload(id = invalidId)
        assert_does_not_add_grocery_if_not_in_database(addPayload)
    }
}
