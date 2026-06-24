package cn.coostack.cooparticlesapi.utils

import kotlin.test.Test
import kotlin.test.assertEquals

class RotationMatrixTest {
    private val eps = 1e-9

    @Test
    fun rotatesXAxisNinetyDegreesAboutZ() {
        val m = RotationMatrix.fromAxisAngle(RelativeLocation.zAxis(), Math.PI / 2)
        val r = m.applyToClone(RelativeLocation(1.0, 0.0, 0.0))
        assertEquals(0.0, r.x, 1e-9)
        assertEquals(1.0, r.y, 1e-9)
        assertEquals(0.0, r.z, 1e-9)
    }

    @Test
    fun zeroAngleIsIdentity() {
        val m = RotationMatrix.fromAxisAngle(RelativeLocation.yAxis(), 0.0)
        val r = m.applyToClone(RelativeLocation(1.0, 2.0, 3.0))
        assertEquals(1.0, r.x, eps)
        assertEquals(2.0, r.y, eps)
        assertEquals(3.0, r.z, eps)
    }

    @Test
    fun applyToCloneLeavesOriginalUntouched() {
        val m = RotationMatrix.fromAxisAngle(RelativeLocation.zAxis(), Math.PI / 2)
        val original = RelativeLocation(1.0, 0.0, 0.0)
        m.applyToClone(original)
        assertEquals(1.0, original.x, eps)
        assertEquals(0.0, original.y, eps)
        assertEquals(0.0, original.z, eps)
    }

    @Test
    fun applyToMutatesAndPreservesLength() {
        val m = RotationMatrix.fromAxisAngle(RelativeLocation(1.0, 1.0, 1.0), Math.PI / 3)
        val p = RelativeLocation(2.0, 0.0, 0.0)
        val before = Math.sqrt(p.x * p.x + p.y * p.y + p.z * p.z)
        m.applyTo(p)
        val after = Math.sqrt(p.x * p.x + p.y * p.y + p.z * p.z)
        // rotations preserve magnitude
        assertEquals(before, after, 1e-9)
    }
}
