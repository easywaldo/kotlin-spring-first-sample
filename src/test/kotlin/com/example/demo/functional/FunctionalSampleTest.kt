package com.example.demo.functional

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.regex.Pattern

@SpringBootTest
class FunctionalSampleTest {
    @Test
    fun fold_test() {
        val numList = listOf(1, 2, 3, 4, 5, 6, 7, 10)
        val result = numList.fold(0, Int::plus)
        println(result)
    }

    @Test
    fun inner_function() {
        fun return_number(number: Int): Int = number + number
        val a = 100
        println(return_number(a))

        fun outer_func(number: Int): Int {
            fun inner_func(number: Int): Int {
                return number * number
            }
            return inner_func(number)
        }

        val b = 20
        println(outer_func(20))
    }

    @Test
    fun extension_function() {
        val counts = "This is an example String multiple words".countWords()
        println(counts)
    }

    @Test
    fun varagrs_func() {
        fun sample(vararg number: Int): Int {
            var total: Int = 0
            for (n in number) {
                total += n
            }
            return total
        }
        println(sample(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11))
    }

    @Test
    fun high_order_function() {
        val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        numbers.forEach {
            println("Called with 4 : ${perf_operation_on_even(it, { it * 2 })}")
        }
    }

    @Test
    fun lambda_sample() {
        unless(3 == 10, {
            println("not same")
        })
    }

    @Test
    fun lambda_varargs_sample() {
        transform(1, 3, 5, 7) { it ->
            it * 2
        }.forEach {
            println(it)
        }
    }

    @Test
    fun unit_lambda_varargs() {
        emit(1, ::println, { it ->
            println(it * 2)
        }, { it ->
            println(it * 5)
        })
    }

    @Test
    fun named_parameters() {
        val c1 = CustomerData(
            firstName = "John",
            middleName = "Waldo",
            lastName = "Charly",
            weight = 91.9,
            height = 183
        )
        println(c1.toString())
    }

    @Test
    fun param_after_varargs() {
        paramAfterVararg(99, "Waldo", "John", "Denial", roomTemperature = 19.5)
    }

    @Test
    fun named_param_high_order_func() {
        high { q, w ->
            println("Age is ${q}")
            println("Name is ${w}")
        }
    }

    @Test
    fun default_param() {
        val programmer = Programer(name = "easywaldo", favoriteLanguage = "Python")
        println(programmer)
        val programerSecond = Programer("easywaldo", "Java", yearsOfExperience = 10)
        println(programerSecond)
    }

    @Test
    fun extension_function_sample() {
        val helloWorld: String = "HelloWorld"
        helloWorld.sendToConsole()
    }

    @Test
    fun class_extension_function_sample() {
        val human = Human(name = "waldo")
        println(human.speak())
    }

    @Test
    fun conflicting_extension_function() {
        val worker = Worker()
        println(worker.work())

        println(worker.work("refactoring"))
        println(worker.rest())
    }

    @Test
    fun extension_function_for_object() {
        Desiner.fastPrototype()
        Desiner.Desk.portfolio().forEach(::println)
    }

    @Test
    fun infix_test() {
        println(1 superOperation 2)
        println(1.superOperation(2))

        All your (Base are Belong to Us)
    }

    @Test
    fun operator_overloading() {
        val talbot = Wolf("Talbot")
        val northPack: Pack = talbot + Wolf("Big Bertha")

        println(northPack.members)
        val result = Wolf("bravo") + Wolf("Easywaldo")
        println(result)

        val biggerPack = northPack + Wolf("Packman")
        println(biggerPack)

    }

    @Test
    fun invoke_test() {
        val wolfman = WolfMan("easywaldo")
        println(wolfman(WolfActions.WALK))

        wolfman.invoke(WolfActions.SLEEP, WolfActions.WALK, WolfActions.BITE)

    }

    @Test
    fun logic_test() {

        var numbers: IntArray = IntArray(3)
        numbers = intArrayOf(1,23,4)


        var sumList = mutableListOf<Int>()
        for ((index, value) in numbers.withIndex()) {
            for ((indexOther, valueOther) in numbers.withIndex()) {
                if (index != indexOther) {
                    sumList.add(value + valueOther)
                }
            }
        }

        sumList.toSet().sorted().forEach {
            println(it)
        }
        var answer: IntArray = intArrayOf()
        answer = sumList.toSet().sorted().toIntArray()
    }

