package es.clean.architecture.ui.common.states

sealed class ResourceState<T> {
    object Idle : ResourceState<Void>()
    data class Loading<String>(val data: String) : ResourceState<String>()
    data class Error<Throwable>(val throwable: Throwable) : ResourceState<Throwable>()
    data class Success<T>(val data: T) : ResourceState<T>()
}
