package org.cg.model


import org.cg.Config
import org.cg.business.Attackable
import org.cg.business.Blockable
import org.cg.business.Destroyable
import org.cg.business.Sufferable
import org.itheima.kotlin.game.core.Composer
import org.itheima.kotlin.game.core.Painter

/**
 * 位置
 * 宽高
 *显示行为
 */

class Wall(override val x: Int,override val y: Int) :Blockable,Sufferable,Destroyable {


    override var blood: Int = 10


    //override val x: Int = 100
    //override val y: Int =100
    override val width: Int = org.cg.Config.block
    override val height: Int = org.cg.Config.block

    override fun draw() {
        Painter.drawImage("img/s5.png",x,y)
    }
    override fun isDestroved(): Boolean = blood <= 0


    override fun notifySuffer(attackable: Attackable):Array<views>? {
        //   println("砖墙接收到挨打的通知了")
        blood -= attackable.attackPower
        Composer.play("snd/5788.wav")
        return arrayOf(Blast(x,y))


    }



}