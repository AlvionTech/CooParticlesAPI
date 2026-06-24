package cn.coostack.cooparticlesapi.utils

import net.minecraft.SharedConstants
import net.minecraft.server.Bootstrap
import net.minecraft.world.phys.Vec3
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class LinerLevelLerpTest {
    private val eps = 1e-9

    @BeforeTest
    fun bootstrap() {
        // Vec3 construction is used below; bootstrap is idempotent.
        SharedConstants.tryDetectVersion()
        Bootstrap.bootStrap()
    }

    @Test
    fun emptyReturnsMin() {
        assertEquals(5.0, LinerLevelLerp().lerp(5.0, 10.0), eps)
    }

    @Test
    fun singleLevelInterpolates() {
        val l = LinerLevelLerp().addLevel()
        l.setLevelProgress(0, 0.5)
        assertEquals(5.0, l.lerp(0.0, 10.0), eps)
    }

    @Test
    fun floatOverloadMatchesDouble() {
        val l = LinerLevelLerp().addLevel()
        l.setLevelProgress(0, 0.25f)
        assertEquals(2.5f, l.lerp(0.0f, 10.0f), 1e-6f)
    }

    @Test
    fun outOfRangeLevelThrows() {
        assertFailsWith<ArrayIndexOutOfBoundsException> {
            LinerLevelLerp().setLevelProgress(0, 0.5)
        }
    }

    @Test
    fun vec3LerpInterpolatesEveryAxis() {
        val l = LinerLevelLerp().addLevel()
        l.setLevelProgress(0, 0.5)
        val r = l.lerp(Vec3(0.0, 0.0, 0.0), Vec3(10.0, 20.0, 30.0))
        assertEquals(5.0, r.x, eps)
        // y and z must interpolate from min->max (regression guard for the
        // previous bug where minY/minZ were read from max).
        assertEquals(10.0, r.y, eps)
        assertEquals(15.0, r.z, eps)
    }
}
