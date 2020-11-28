package tz.co.asoft

import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
import kotlinx.serialization.InternalSerializationApi
import java.io.InputStream
import java.io.OutputStream
import java.net.URI
import java.net.URLEncoder

fun Uri.toURI() = URI(URLEncoder.encode(toString(), "UTF-8"))

@OptIn(InternalSerializationApi::class)
suspend fun InputStream.copyTo(
    out: OutputStream,
    bufferSize: Int = DEFAULT_BUFFER_SIZE,
    yieldSize: Int = 4 * 1024 * 1024
) = withContext(Dispatchers.IO) {
    val buffer = ByteArray(bufferSize)
    var bytesCopied = 0L
    var bytesAfterYield = 0L
    while (true) {
        val bytes = read(buffer).takeIf { it >= 0 } ?: break
        out.write(buffer, 0, bytes)
        if (bytesAfterYield >= yieldSize) {
            yield()
            bytesAfterYield %= yieldSize
        }
        bytesCopied += bytes
        bytesAfterYield += bytes
    }
    return@withContext bytesCopied
}