package org.cg.model

import org.cg.Config
import org.cg.business.*
import org.itheima.kotlin.game.core.Painter
import kotlin.random.Random

/**
 * @return:$
 * @since: 1.0.0
 * @Author:$
 * @Date: 2018/11/23$ 19:04$
 */
class Enemy(override var x: Int, override var y: Int) :Movable,AutoMovable,Blockable,AuttoSbot,Sufferable,Destroyable{


    override var blood: Int = 5
    private var badDireoction:Direction? = null
    override var currentDirection: Direction = Direction.DOWN
    override val speed: Int = 3
    private  var lasstaShottime = 0L
    private var shotpraquency = 800
    private  var lastMoveTime = 0L
    private var Movableover = 50
    override val width: Int = Config.block
    override val height: Int = Config.block


    override fun draw() {
       val imgagePaths = when(currentDirection){

            Direction.UP -> "img/ds.png"
            Direction.DOWN -> "img/dx.png"
            Direction.LEFT ->"img/dz.png"
            Direction.RIGHT -> "img/dy.png"
        }
        Painter.drawImage(imgagePaths,x, y)


    }
//    override fun willCollision(block: Blockable): Direction? {
//        return null
//
//
//    }
    override fun notifyCollision(direction: Direction?, block: Blockable?) {
         badDireoction = direction
    }
    override fun outMovable() {
        val  curren:Long = System.currentTimeMillis()
        if (curren - lastMoveTime < Movableover)return
        lastMoveTime = curren

        if (currentDirection == badDireoction){
            currentDirection  = edmDirection(badDireoction)
            return
        }
        when(currentDirection){
            Direction.UP -> y -= speed
            Direction.DOWN -> y +=speed
            Direction.LEFT -> x -=speed
            Direction.RIGHT ->x +=speed
        }
        //xy越界判断
        if(x < 0) x = 0
        if(x > Config.gameWidth - width) x = Config.gameWidth - width
        if(y < 0) y = 0
        if (y > Config.gameheight - height) y = Config.gameheight - height

    }
    override val currentdirection: Direction
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    private fun edmDirection(bad:Direction?):Direction{
        val  i = Random.nextInt(4)
        val  direction:Direction= when(i){
            0->Direction.UP
            1->Direction.DOWN
            2->Direction.LEFT
            3->Direction.RIGHT
            else ->Direction.UP
        }
        if (direction == bad){
            return edmDirection(bad) //尾递归调用
        }
        return direction

    }

    override fun autoShot(): views? {
        val  curren:Long = System.currentTimeMillis()
        if (curren - lasstaShottime < shotpraquency)return null
        lasstaShottime = curren

            return Bullet(this,currentDirection, { bulletWidth, bulletHeighe ->
                val tankx = x
                val tankey = y
                val tankeWidth = width
                val tankeHeight = height
                //子弹的方法
                var bulletX: Int = 0
                var bulletY: Int = 0
                when (currentDirection) {
                    Direction.UP -> {
                        bulletX = tankx + (tankeWidth - bulletWidth) / 2
                        bulletY = tankey - bulletHeighe / 2
                    }
                    Direction.DOWN -> {
                        bulletX = tankx + (tankeWidth - bulletWidth) / 2
                        bulletY = tankey + tankeHeight - bulletHeighe / 2
                    }
                    Direction.LEFT -> {
                        bulletX = tankx - bulletWidth / 2
                        bulletY = tankey + (tankeHeight - bulletHeighe) / 2
                    }
                    Direction.RIGHT -> {
                        bulletX = tankx + tankeWidth - bulletWidth / 2
                        bulletY = tankey + (tankeHeight - bulletHeighe) / 2
                    }
                }
                Pair(bulletX, bulletY)
            })


        }

    override fun notifySuffer(attackable: Attackable): Array<views>? {
        if(attackable.owner is Enemy){
            return null
        }
        blood -= attackable.attackPower
        return  arrayOf(Blast(x,y))

    }
    override fun isDestroved(): Boolean =  blood <= 0


}
