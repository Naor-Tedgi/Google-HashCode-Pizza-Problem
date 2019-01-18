package hashcode


lateinit var path: String

enum class INPUT(val PATH: String) {
    EXAMPLE("$path/a_example.in"),
    SMALL("$path/b_small.in"),
    MEDIUM("$path/c_medium.in"),
    BIG("$path/d_big.in");
}


fun main(args: Array<String>) {
    path = getResourcesDir()
    val input = INPUT.EXAMPLE.PATH
    val pizza = PizzaGenerator.create(input)
    val slices = Slicer.slice(pizza)

}

private fun getResourcesDir() = arrayListOf<String>(System.getProperty("user.dir"), "src", "main", "kotlin", "resources").joinToString("/")

