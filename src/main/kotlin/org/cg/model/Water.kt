package org.cg.model

import org.cg.Config
import org.cg.business.Blockable
import org.itheima.kotlin.game.core.Painter

/**
 * 水
 * override val x: Int = 200verride val y: Int =200
  * 实现views接口
 */
class Water(override val x: Int,override val y: Int) :Blockable{
    override val width: Int = Config.block
    override val height: Int = Config.block

    override fun draw() {
        Painter.drawImage("img/s4.png",x,y)

    }



}
