package android.afebrerp.com.inngame.data.responseutils

import android.util.Log
import com.google.gson.Gson
import retrofit2.HttpException

object BackendResponseMapper {


    fun parseHttpException(httpException: HttpException): String {
        var errorMessage = ""
        try {
            val errorJsonString = httpException.response().errorBody()?.string()
            val basicError = Gson().fromJson(errorJsonString, BasicError::class.java)
            errorMessage = if (basicError?.errors != null && basicError.errors.isEmpty()) {
                basicError.errors[0]
            } else {
                "UnHandled Error Message"
            }

        } catch (e: Exception) {
            Log.e("BackendResponseMapper", e.toString())
        } finally {
            return errorMessage
        }
    }

}