package org.cg.model

import org.cg.Config
import org.cg.business.Attackable
import org.cg.business.Blockable
import org.cg.business.Destroyable
import org.cg.business.Sufferable
import org.itheima.kotlin.game.core.Painter

class Comp(override var x: Int, override var y: Int) :Blockable,Sufferable,Destroyable{



    override var blood: Int = 12
    override var width: Int = Config.block * 2
    override var height: Int = Config.block* 2

    override fun draw() {
        //bloock低于6是画墙 3 没有樯
        if(blood<=3){
            width = Config.block
            height = Config.block
            x = (Config.gameWidth  - Config.block)/2
            y = Config.gameheight  - Config.block
            Painter.drawImage("img/laogou.jpg",x, y )
        }else if(blood<=6){
            Painter.drawImage("img/zhuan.png",x, y)
            Painter.drawImage("img/zhuan.png",x+32, y)
            Painter.drawImage("img/zhuan.png",x+64, y)
            Painter.drawImage("img/zhuan.png",x+96, y)
            Painter.drawImage("img/zhuan.png",x, y+32)
            Painter.drawImage("img/zhuan.png",x, y+64)
            Painter.drawImage("img/zhuan.png",x+96, y+32)
            Painter.drawImage("img/zhuan.png",x+96, y+64)
            Painter.drawImage("img/laogou.jpg",x + 32, y + 32)


        }else{


        Painter.drawImage("img/qiang.png",x, y)
        Painter.drawImage("img/qiang.png",x+32, y)
        Painter.drawImage("img/qiang.png",x+64, y)
        Painter.drawImage("img/qiang.png",x+96, y)
        Painter.drawImage("img/qiang.png",x, y+32)
        Painter.drawImage("img/qiang.png",x, y+64)
        Painter.drawImage("img/qiang.png",x+96, y+32)
        Painter.drawImage("img/qiang.png",x+96, y+64)
        Painter.drawImage("img/laogou.jpg",x + 32, y + 32)
    }
    }
    override fun notifySuffer(attackable: Attackable): Array<views>? {
        //挨打时候
        blood  -= attackable.attackPower
        return null

    }
    override fun isDestroved(): Boolean  = blood<=0
//    override fun showDestroy(): Array<views>? {
//        return arrayOf(Blast(x - 32,y - 32)
//        ,Blast(x,y-32)
//        ,Blast(x+32,y-32)
//
//        ,Blast(x - 32,y)
//            ,Blast(x,y)
//            ,Blast(x+32,y),
//
//            Blast(x - 32,y + 32)
//            ,Blast(x,y+32)
//            ,Blast(x+32,y+32)
//        )
//    }
}
