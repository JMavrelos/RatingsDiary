package gr.blackswamp.core

import gr.blackswamp.core.logging.ILog

object TestLog :ILog {
    override fun d(tag: String, message: String, throwable: Throwable?) {
        println("Debug $tag : $message ($throwable)")
    }
    override fun v(tag: String, message: String, throwable: Throwable?) {
        println("Verbose $tag : $message ($throwable)")
    }
    override fun i(tag: String, message: String, throwable: Throwable?) {
        println("Info $tag : $message ($throwable)")
    }
    override fun w(tag: String, message: String, throwable: Throwable?) {
        println("Warn $tag : $message ($throwable)")
    }
    override fun e(tag: String, message: String, throwable: Throwable?) {
        println("Error $tag : $message ($throwable)")
    }
    override fun wtf(tag: String, message: String, throwable: Throwable?) {
        println("WTF $tag : $message ($throwable)")
    }
}