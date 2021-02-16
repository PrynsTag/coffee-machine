open class CoffeeMachine(
    open var amountWater: Int = 400, open var amountMilk: Int = 540, open var amountBeans: Int = 120,
    open var disposableCups: Int = 9, open var amountMoney: Int = 550
) {

    data class Espresso(
        override var amountWater: Int = 250, override var amountBeans: Int = 16,
        override var disposableCups: Int = 1, override var amountMoney: Int = 4
    ) : CoffeeMachine()

    data class Latte(
        override var amountWater: Int = 350, override var amountBeans: Int = 20,
        override var disposableCups: Int = 1, override var amountMoney: Int = 7,
        override var amountMilk: Int = 75,
    ) : CoffeeMachine()

    data class Cappuccino(
        override var amountWater: Int = 200, override var amountBeans: Int = 12,
        override var disposableCups: Int = 1, override var amountMoney: Int = 6,
        override var amountMilk: Int = 100,
    ) : CoffeeMachine()

    override fun toString(): String {
        return """
            The coffee machine has:
            $amountWater of water
            $amountMilk of milk
            $amountBeans of coffee beans
            $disposableCups of disposable cups
            $amountMoney of money
        """.trimIndent()
    }

    fun startMachine() {
        println(this)
        print("\nWrite action (buy, fill, take): ")
        when (readLine()!!) {
            "buy" -> buyCoffee()
            "fill" -> fillMachine()
            "take" -> takeMoney()
        }
    }

    private fun buyCoffee() {
        print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ")
        when (readLine()!!.toInt()) {
            1 -> reduceResource(Espresso())
            2 -> reduceResource(Latte())
            3 -> reduceResource(Cappuccino())
            else -> println("Wrong Input..")
        }
    }

    private fun reduceResource(coffee: CoffeeMachine) {
        println()
        this.amountWater -= coffee.amountWater
        if (coffee !is Espresso) this.amountMilk -= coffee.amountMilk
        this.amountBeans -= coffee.amountBeans
        this.disposableCups -= coffee.disposableCups
        this.amountMoney += coffee.amountMoney
        println(this)
    }

    private fun fillMachine() {
        print("Write how many ml of water do you want to add: ")
        this.amountWater += readLine()!!.toInt()

        print("Write how many ml of milk do you want to add: ")
        this.amountMilk += readLine()!!.toInt()

        print("Write how many grams of coffee beans do you want to add: ")
        this.amountBeans += readLine()!!.toInt()

        print("Write how many disposable cups of coffee do you want to add: ")
        this.disposableCups += readLine()!!.toInt()
        println()
        print(this)
    }

    private fun takeMoney() {
        println("I gave you $$amountMoney\n")
        this.amountMoney = 0
        println(this)
    }
}

fun main() = CoffeeMachine().startMachine()