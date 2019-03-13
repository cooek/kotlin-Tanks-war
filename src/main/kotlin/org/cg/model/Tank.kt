package org.cg.model

import javafx.beans.binding.When
import org.cg.business.Blockable
import org.cg.business.Movable
import org.cg.Config
import org.cg.business.Attackable
import org.cg.business.Sufferable
import org.itheima.kotlin.game.core.Painter


/**
 * 我方坦克
 */
class  Tank(override var x: Int,override var y: Int):Movable,Blockable,Sufferable{


    override var blood: Int = 20
    override val width: Int =org.cg.Config.block
    override val height: Int =org.cg.Config.block

    override var currentDirection:Direction = Direction.UP
    override  var speed:Int = 8 //思聪移动速度
    private var badDireoction:Direction? = null //

    override fun draw() {
        when(currentDirection){
            //我的坦克方向资源
            Direction.UP -> Painter.drawImage("img/s11.png", x, y)
            Direction.DOWN -> Painter.drawImage( "img/s9.png" ,x, y)
            Direction.LEFT -> Painter.drawImage("img/s7.png" ,x, y)
            Direction.RIGHT -> Painter.drawImage( "img/s8.png" ,x, y)
        }

    }
    fun move(direction: Direction){
        if (direction == badDireoction){
            return
        }
        //转动方向
        if (this.currentDirection != direction){
            this.currentDirection = direction
            return

        }
        this.currentDirection = direction
        //移动速度
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
//    override fun willCollision(block: Blockable): Direction? {
//        var x:Int = this.x
//        var y:Int = this.y
//
//
//        when(currentDirection){
//            Direction.UP -> y -= speed
//            Direction.DOWN -> y +=speed
//            Direction.LEFT -> x -=speed
//            Direction.RIGHT ->x +=speed
//        }
//
//        //TODO: jiance
////        val collliston:Boolean =  when {
////            block.y + block.height <= y ->
////                false
////            y + height <=block.y ->
////                false
////            block.x + block.width <= x ->
////                false
////            else -> x + width>block.x
////        }
//        val collliston:Boolean = checkCollision(block.x,block.y,block.width,block.height,x,y,width,height)
//        return if(collliston)currentDirection else null
//    }
    override fun notifyCollision(direction: Direction?, block: Blockable?) {
        this.badDireoction  = direction
    }

  fun shot():Bullet{
      return Bullet(this,currentDirection,{ bulletWidth,bulletHeighe ->
          val tankx = x
          val tankey = y
          val  tankeWidth = width
          val  tankeHeight = height
          //子弹的方法
          var  bulletX :Int = 0
          var  bulletY :Int = 0
          when(currentDirection){
              Direction.UP -> {
                  bulletX = tankx + (tankeWidth - bulletWidth) / 2
                  bulletY = tankey - bulletHeighe/2
              }
              Direction.DOWN ->{
                  bulletX = tankx + (tankeWidth - bulletWidth) / 2
                  bulletY = tankey + tankeHeight - bulletHeighe / 2
              }
              Direction.LEFT ->{
                  bulletX = tankx - bulletWidth/ 2
                  bulletY = tankey +( tankeHeight - bulletHeighe)/2
              }
              Direction.RIGHT ->{
                 bulletX = tankx + tankeWidth - bulletWidth / 2
                 bulletY = tankey +(tankeHeight - bulletHeighe)/2
              }
          }
       Pair(bulletX,bulletY)
      })
  }


    override fun notifySuffer(attackable: Attackable): Array<views>? {
        blood -= attackable.attackPower
        return  arrayOf(Blast(x,y))


    }

}
