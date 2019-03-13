package org.cg.model

/**
 * views 视图规则
 */

interface views {
    val x:Int
    val y:Int
    val width:Int
    val height:Int

    fun  draw()
    fun checkCollision(x1:Int,y1:Int,w1:Int,h1:Int,x2:Int,y2:Int,w2:Int,h2:Int):Boolean{
        return  when {
            y2 + h2<= y1 ->
                false
            y1 + h1 <=y2 ->
                false
            x2 + w2 <= x1 ->
                false
            else -> x1 + w1>x2
        }
    }
//    fun checkCollision(view: views):Boolean{
//        return false
//    }


}