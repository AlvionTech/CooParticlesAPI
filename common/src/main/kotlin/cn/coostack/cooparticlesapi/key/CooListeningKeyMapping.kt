package cn.coostack.cooparticlesapi.key

import com.mojang.blaze3d.platform.InputConstants
import net.minecraft.client.KeyMapping

/**
 * A "listen only" key mapping used for keys bound to mouse buttons, so the binding can be
 * polled without consuming the click (which would otherwise block rapid clicking).
 *
 * NOTE: upstream's full implementation relied on `KeyMappingMixin` / `KeyMappingAccessor`
 * mixins that were never committed to their repository. Those mixins are intentionally NOT
 * reconstructed here (guessing at vanilla-internals mixins risks breaking key handling).
 * Without them this behaves exactly like a standard [KeyMapping] — the rapid-click
 * optimisation simply falls back to vanilla behaviour instead of crashing on a missing class.
 */
class CooListeningKeyMapping(
    name: String,
    type: InputConstants.Type,
    keyCode: Int,
    category: String,
) : KeyMapping(name, type, keyCode, category)
