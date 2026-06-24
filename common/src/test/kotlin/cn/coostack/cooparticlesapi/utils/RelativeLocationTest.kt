package cn.coostack.cooparticlesapi.utils

import kotlin.math.sqrt
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotSame
import kotlin.test.assertTrue

class RelativeLocationTest {
    private val eps = 1e-9

    @Test
    fun normalizeProducesUnitVector() {
        val n = RelativeLocation(0.0, 3.0, 4.0).normalize()
        val len = sqrt(n.x * n.x + n.y * n.y + n.z * n.z)
        assertEquals(1.0, len, eps)
        assertEquals(0.0, n.x, eps)
        assertEquals(0.6, n.y, eps)
        assertEquals(0.8, n.z, eps)
    }

    @Test
    fun normalizeZeroVectorDefaultsToXAxis() {
        val n = RelativeLocation(0.0, 0.0, 0.0).normalize()
        assertEquals(1.0, n.x, eps)
        assertEquals(0.0, n.y, eps)
        assertEquals(0.0, n.z, eps)
    }

    @Test
    fun dotProduct() {
        val d = RelativeLocation(1.0, 2.0, 3.0).dot(RelativeLocation(4.0, 5.0, 6.0))
        assertEquals(32.0, d, eps)
    }

    @Test
    fun cloneIsIndependentCopy() {
        val a = RelativeLocation(1.0, 2.0, 3.0)
        val b = a.clone()
        assertNotSame(a, b)
        b.x = 9.0
        assertEquals(1.0, a.x, eps)
    }

    @Test
    fun addMutatesInPlaceAndReturnsThis() {
        val a = RelativeLocation(1.0, 1.0, 1.0)
        val r = a.add(RelativeLocation(2.0, 3.0, 4.0))
        assertTrue(r === a)
        assertEquals(3.0, a.x, eps)
        assertEquals(4.0, a.y, eps)
        assertEquals(5.0, a.z, eps)
    }

    @Test
    fun minusReturnsNewWithoutMutating() {
        val a = RelativeLocation(5.0, 5.0, 5.0)
        val r = a - RelativeLocation(1.0, 2.0, 3.0)
        assertEquals(4.0, r.x, eps)
        assertEquals(3.0, r.y, eps)
        assertEquals(2.0, r.z, eps)
        assertEquals(5.0, a.x, eps)
    }

    @Test
    fun timesScalarAndUnaryMinus() {
        val a = RelativeLocation(1.0, -2.0, 3.0)
        val t = a * 2.0
        assertEquals(2.0, t.x, eps)
        assertEquals(-4.0, t.y, eps)
        assertEquals(6.0, t.z, eps)
        val neg = -a
        assertEquals(-1.0, neg.x, eps)
        assertEquals(2.0, neg.y, eps)
        assertEquals(-3.0, neg.z, eps)
    }
}
