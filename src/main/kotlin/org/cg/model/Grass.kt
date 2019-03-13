package org.cg.model

import org.cg.Config
import org.itheima.kotlin.game.core.Painter

/**
 *草坪
 * 实现views接口
 */
class Grass(override val x: Int,override val y: Int) :views{
    override val width: Int = Config.block
    override val height: Int = Config.block

    override fun draw() {
        Painter.drawImage("img/s3.png",x,y)

    }



}
