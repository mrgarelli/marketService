package com.example.marketService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class GroceryController {
    @Autowired
    private var cart: GroceryCart = GroceryCart()

    @RequestMapping("/", method = [RequestMethod.GET])
    fun students() = cart.getGroceries()

    @RequestMapping("/addGroceryItem", method = [RequestMethod.PUT])
    fun addGroceryItem(@RequestBody addGroceryPayload: AddGroceryPayload) =
            if (cart.addGroceryItem(addGroceryPayload)) "200"
            else throw Exception("Unable to add grocery to cart")
}
