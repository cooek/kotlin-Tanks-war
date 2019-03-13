package org.cg.business

import org.cg.model.views
import java.sql.Array

/**
 * @return:$
 * @since: 1.0.0
 * @Author:$
 * @Date: 2018/11/23$ 16:29$
 */
interface Sufferable :views{
    val  blood:Int
    //接受
    fun notifySuffer(attackable: Attackable):kotlin.Array<views>?

}
