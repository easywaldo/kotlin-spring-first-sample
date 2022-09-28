package com.example.demo.pattern.adapter

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.stream.Stream

@SpringBootTest
class AdapterTest {
    @Test
    fun test() {
        val cellPhone = cellPhone(
            charger(
                usPowerOutlet().toEUPlug()
            ).toUsbTypeC()
        )

        val usPowerCellPhone = cellPhone(usCharger(usPowerOutlet()))

        val brokenPhone = cellPhone(brokenCharger(usPowerOutlet()))

        println(cellPhone.hasPower)
        println(usPowerCellPhone.hasPower)
        println(brokenPhone.hasPower)
    }

    @Test
    fun test2() {
        val list = listOf("a", "b", "c")
        printStream(list.stream())

        val stream = Stream.generate { 42 }
        stream.toList()
    }
}

fun printStream(stream: Stream<String>) {
    stream.forEach {
        println(it)
    }
}

interface USPlug {
    val hasPower: Int
}

interface EUPlug {
    val hasPower: String // "TRUE" or "FALSE"
}

interface UsbMini {
    val hasPower: Power
}
enum class Power {
    TRUE, FALSE
}

interface UsbTypeC {
    val hasPower: Boolean
}

class Phone(hasPower: Boolean) {
    val hasPower: Boolean = hasPower
}

fun cellPhone(chargeCable: UsbTypeC): Phone {
    if (chargeCable.hasPower) {
        println("I've Got The Power!")
    } else {
        println("No power")
    }

    return Phone(chargeCable.hasPower)
}

// Power outlet exposes USPlug interface
fun usPowerOutlet(): USPlug {
    return object : USPlug {
        override val hasPower = 1
    }
}

// Charger accepts EUPlug interface and exposes UsbMini interface
fun charger(plug: EUPlug): UsbMini {
    return object : UsbMini {
        override val hasPower=Power.valueOf(plug.hasPower)
    }
}

fun usCharger(plug: USPlug): UsbTypeC {
    return object : UsbTypeC {
        override val hasPower: Boolean = true
    }
}

fun brokenCharger(plug: USPlug): UsbTypeC {
    return object : UsbTypeC {
        override val hasPower: Boolean
            get() = false
    }
}

fun USPlug.toEUPlug(): EUPlug {
    val hasPower = if (this.hasPower == 1) "TRUE" else "FALSE"
    return object : EUPlug {
        // Transfer power
        override val hasPower = hasPower
    }
}

fun UsbMini.toUsbTypeC(): UsbTypeC {
    val hasPower = this.hasPower == Power.TRUE
    return object : UsbTypeC {
        override val hasPower = hasPower
    }
}