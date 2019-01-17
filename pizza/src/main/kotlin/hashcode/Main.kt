package hashcode

const val path = "/Users/naortedgi/Desktop/Google-HashCode-Pizza-Problem/pizza/src/main/kotlin/resources"

enum class INPUT(val PATH: String) {
    EXAMPLE("$path/a_example.in"),
    SMALL("$path/b_small.in"),
    MEDIUM("$path/c_medium.in"),
    BIG("$path/d_big.in");
}


fun main(args: Array<String>) {
    val input = INPUT.EXAMPLE.PATH
    val pizza = PizzaGenerator.create(input)
    val slices = Slicer.slice(pizza)

}

