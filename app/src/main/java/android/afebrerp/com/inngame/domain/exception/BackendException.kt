package android.afebrerp.com.inngame.domain.exception

class BackendException(cause: Throwable = Throwable(),
                       exceptionCode: Int = 0,
                       customMessage: String = "")
    : BaseException(cause, exceptionCode, customMessage)