package hashcode

import astar.ISearchNode
import java.util.ArrayList


class PizzaSliccerSearchNode(val pizza: Pizza, val src: Point, parent: ISearchNode? = null) : ISearchNode {
    private var parent: PizzaSliccerSearchNode? = null

    override fun getParent(): PizzaSliccerSearchNode? {
        return parent
    }

    override fun setParent(parent: ISearchNode?) {
        this.parent = parent as PizzaSliccerSearchNode?
    }

    override fun h(): Double {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var gLocal: Double

    init {
        gLocal = 0.0
    }

    override fun g(): Double {
        return gLocal
    }

    override fun setG(g: Double) {
        gLocal = g
    }

    override fun c(successor: ISearchNode?): Double {
        if (successor != null) {
            return successor.g()
        }
        return 1.0
    }

    override fun getSuccessors(): ArrayList<ISearchNode> {
        val succesores = Slicer.slice(pizza, src)
        return ArrayList<ISearchNode>().apply {
            addAll(succesores)
        }

    }

    override fun keyCode(): Int {
        return pizza.hashCode()
    }

    override fun f(): Double {
        return gLocal
    }


}