    @Test
    fun indexed_operator() {
        val talbot = Wolf("Talbot")
        val northPack: Pack = talbot + Wolf("Big Bertha")
        val biggerPack = northPack + Wolf("Bad Wolf")
        val badWolf = biggerPack["Bad Wolf"]
        var result = biggerPack.get("Bad Wolf")
        println(result)

        talbot.set(WolfRelationships.ENEMY, badWolf)
        talbot[WolfRelationships.PARTNER] = Wolf("black wolf")
    }

    @Test
    fun unary_operator() {
        val talbot = Wolf("Talbot")
        println(!talbot)
    }

    @Test
    fun join_pipe_test() {
        val joinWithPipe = with(listOf("One", "Two", "Three")) {
            joinToString(separator = "|")
        }
        println(joinWithPipe)


        val html = buildString {
            append("<html>\n")
            append("\t<body>\n")
            append("\t\t<ul>\n")
            listOf(1, 2, 3).forEach { i ->
                append("\t\t\t<li>$i</li>\n")
            }
            append("\t\t<ul>\n")
            append("\t</body>\n")
            append("</htmll>")
        }
        println(html)
    }

    @Test
    fun dsl_sample() {
        val commuter = bicycle {
            description("Fast carbon commuter")
            bar {
                barType = BarType.FLAT
                material = Material.ALUMINIUM
                this@bicycle.frame { }
            }
        }
        println(commuter)
    }

    @Test
    fun inline_function_sample() {
        // compiled result code
        val (_, time) = time(object : Function0<Unit> {
            override fun invoke() {
                Thread.sleep(1000)
            }
        })
        println(time)

        // kotlin functional
        val (_, time2) = time {
            Thread.sleep(1000)
        }
        println("time=$time2")
    }

    @Test
    fun inline_restrictions_sample() {
        val service = UserService(mutableListOf(User(name = "EasyWaldo")))
        service.transformName(String::toLowerCase).forEach {
            println(it)
        }

    }
}

fun String.countWords():Int {
    return trim()
        .split(Pattern.compile("\\s+"))
        .size
}

fun perf_operation_on_even(number: Int, operation: (Int) -> Int): Int {
    if (number %2 == 0) {
        return operation(number)
    } else {
        return number
    }
}

fun unless(condition: Boolean, block: () -> Unit) {
    if (!condition) {
        block()
    }
}

fun <T, R> transform(vararg ts: T, f: (T) -> R): List<R> = ts.map(f)

fun <T> emit(t: T, vararg listeners: (T) -> Unit) = listeners.forEach {
    it(t)
}

typealias Kg = Double
typealias cm = Int
data class CustomerData(val firstName: String,
                        val middleName: String,
                        val lastName: String,
                        val weight: Kg,
                        val height: cm)

fun paramAfterVararg(courseId: Int, vararg  students: String, roomTemperature: Double) {
    // Do something here
    println("do something...")
}

fun high(f: (age: Int, name: String) -> Unit) {
    f(1, "Romeo")
}

data class Programer(val name: String, val favoriteLanguage: String = "Kotlin", val yearsOfExperience: Int = 0)

fun String.sendToConsole() = println(this)

class Human(val name: String)
fun Human.speak(): String = "${this.name} makes a noise"


class Worker {
    fun work() = "*working hard*"
    private fun rest() = "*resting"
}

fun Worker.work() = "*not working so hard*"

fun <T> Worker.work(t: T) = "* working on $t*"

fun Worker.rest() = "*playing video games*"

object Buidler {
}
fun Buidler.builderBrigde() = "A shinny new bridge"
class Desiner {
    companion object {}
    object Desk{}
}
fun Desiner.Companion.fastPrototype() = listOf<String>("1", "2")
fun Desiner.Desk.portfolio() = listOf<String>("Project1", "Project2")

infix fun Int.superOperation(i: Int) = this + i

object All {
    infix fun your(base: Pair<Base, Us>) {}
}
object Base {
    infix fun are(belong: Belong) = this
}

object Belong
object Us

