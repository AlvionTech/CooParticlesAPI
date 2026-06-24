package cn.coostack.cooparticlesapi.utils

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CircularQueueTest {

    @Test
    fun addAndGetWithinCapacity() {
        val q = CircularQueue<Int>(3)
        q.addFirst(10)
        q.addFirst(20)
        assertEquals(10, q[0])
        assertEquals(20, q[1])
        assertEquals(2, q.notNullSize())
        assertFalse(q.empty())
    }

    @Test
    fun newQueueIsEmpty() {
        assertTrue(CircularQueue<Int>(2).empty())
    }

    @Test
    fun iteratorYieldsInsertedElementsInOrder() {
        val q = CircularQueue<String>(3)
        q.addFirst("a")
        q.addFirst("b")
        assertEquals(listOf("a", "b"), q.toList())
    }

    @Test
    fun overwritesOldestWhenExceedingCapacity() {
        val q = CircularQueue<Int>(2)
        q.addFirst(1)
        q.addFirst(2)
        q.addFirst(3) // wraps, overwrites oldest (1)
        assertEquals(2, q.notNullSize())
        assertEquals(listOf(2, 3), q.toList())
    }

    @Test
    fun getWellBeyondSizeThrows() {
        val q = CircularQueue<Int>(3)
        q.addFirst(1)
        assertFailsWith<IndexOutOfBoundsException> { q[5] }
    }
}
