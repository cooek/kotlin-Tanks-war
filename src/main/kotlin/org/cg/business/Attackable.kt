package org.cg.business

import org.cg.model.views

/**
 * @return:$
 * @since: 1.0.0
 * @Author:攻击能力
 * @Date: 2018/11/23$ 16:27$
 */
interface Attackable:views {
    val owner:views





    val attackPower:Int
    fun isCollsoon(sufferable: Sufferable):Boolean

    fun notityAttack(sufferable: Sufferable)

}