class Wolf(val name: String) {
    operator fun plus(wolf: Wolf) = Pack(mapOf(name to this, wolf.name to wolf))
}

class Pack(val members: Map<String, Wolf>)
operator fun Pack.plus(wolf: Wolf) = Pack(this.members.toMutableMap() + (wolf.name to wolf))

interface Function1<in P1, out R> : Function<R> {
    operator fun invoke(p1: P1): R
}
enum class WolfActions {
    SLEEP, WALK, BITE,
}
class WolfMan(val name: String) {
    operator fun invoke(vararg actions: WolfActions) = actions.forEach {
        action ->
        when(action) {
            WolfActions.SLEEP -> println("$name is sleeping")
            WolfActions.WALK -> println("$name is walking")
            WolfActions.BITE -> println("$name is biting")
        }
    }
}

operator fun Pack.get(name: String) = members[name]!!
enum class WolfRelationships {
    FRIEND, SIBLING, ENEMY, PARTNER
}
operator fun Wolf.set(relationships: WolfRelationships, wolf: Wolf) {
    println("${wolf.name} is my new $relationships")
}

operator fun Wolf.not() = "$name is angry!!!"

interface Element {
    fun render(builder: java.lang.StringBuilder, indent: String)
}
@DslMarker
annotation class ElementMarker

@ElementMarker
abstract class Part(private val name: String) : Element {
    private val children = arrayListOf<Element>()
    protected val attributes = hashMapOf<String, String>()

    protected fun <T: Element> initElement(element: T, init: T.() -> Unit): T {
        element.init()
        children.add(element)
        return element
    }

    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent<$name${renderAttributes()}>\n")
        children.forEach {
            c -> c.render(builder, indent + "\t")
        }
        builder.append("$indent</$name>\n")
    }

    private fun renderAttributes(): String = buildString {
        attributes.forEach {
            attr, value -> append(" $attr=$value")
        }
    }

    override fun toString(): String = buildString{
        render(this, "")
    }
}

enum class Material {
    CARBON, STEEL, TITANIUM, ALUMINIUM
}

enum class BarType {
    DROP, FLAT, TT, BULLHORN
}

enum class Brake {
    RIM, DISK
}
abstract class PartWithMaterial(name: String) : Part(name) {
    var material: Material
        get() = Material.valueOf(attributes["material"]!!)
        set(value) {
            attributes["material"] = value.name
        }
}
class Bicycle : Part("bicycle") {

    fun description(description: String) {
        attributes["description"] = description
    }

    fun frame(init: Frame.() -> Unit) = initElement(Frame(), init)

    fun fork(init: Fork.() -> Unit) = initElement(Fork(), init)

    fun bar(init: Bar.() -> Unit) = initElement(Bar(), init)
}
class Frame : PartWithMaterial("frame") {
    fun backWheel(init: Wheel.() -> Unit) = initElement(Wheel(), init)
}
class Wheel : PartWithMaterial("wheel") {
    var brake: Brake
        get() = Brake.valueOf(attributes["brake"]!!)
        set(value) {
            attributes["brake"] = value.name
        }
}
class Bar : PartWithMaterial("bar") {
    var barType: BarType
        get() = BarType.valueOf(attributes["type"]!!)
        set(value) {
            attributes["type"] = value.name
        }
}
class Fork : PartWithMaterial("fork") {
    fun frontWheel(init: Wheel.() -> Unit) = initElement(Wheel(), init)
}
fun bicycle(init: Bicycle.() -> Unit): Bicycle {
    val cycle = Bicycle()
    cycle.init()
    return cycle
}

fun <T> time(body: () -> T): Pair<T, Long> {
    val startTime = System.nanoTime()
    val v = body()
    val endTime = System.nanoTime()
    return v to endTime - startTime
}

data class User(val name: String)
class UserService {
    val users = mutableListOf<User>()
    val listeners = mutableListOf<(User) -> Unit>()
    constructor(users: List<User>) {
        users.forEach {
            this.users.add(it)
        }
    }

    fun addListener(listener: (User) -> Unit) {
        listeners += listener
    }
    inline fun transformName(crossinline transform: (name: String) -> String): List<User> {

        val buildUser = { name: String ->
            User(transform(name))
        }

        return users.map { user -> buildUser(user.name) }
    }
}
