class CoffeeMachine(
    private var amountWater: Int = 400,
    private var amountMilk: Int = 540,
    private var amountBeans: Int = 120,
    private var disposableCups: Int = 9,
    private var amountMoney: Int = 550,
) {
    enum class Coffee(
        val water: Int,
        val milk: Int,
        val beans: Int,
        val money: Int,
        val disposableCups: Int = 1,
    ) {
        ESPRESSO(250, 0, 16, 4),
        LATTE(350, 75, 20, 7),
        CAPPUCCINO(200, 100, 12, 6);
    }

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
            "1" -> prepareCoffee(Coffee.ESPRESSO)
            "2" -> prepareCoffee(Coffee.LATTE)
            "3" -> prepareCoffee(Coffee.CAPPUCCINO)
            "back" -> return
            else -> println("Wrong Input..")
        }
    }

    private fun prepareCoffee(coffee: Coffee) {
        when {
            this.amountWater - coffee.water <= 0 -> return println("Sorry not enough water!\n")
            this.amountBeans - coffee.beans <= 0 -> return println("Sorry not enough beans!\n")
            this.disposableCups - coffee.disposableCups <= 0 -> return println("Sorry not enough cups!\n")
            coffee != Coffee.ESPRESSO -> {
                if (this.amountMilk - coffee.milk <= 0)
                    return println("Sorry not enough milk!")
                else
                    this.amountMilk -= coffee.milk
            }
        }

        this.amountWater -= coffee.water
        this.amountBeans -= coffee.beans
        this.disposableCups -= coffee.disposableCups
        this.amountMoney += coffee.money
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
        println("\nI gave you $$amountMoney\n")
        this.amountMoney = 0
    }
}

fun main() = CoffeeMachine().startMachine()