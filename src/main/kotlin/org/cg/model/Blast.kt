package org.cg.model

import org.cg.Config
import org.cg.business.Destroyable
import org.itheima.kotlin.game.core.Composer
import org.itheima.kotlin.game.core.Painter

/**
 * 功能描述: <br>
 * 〈$〉
 *
$
 * @return:$
 * @since: 1.0.0
 * @Author:$
 * @Date: 2018/11/23$ 18:30$
 */
class  Blast(override val x: Int, override val y: Int) :views,Destroyable{


    override val width: Int = Config.block
    override val height: Int = Config.block
    private  val imagePath:ArrayList<String> = arrayListOf<String>()
    private  var index:Int = 0
    init {
        (1..10).forEach {
            imagePath.add("img/${it}.png")
        }
    }
    override fun draw() {
        val i:Int =index % imagePath.size
        Painter.drawImage(imagePath[i],x,y)
        index++
    }
    override fun isDestroved(): Boolean {
        return index >=imagePath.size
    }


}