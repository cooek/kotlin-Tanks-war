package org.cg

import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import org.cg.business.*
import org.cg.model.*
import org.itheima.kotlin.game.core.Window
import org.omg.CORBA.INTERNAL
import java.io.File
import java.util.concurrent.CopyOnWriteArrayList


/***
 *继承游戏引擎Window 传入参数重新窗口代码
 *
 */

class Gamewindon:Window("思聪大战1.0","img/s1.png",height = Config.gameheight,width = Config.gameWidth) {
    // var wall = Wall(100,100) //model层创建
    //var grass = Grass(200,200)
    //管理元素集合
    val viewss = CopyOnWriteArrayList<views>()
    private lateinit var tankes :Tank
    private var gemeOver:Boolean = false
    private  var enemTotalSiza = 20
    private  var enemvActivtySiza = 6
    private var enemBo = arrayListOf<Pair<Int,Int>>()
    private  var bodlndex = 0

    override fun onCreate() {
        println("onCreate")
        val file = File(javaClass.getResource("/map/1.map").path)
        val lins = file.readLines()
        var lineNum = 0
        lins.forEach {
            //拿到每一行
            var  ColumNum = 0
            it.toCharArray().forEach {
                when (it) {
                    '砖' -> {
                        viewss.add(Wall(ColumNum * Config.block, lineNum * Config.block))
                    }
                    '铁' -> {
                        viewss.add(Steel(ColumNum * Config.block, lineNum * Config.block))
                    }
                    '草' -> {
                    viewss.add(Grass(ColumNum * Config.block, lineNum * Config.block))
                }
                    '水' -> {
                        viewss.add(Water(ColumNum * Config.block, lineNum * Config.block))
                    }
                    '敌' ->{
                        enemBo.add(Pair(ColumNum*Config.block,lineNum*Config.block))
                    }
                }
                ColumNum ++
            }
            lineNum ++
        }
        tankes = Tank(Config.block*10,Config.block*12)
        viewss.add(tankes)
        //添加元素
        viewss.add(Comp(Config.gameWidth/2 - Config.block,Config.gameheight - 96))

    }
    override fun onDisplay() {
       //绘制一直运行
        //绘制modle层
        viewss.forEach{
            it.draw()
        }
        println("${viewss.size}")

    }

    override fun onKeyPressed(event: KeyEvent) {
        //按钮回调
        if(!gemeOver){

        when (event.code) {
            KeyCode.W -> {
                tankes.move(Direction.UP)
            }
            KeyCode.S -> {
                tankes.move(Direction.DOWN)
            }
            KeyCode.A -> {
                tankes.move(Direction.LEFT)
            }
            KeyCode.D -> {
                tankes.move(Direction.RIGHT)
            }
            KeyCode.ENTER ->{
                val bullet = tankes.shot()
                viewss.add(bullet)
            }
        }

        }
    }
    override fun onRefresh() {
        //刷新 业务逻辑
        //判断运动的物体和阻塞物体是否发生碰撞
        viewss.filter { it is Destroyable }.forEach {it
            if ((it as Destroyable).isDestroved()){
                viewss.remove(it)
                if ( it is Enemy){
                    enemTotalSiza --
                }

            }

        }
        println("onRefresh")
        if (gemeOver)return

        viewss.filter { it is Movable }.forEach { move ->
            //找到阻塞的物体
            move as Movable
            //方向
            var badDirection:Direction? = null
            //阻塞
            var badBlock:Blockable? = null
            viewss.filter { (it is Blockable) and (move !=it) }.forEach Tag@{ block ->
                //找到运动的物体
                block as Blockable
                val direction:Direction? = move.willCollision(block)
                direction?.let {
                    badDirection = direction
                    badBlock  = block
                    return@Tag //返回循环
                }
            }
            move.notifyCollision(badDirection,badBlock)
        }
        //自动检测
        viewss.filter { it is AutoMovable }.forEach {
            (it as AutoMovable).outMovable()
        }
//        viewss.filter { it is Destroyable }.forEach {it
//           if ((it as Destroyable).isDestroved()){
//               viewss.remove(it)
//
//           }
//
//        }
        viewss.filter { it is Attackable }.forEach {attack ->
            attack as Attackable

            viewss.filter { (it is Sufferable)and (attack.owner !=it) and (attack != it) }.forEach suffer@ {suffer ->
                suffer as Sufferable
                if(attack.isCollsoon(suffer)){
                    attack.notityAttack(suffer)
                    val sufferview = suffer.notifySuffer(attack)
                    sufferview?.let {
                        viewss.addAll(sufferview)
                    }
                    return@suffer
                    //找到碰撞者，并通知攻击者

                }


            }

        }
        viewss.filter { it is AuttoSbot }.forEach {
            it as AuttoSbot
            val Sbot:views? = it.autoShot()
            Sbot?.let {
                 viewss.add(Sbot)
            }
        }
        if((viewss.filter {it is Comp }.isEmpty()or(enemTotalSiza<=0))){
            gemeOver = true

        }
        //小于我们就激活
        if((enemTotalSiza >0) and ( viewss.filter { it is Enemy }.size < enemvActivtySiza)){
            val index = bodlndex % enemBo.size
            val pair = enemBo[index]

            viewss.add(Enemy(pair.first,pair.second))
            bodlndex ++

        }



    }

}