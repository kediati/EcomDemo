import com.example.myapplication.model.Product
import com.example.myapplication.shoppingcart.ShoppingCart
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal

class ShoppingCartTest {
    lateinit var product1: Product
    lateinit var product2: Product

    @Before
    fun setup() {
        product1 = Product(id = 1, title = "product1", price = "5.00", category = "zzz", description = "Test description", image = "image.jpg")
        product2 = Product(id = 2, title = "product2", price = "10.00", category = "zzz", description = "Test description", image = "image.jpg")
        ShoppingCart.cartItemList.clear() // Ensuring the cart is empty before each test
    }

    @Test
    fun addNewItemTest() {
        ShoppingCart.addNewItem(product1)
        assertEquals(1, ShoppingCart.getSize())
        assertEquals(product1, ShoppingCart.getItemAtIndex(0).product)
    }

    @Test
    fun removeItemTest() {
        ShoppingCart.addNewItem(product1)
        val result = ShoppingCart.removeItem(product1.id)
        assertTrue(result)
        assertEquals(0, ShoppingCart.getSize())
    }

    @Test
    fun removeItemNotTest() {
        val result = ShoppingCart.removeItem(product1.id)
        assertFalse(result)
        assertEquals(0, ShoppingCart.getSize())
    }

    @Test
    fun incrementItemQuantityTest() {
        ShoppingCart.addNewItem(product1)
        ShoppingCart.incrementItemQuantity(product1.id)
        assertEquals(2, ShoppingCart.getItemAtIndex(0).quantity)
    }

    @Test
    fun decrementItemQuantityTest() {
        ShoppingCart.addNewItem(product1)
        ShoppingCart.incrementItemQuantity(product1.id)
        ShoppingCart.decrementItemQuantity(product1.id)
        assertEquals(1, ShoppingCart.getItemAtIndex(0).quantity)
    }

    @Test
    fun decrementItemQuantityRemoveTest() {
        ShoppingCart.addNewItem(product1)
        ShoppingCart.decrementItemQuantity(product1.id)
        assertEquals(0, ShoppingCart.getSize())
    }

    @Test
    fun totalTest() {
        ShoppingCart.addNewItem(product1)
        ShoppingCart.addNewItem(product2)
        assertEquals("15.00", ShoppingCart.total)
    }

    @Test
    fun getCartItemTest() {
        ShoppingCart.addNewItem(product1)
        val cartItem = ShoppingCart.getItemAtIndex(0)
        assertEquals(product1, cartItem.product)
    }
}
