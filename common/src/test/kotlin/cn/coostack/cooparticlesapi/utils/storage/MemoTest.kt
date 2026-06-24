package cn.coostack.cooparticlesapi.utils.storage

import java.util.function.Supplier
import kotlin.test.Test
import kotlin.test.assertEquals

class MemoTest {

    @Test
    fun computesLazilyAndCachesResult() {
        var calls = 0
        val memo = Memo(Supplier { calls++; 42 })
        assertEquals(0, calls, "supplier must not run before get()")
        assertEquals(42, memo.get())
        assertEquals(1, calls)
        memo.get()
        assertEquals(1, calls, "second get() should use cached value")
    }

    @Test
    fun resetMemoRecomputes() {
        var calls = 0
        val memo = Memo(Supplier { ++calls })
        assertEquals(1, memo.get())
        memo.resetMemo()
        assertEquals(2, memo.get(), "resetMemo should recompute from the supplier")
    }

    @Test
    fun setMemoValueOverridesSupplier() {
        val memo = Memo(Supplier { 1 })
        memo.setMemoValue(99)
        assertEquals(99, memo.get())
    }
}
