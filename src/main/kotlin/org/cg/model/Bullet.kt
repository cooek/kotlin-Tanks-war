package org.cg.model

import org.cg.Config
import org.cg.business.Attackable
import org.cg.business.AutoMovable
import org.cg.business.Destroyable
import org.cg.business.Sufferable
import org.cg.exit.checkCollision
import org.itheima.kotlin.game.core.Painter


class Bullet(override val owner: views,override  val currentdirection: Direction, creade:(Width:Int,height:Int)-> Pair<Int,Int>

) : AutoMovable, Destroyable,Attackable {
    override val attackPower: Int = 1
    override val speed: Int = 8
    override val width: Int
    override val height: Int
    override var x: Int = 0
    override var y: Int = 0
    private var isDostokyed = false
    private val imagepath :String =
        when (currentdirection) {
            Direction.UP -> "img/zidan.png"
            Direction.LEFT -> "img/zidanz.png"
            Direction.RIGHT -> "img/zidany.png"
            Direction.DOWN -> "img/zidanx.png"
        }
    init {
        val siza:Array<Int> = Painter.size(imagepath)
        width = siza[0]
        height = siza[1]
        val pair = creade.invoke(width,height)
        x = pair.first
        y = pair.second
    }


    override fun draw() {
        Painter.drawImage(imagepath,x,y)
    }
    override fun outMovable() {

        when(currentdirection){
            Direction.UP -> y -=speed
            Direction.DOWN -> y +=speed
            Direction.LEFT -> x -=speed
            Direction.RIGHT ->x +=speed

        }

    }
    override fun isDestroved(): Boolean {
        if(isDostokyed)return true
        if (x < -width)return true
        if (x > Config.gameWidth)return true
        if (y < -height)return true
        if (y > Config.gameheight)return true

        return  false

    }
    override fun isCollsoon(sufferable: Sufferable): Boolean {
        return checkCollision(sufferable)
    }

    override fun notityAttack(sufferable: Sufferable){
        println("子弹接受到了碰撞....")
        isDostokyed = true


    }





}
