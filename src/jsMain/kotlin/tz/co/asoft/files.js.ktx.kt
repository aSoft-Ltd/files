package tz.co.asoft

import kotlinx.coroutines.suspendCancellableCoroutine
import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Uint8Array
import org.khronos.webgl.get
import org.w3c.dom.HTMLAnchorElement
import org.w3c.dom.url.URL
import org.w3c.files.Blob
import org.w3c.files.FileReader
import kotlinx.browser.document
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

inline fun Uint8Array.toByteArray() = ByteArray(length) { this[it] }

suspend fun Blob.readBytes(onProgress: ((Int) -> Unit)? = null): ByteArray =
    suspendCancellableCoroutine { cont ->
        FileReader().apply {
            onProgress?.let {
                onprogress = { it(((100.0 * it.loaded.toDouble()) / it.total.toDouble()).toInt()) }
            }
            onload = { cont.resume(Uint8Array(result.unsafeCast<ArrayBuffer>()).toByteArray()) }
            onerror = { cont.resumeWithException(IOException("Failed to readBytes")) }
            readAsArrayBuffer(this@readBytes)
        }
    }

private fun String.initiateDownload() {
    val a = (document.createElement("a") as HTMLAnchorElement).apply {
        href = this@initiateDownload
        target = "new"
        download = name
    }
    document.body?.apply {
        appendChild(a)
        a.click()
        removeChild(a)
    }
}

fun FileRef.initiateDownload() = url.initiateDownload()

fun Blob.initiateDownload() = URL.createObjectURL(this).run {
    initiateDownload()
    URL.revokeObjectURL(this)
}