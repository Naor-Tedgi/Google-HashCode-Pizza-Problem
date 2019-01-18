package hashcode

import astar.IGoalNode
import astar.ISearchNode

class PizzaSliccerGoalNode(val pizza: Pizza) : IGoalNode {
    override fun inGoal(other: ISearchNode?): Boolean {
        other as PizzaSliccerSearchNode
        return other.pizza == pizza
    }
}