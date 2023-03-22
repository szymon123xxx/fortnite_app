package com.inzynierkaApp.app.sample.ui.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

fun ImageView.loadFromUrl(
    url: String?,
    placeholder: Int? = null,
    doOnError: (() -> Unit)? = null,
    doOnSuccess: (() -> Unit)? = null
) =
    Glide.with(context)
        .load(url)
        .run {
            placeholder?.let {
                placeholder(placeholder)
            } ?: this
        }
        .listener(provideGlideRequestListener(doOnError, doOnSuccess)).also {
            it.into(this)
        }

private fun provideGlideRequestListener(
    doOnSuccess: (() -> Unit)? = null,
    doOnError: (() -> Unit)? = null,
): RequestListener<Drawable> {
    return object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            doOnError?.invoke()
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            doOnSuccess?.invoke()
            return false
        }
    }
}
