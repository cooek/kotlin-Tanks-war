package org.cg.exit

import org.cg.model.views

/**
 * @return:$
 * @since: 1.0.0
 * @Author:$
 * @Date: 2018/11/23$ 17:03$
 */
fun views.checkCollision(view: views):Boolean{
    return   checkCollision(x,y,width,height,view.x,view.y,view.width,view.height)

  }