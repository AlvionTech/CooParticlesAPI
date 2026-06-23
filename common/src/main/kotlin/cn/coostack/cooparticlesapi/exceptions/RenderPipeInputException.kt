package cn.coostack.cooparticlesapi.exceptions

class RenderPipeInputException(inputFBO: Int, inputChannel: Int) :
    Exception("Render pipeline fbo: $inputFBO color channel $inputChannel can only have one input") {
}