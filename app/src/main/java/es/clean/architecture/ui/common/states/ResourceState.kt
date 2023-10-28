package es.clean.architecture.ui.common.states

sealed class ResourceState<T> {
     class Idle<T> : ResourceState<T>()
     class Loading<T>: ResourceState<T>()
    data class Error<T>(val throwable: Throwable) : ResourceState<T>()
    data class Success<T>(val data: T) : ResourceState<T>()
}
