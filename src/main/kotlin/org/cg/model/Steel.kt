package org.cg.model

import org.cg.Config
import org.cg.business.Attackable
import org.cg.business.Blockable
import org.cg.business.Sufferable
import org.itheima.kotlin.game.core.Painter

/**
 *贴墙
 * 实现views接口
 */
class Steel(override val x: Int,override val y: Int) :Blockable,Sufferable{
    override val blood: Int = 1


    //override val x: Int = 100
   // override val y: Int =100
    override val width: Int = Config.block
    override val height: Int = Config.block

    override fun draw() {
        Painter.drawImage("img/s6.png",x,y)

    }
    override fun notifySuffer(attackable: Attackable): Array<views>? {
        return arrayOf(Blast(x,y))
    }




}
