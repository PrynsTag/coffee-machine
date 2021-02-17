open class CoffeeMachine(
    open var amountWater: Int = 400,
    open var amountMilk: Int = 540,
    open var amountBeans: Int = 120,
    open var disposableCups: Int = 9,
    open var amountMoney: Int = 550,
) {

    private data class Espresso(
        override var amountWater: Int = 250,
        override var amountBeans: Int = 16,
        override var disposableCups: Int = 1,
        override var amountMoney: Int = 4,
    ) : CoffeeMachine()

    private data class Latte(
        override var amountWater: Int = 350,
        override var amountBeans: Int = 20,
        override var disposableCups: Int = 1,
        override var amountMoney: Int = 7,
        override var amountMilk: Int = 75,
    ) : CoffeeMachine()

    private data class Cappuccino(
        override var amountWater: Int = 200,
        override var amountBeans: Int = 12,
        override var disposableCups: Int = 1,
        override var amountMoney: Int = 6,
        override var amountMilk: Int = 100,
    ) : CoffeeMachine()

    override fun toString(): String {
        return "\nThe coffee machine has:\n" +
                "$amountWater of water\n" +
                "$amountMilk of milk\n" +
                "$amountBeans of coffee beans\n" +
                "$disposableCups of disposable cups\n" +
                "$$amountMoney of money\n"
    }

    fun startMachine() {
        while (true) {
            print("Write action (buy, fill, take, remaining, exit): ")
            when (readLine()!!) {
                "buy" -> buyCoffee()
                "fill" -> fillMachine()
                "take" -> takeMoney()
                "remaining" -> println(this)
                "exit" -> return
            }
        }
    }

    private fun buyCoffee() {
        print("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
        when (readLine()!!) {
            "1" -> reduceResource(Espresso())
            "2" -> reduceResource(Latte())
            "3" -> reduceResource(Cappuccino())
            "back" -> return
            else -> println("Wrong Input..")
        }
    }

    private fun reduceResource(coffee: CoffeeMachine) {
        when {
            this.amountWater - coffee.amountWater <= 0 -> return println("Sorry not enough water!\n")
            this.amountBeans - coffee.amountBeans <= 0 -> return println("Sorry not enough beans!\n")
            this.disposableCups - coffee.disposableCups <= 0 -> return println("Sorry not enough cups!\n")
            coffee !is Espresso -> {
                if (this.amountMilk - coffee.amountMilk <= 0)
                    return println("Sorry not enough milk!")
                else
                    this.amountMilk -= coffee.amountMilk
            }
        }

        this.amountWater -= coffee.amountWater
        this.amountBeans -= coffee.amountBeans
        this.disposableCups -= coffee.disposableCups
        this.amountMoney += coffee.amountMoney
        println("I have enough resources, making you a coffee!\n")
    }

    private fun fillMachine() {
        println()
        print("Write how many ml of water do you want to add: ")
        this.amountWater += readLine()!!.toInt()

        print("Write how many ml of milk do you want to add: ")
        this.amountMilk += readLine()!!.toInt()

        print("Write how many grams of coffee beans do you want to add: ")
        this.amountBeans += readLine()!!.toInt()

        print("Write how many disposable cups of coffee do you want to add: ")
        this.disposableCups += readLine()!!.toInt()
        println()
    }

    private fun takeMoney() {
        println()
        println("I gave you $$amountMoney\n")
        this.amountMoney = 0
    }
}

fun main() = CoffeeMachine().startMachine()