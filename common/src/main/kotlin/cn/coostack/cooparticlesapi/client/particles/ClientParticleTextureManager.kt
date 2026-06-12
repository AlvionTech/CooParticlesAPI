package cn.coostack.cooparticlesapi.client.particles

import cn.coostack.cooparticlesapi.CooParticlesConstants
import net.minecraft.client.particle.ParticleRenderType

object ClientParticleTextureManager {
    val particleTexturesMapper: MutableMap<String, ParticleRenderType> = mutableMapOf()

    @JvmStatic
    fun registerRenderType(type: ParticleRenderType) {
        particleTexturesMapper[type.toString()] = type
    }

    @JvmStatic
    fun textureSheetFromString(sheet: String): ParticleRenderType? {
        return particleTexturesMapper[sheet]
    }

    @JvmStatic
    fun getTextureSheet(sheet: String): ParticleRenderType {
        return textureSheetFromString(sheet) ?: let {
            CooParticlesConstants.logger.error("can not find textureSheet $sheet you need use ClientParticleTextureManager.registerRenderType() to register mapper")
            ParticleRenderType.PARTICLE_SHEET_OPAQUE
        }
    }
}
