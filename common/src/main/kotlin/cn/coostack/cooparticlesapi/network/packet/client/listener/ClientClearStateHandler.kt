package cn.coostack.cooparticlesapi.network.packet.client.listener

import cn.coostack.cooparticlesapi.CooParticlesAPIClient
import cn.coostack.cooparticlesapi.network.packet.server.PacketClearClientStateS2C
import cn.coostack.cooparticlesapi.platform.network.ClientContext

/**
 * Client handler for [PacketClearClientStateS2C]: clears all transient client-side state.
 *
 * Reconstructed during upstream integration: upstream referenced this handler but never
 * committed the source file. Delegates to the existing
 * [CooParticlesAPIClient.clearTransientClientState], matching the server's clear logic.
 */
object ClientClearStateHandler {
    fun receive(
        payload: PacketClearClientStateS2C,
        context: ClientContext
    ) {
        CooParticlesAPIClient.clearTransientClientState()
    }
}
