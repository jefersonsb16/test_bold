package com.jefersonsalazar.test.testbold.features

import android.app.AlertDialog
import android.content.Context
import com.jefersonsalazar.test.domain.ErrorDomain
import com.jefersonsalazar.test.testbold.R

const val BAD_REQUEST = 400
const val UNAUTHORIZED = 401
const val NOT_FOUND = 404
const val SERVER_ERROR = 500

class ShowErrorFactory {

    fun getDialog(context: Context, error: ErrorDomain): AlertDialog.Builder {
        return when (error) {
            ErrorDomain.Connectivity -> {
                AlertDialog.Builder(context)
                    .setTitle(context.getString(R.string.error))
                    .setMessage(context.getString(R.string.message_not_internet_text))
            }
            is ErrorDomain.Server -> {
                AlertDialog.Builder(context)
                    .setTitle(context.getString(R.string.error))
                    .setMessage(context.getString(getServerErrorMessage(error.code)))
            }
            is ErrorDomain.Unknown -> {
                AlertDialog.Builder(context)
                    .setTitle(context.getString(R.string.error))
                    .setMessage(error.message)
            }
        }
    }

    private fun getServerErrorMessage(errorCode: Int): Int {
        return when (errorCode) {
            BAD_REQUEST -> {
                R.string.bad_request_error_text
            }
            UNAUTHORIZED -> {
                R.string.unauthorized_error_text
            }
            NOT_FOUND -> {
                R.string.not_found_request_error_text
            }
            SERVER_ERROR -> {
                R.string.server_error_text
            }
            else -> {
                R.string.generic_error_text
            }
        }
    }
}