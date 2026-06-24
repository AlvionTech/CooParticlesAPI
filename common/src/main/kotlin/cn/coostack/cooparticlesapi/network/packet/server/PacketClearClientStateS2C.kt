package cn.coostack.cooparticlesapi.network.packet.server

import cn.coostack.cooparticlesapi.CooParticlesConstants
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.ResourceLocation

/**
 * Server -> client signal to drop all transient client-side particle / render / sound
 * state (sent from [cn.coostack.cooparticlesapi.CooParticlesAPI.clearTransientState],
 * e.g. on dimension change or server state reset). Carries no payload data.
 *
 * Reconstructed during upstream integration: upstream referenced this class but never
 * committed the source file.
 */
object PacketClearClientStateS2C : CustomPacketPayload {
    private val identifierID = ResourceLocation.fromNamespaceAndPath(CooParticlesConstants.MOD_ID, "clear_client_state")
    val payloadID = CustomPacketPayload.Type<PacketClearClientStateS2C>(identifierID)

    val CODEC: StreamCodec<FriendlyByteBuf, PacketClearClientStateS2C> =
        CustomPacketPayload.codec({ _, _ -> }, { _ -> PacketClearClientStateS2C })

    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload> {
        return payloadID
    }
}
