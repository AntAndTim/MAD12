package me.antandtim.mad12.card.util

import android.os.CountDownTimer
import java.time.Instant
import java.time.temporal.ChronoUnit

/**
 * Interface providing a method to bind the text string
 * */
interface ExpirationBinder {
    fun bind(expirationTime: String)
}

/**
 * This function creates a timer which updates the time in the field
 * */
fun ExpirationBinder.bindExpireDate(
    expireDate: Instant,
    finishedText: String = "Expired!",
    interval: Long = 1000
) {
    object : CountDownTimer(
        Instant.now().until(expireDate, ChronoUnit.MILLIS),
        interval
    ) {
        override fun onFinish() {
            this@bindExpireDate.bind(finishedText)
        }

        override fun onTick(millisUntilFinished: Long) {
            this@bindExpireDate.bind(expireDate.getTimeLeft())
        }
    }.start()
}

/**
 * Function returns time difference between current time and card expiration time in the form of
 * hh::mm::ss e.g. 23:59:57
 * */
fun Instant.getTimeLeft(): String {
    val hoursLeft = Instant.now().until(this, ChronoUnit.HOURS)
    val minutesLeft = Instant.now().until(this, ChronoUnit.MINUTES) - hoursLeft * 60
    val secondsLeft = Instant.now()
        .until(this, ChronoUnit.SECONDS) - hoursLeft * 3600 - minutesLeft * 60

    return StringBuilder()
        .append(hoursLeft.asTime())
        .append(":")
        .append(minutesLeft.asTime())
        .append(":")
        .append(secondsLeft.asTime())
        .toString()
}

private fun Long.asTime() = String.format("%02d", this)