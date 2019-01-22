package android.afebrerp.com.inngame.domain.exception

abstract class BaseException(override val cause: Throwable = Throwable(), val exceptionCode: Int = 0, val customMessage: String = "")
    : RuntimeException(cause)