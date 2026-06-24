package cn.coostack.cooparticlesapi.utils

import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class MathPresetsTest {

    @Test
    fun romaIRejectsTooSmallScale() {
        assertFailsWith<IllegalArgumentException> { MathPresets.romaI(0.0) }
    }

    @Test
    fun romaIIRejectsTooSmallScale() {
        assertFailsWith<IllegalArgumentException> { MathPresets.romaII(0.001) }
    }

    @Test
    fun romaIProducesPoints() {
        assertTrue(MathPresets.romaI(1.0).isNotEmpty())
    }
}
