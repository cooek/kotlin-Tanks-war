package org.cg.business

import org.cg.model.Direction
import org.cg.model.views

interface AutoMovable :views{

    val currentdirection:Direction
    val speed:Int
    fun outMovable()



}
/**
 * @return:$
 * @since: 1.0.0
 * @Author:自动移动的能力
 * 接口是一种事务的能力 有这种能力的直接打标记 然后去掉！
 * 抽象方法是事务的本质
 * @Date: 2018/11/22$ 16:19$
 */