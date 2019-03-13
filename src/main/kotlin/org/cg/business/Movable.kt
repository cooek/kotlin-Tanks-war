package org.cg.business

import org.cg.Config
import org.cg.model.Direction
import org.cg.model.views

interface Movable :views {

    /**
     * 可以移动的物体存在的方向
     *
     */
    val  currentDirection:Direction
    /**
     * 可以移动的物体速度
     */
    val  speed:Int

    /**
     * 判定移动的物体是否和阻挡物体发生碰撞
     */
    fun willCollision(block:Blockable):Direction?{

            var x:Int = this.x
            var y:Int = this.y
            when(currentDirection){
                Direction.UP -> y -= speed
                Direction.DOWN -> y +=speed
                Direction.LEFT -> x -=speed
                Direction.RIGHT ->x +=speed
            }
            if(x < 0) return  Direction.LEFT
            if(x > Config.gameWidth - width)return  Direction.RIGHT
            if(y < 0)return  Direction.UP
            if (y > Config.gameheight - height)return Direction.DOWN


        val collliston:Boolean = checkCollision(block.x,block.y,block.width,block.height,x,y,width,height)
            return if(collliston)currentDirection else null
    }
    /**
     * 通知碰消息
     */
    fun notifyCollision(direction: Direction?,block: Blockable?)

}