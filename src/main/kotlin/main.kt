class CoffeeMachine {
    private var amountWater: Int = 0
    private var amountMilk: Int = 0
    private var amountBeans: Int = 0
    private var cups: Int = 0

    override fun toString(): String {
        return """
            For $cups cups of coffee you will need:
            ${200 * cups} ml of water
            ${50 * cups} ml of milk
            ${15 * cups} g of coffee beans
        """.trimIndent()
    }

    init {
        print("Write how many ml of water the coffee machine has: ")
        amountWater = readLine()!!.toInt()

        print("Write how many ml of milk the coffee machine has: ")
        amountMilk = readLine()!!.toInt()

        print("Write how many grams of coffee beans the coffee machine has: ")
        amountBeans = readLine()!!.toInt()

        print("Write how many cups of coffee you will need: ")
        cups = readLine()!!.toInt()
    }

    fun checkCoffee() {
        return when {
            cups > amountOfCoffee() -> println("No, I can make only ${amountOfCoffee()} cups of coffee")
            cups == amountOfCoffee() -> println("Yes, I can make that amount of coffee")
            amountOfCoffee() > cups -> println("Yes, I can make that amount of coffee " +
                    "(and even ${amountOfCoffee() - cups} more than that)")
            else -> println("Invalid")
        }
    }

    private fun amountOfCoffee() = minOf(amountWater / needWater, amountMilk / needMilk, amountBeans / needCoffeeBeans)

    companion object {
        const val needWater: Int = 200
        const val needMilk: Int = 50
        const val needCoffeeBeans: Int = 15
    }
}

fun main() = CoffeeMachine().checkCoffee()