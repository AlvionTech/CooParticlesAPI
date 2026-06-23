package cn.coostack.cooparticlesapi.exceptions

import net.minecraft.resources.ResourceLocation

class RenderPipeOutputNotSetException(pipeID: ResourceLocation) :
    Exception("$pipeID has no output pipeline set!") {
}